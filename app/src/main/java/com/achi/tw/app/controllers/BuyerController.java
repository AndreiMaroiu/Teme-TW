package com.achi.tw.app.controllers;

import com.achi.tw.app.dto.Message;
import com.achi.tw.app.entity.*;
import com.achi.tw.app.repositories.CartItemRepository;
import com.achi.tw.app.repositories.HistoryRepository;
import com.achi.tw.app.repositories.TraderNotificationRepository;
import com.achi.tw.app.repositories.TraderStockRepository;
import com.achi.tw.app.services.SocketsService;
import com.achi.tw.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
public class BuyerController
{
    private final SocketsService socketsService;
    private final TraderStockRepository stockRepository;
    private final HistoryRepository historyRepository;
    private final CartItemRepository cartItemRepository;
    private final TraderNotificationRepository notificationRepository;

    @Autowired
    public BuyerController(SocketsService socketsService, TraderStockRepository stockRepository,
                           HistoryRepository historyRepository, CartItemRepository cartItemRepository,
                           TraderNotificationRepository notificationRepository)
    {
        this.socketsService = socketsService;
        this.stockRepository = stockRepository;
        this.historyRepository = historyRepository;
        this.cartItemRepository = cartItemRepository;
        this.notificationRepository = notificationRepository;
    }

    private static boolean isNullOrEmpty(String str)
    {
        return str == null || str.trim().length() == 0;
    }

    @GetMapping("/buyer")
    public ModelAndView buyer(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) Float maxPrice)
    {
        Iterable<TraderStock> stocks = null;
        if (!isNullOrEmpty(name) && maxPrice != null)
        {
            stocks = stockRepository.findAllByNameAndPrice(maxPrice, name);
        }
        else if (!isNullOrEmpty(name))
        {
            stocks = stockRepository.findStocksByProductName(name);
        }
        else if (maxPrice != null)
        {
            stocks = stockRepository.findAllByPrice(maxPrice);
        }
        else
        {
            stocks = stockRepository.findAllAvailable();
        }

        model.addAttribute("stocks", stocks);

        return new ModelAndView("buyer");
    }

    @GetMapping("/buyer/history")
    public ModelAndView history(Model model)
    {
        model.addAttribute("commands", historyRepository.findAllInactiveByUser(SecurityUtils.getUser().getId()));
        return new ModelAndView("buyerHistory");
    }

    @GetMapping("/buyer/checkout")
    public ModelAndView checkout(Model model, @RequestParam Integer id)
    {
        TraderStock stock = stockRepository.findById(id).get();
        model.addAttribute("stock", stock);
        return new ModelAndView("buyerCheckout");
    }

    @GetMapping("/buyer/confirm")
    private ModelAndView confirm(Model model, @RequestParam(required = false) Integer stockId)
    {
        model.addAttribute("stockId", stockId);
        return new ModelAndView("buyerConfirm");
    }

    @GetMapping("/buyer/shoppingCart")
    public ModelAndView shoppingCart(Model model)
    {
        List<CartItem> items = cartItemRepository.findAllByUser(SecurityUtils.getUser().getId());
        float total = 0.0f;

        for (var item : items)
        {
            total += item.getAmount() * item.getStock().getPrice();
        }

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("itemsCount", items.size());

        return new ModelAndView("buyerShoppingCart");
    }

    @PostMapping("/buyer/shoppingCart/buy")
    public ModelAndView buyShoppingCart()
    {
        User buyer = SecurityUtils.getUser();

        BuyerHistory cart = historyRepository.findActiveCart(buyer.getId());

        for (var item : cart.getItems())
        {
            TraderStock stock = item.getStock();

            if (stock.getAmount() >= item.getAmount())
            {
                stock.buyFromStock(item.getAmount());
            }
            else
            {
                return new ModelAndView("buyerConfirmShoppingCart");
            }
        }

        for (var item : cart.getItems())
        {
            TraderStock stock = item.getStock();

            if (stock.getAmount() < stock.getMinStock())
            {
                notifyTrader(stock);
            }

            stockRepository.save(stock);
        }

        cart.setDate(new Date());
        cart.setActive(false);
        historyRepository.save(cart);

        // TODO: should redirect to success page
        return new ModelAndView("redirect:/buyer/shoppingCart/success?id=" + cart.getId());
    }

    @PostMapping("/buyer/buyRemaining")
    public ModelAndView buyRemainingStock(@RequestParam Integer stockId)
    {
        var stock = stockRepository.findById(stockId);
        addToCart(stockId, stock.get().getAmount());
        return new ModelAndView("redirect:/buyer/confirm");
    }

    @PostMapping("/buyer/buy")
    public ModelAndView buy(@RequestParam Integer stockId, @RequestParam Integer quantity)
    {
        TraderStock stock = stockRepository.getStockById(stockId);

        if (stock.getAmount() >= quantity)
        {
            addToCart(stockId, quantity);
            return new ModelAndView("redirect:/buyer/confirm");
        }
        else
        {
            return new ModelAndView("redirect:/buyer/confirm?insufficientStocks=true&stockId=" + stockId);
        }
    }

    @GetMapping("/buyer/shoppingCart/buyRemaining")
    public ModelAndView buyRemaining()
    {
        BuyerHistory cart = getCart();

        for (var item : cart.getItems())
        {
            TraderStock stock = item.getStock();

            if (item.getAmount() > stock.getAmount())
            {
                item.setAmount(stock.getAmount());
                stock.buyFromStock(stock.getAmount());
            }
            else
            {
                stock.buyFromStock(item.getAmount());
            }

            stockRepository.save(stock);
            notifyTrader(stock);
        }

        cart.setDate(new Date());
        cart.setActive(false);

        historyRepository.save(cart);

        return new ModelAndView("redirect:/buyer/shoppingCart/success?id=" + cart.getId());
    }

    @GetMapping("/buyer/shoppingCart/clear")
    public ModelAndView clearShoppingCart()
    {
        BuyerHistory cart = getCart();

        cartItemRepository.deleteAll(cart.getItems());

        return new ModelAndView("redirect:/buyer/shoppingCart");
    }

    @GetMapping("/buyer/shoppingCart/success")
    public ModelAndView shoppingCartSucces(Model model, @RequestParam Integer id)
    {
        BuyerHistory cart = historyRepository.findById(id).get();
        model.addAttribute("cart", cart);
        return new ModelAndView("buyerSuccess");
    }

    private BuyerHistory getCart()
    {
        User buyer = SecurityUtils.getUser();
        BuyerHistory cart = historyRepository.findActiveCart(buyer.getId());

        if (cart == null)
        {
            cart = new BuyerHistory();
            cart.setBuyer(buyer);
            cart.setActive(true);
            historyRepository.save(cart);
        }

        return cart;
    }

    private void addToCart(Integer stockId, Integer quantity)
    {
        BuyerHistory cart = getCart();

        TraderStock stock = stockRepository.getStockById(stockId);
        CartItem item = new CartItem();
        item.setHistory(cart);
        item.setAmount(quantity);
        item.setStock(stock);

        cartItemRepository.save(item);
    }

    private void notifyTrader(TraderStock stock)
    {
        Integer traderId = stock.getTrader().getId();
        String message = "Stock empty for " + stock.getName();
        socketsService.notifyUser(traderId, new Message(message, stock.getId()));

        TraderNotification notification = new TraderNotification();

        notification.setTrader(stock.getTrader());
        notification.setMessage(message);
        notification.setStockId(stock.getId());

        notificationRepository.save(notification);
    }
}
