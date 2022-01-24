package com.achi.tw.app.controllers;

import com.achi.tw.app.entity.BuyerHistory;
import com.achi.tw.app.entity.CartItem;
import com.achi.tw.app.entity.TraderStock;
import com.achi.tw.app.entity.User;
import com.achi.tw.app.repositories.CartItemRepository;
import com.achi.tw.app.repositories.HistoryRepository;
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

import java.util.List;

@RestController
public class BuyerController
{
    private final SocketsService socketsService;
    private final TraderStockRepository stockRepository;
    private final HistoryRepository historyRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public BuyerController(SocketsService socketsService, TraderStockRepository stockRepository,
                           HistoryRepository historyRepository, CartItemRepository cartItemRepository)
    {
        this.socketsService = socketsService;
        this.stockRepository = stockRepository;
        this.historyRepository = historyRepository;
        this.cartItemRepository = cartItemRepository;
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
            stocks = stockRepository.findAll();
        }

        model.addAttribute("stocks", stocks);

        return new ModelAndView("buyer");
    }

    @GetMapping("/buyer/history")
    public ModelAndView history(Model model)
    {
        model.addAttribute("commands", historyRepository.findAllByBuyer(SecurityUtils.getUser().getId()));
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
    private ModelAndView confirm(Model model, @RequestParam(required = false) Boolean insufficientStocks,
                                 @RequestParam(required = false) Integer stockId)
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
            total += item.getAmount() * item.getProductPrice();
        }

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("itemsCount", items.size());

        return new ModelAndView("buyerShoppingCart");
    }

    @PostMapping("/buyer/buyRemaining")
    public ModelAndView buyRemaningStock(@RequestParam Integer stockId)
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
            // TODO: redirect to confirm
            return new ModelAndView("redirect:/buyer/confirm?insufficientStocks=true&stockId=" + stockId);
        }
    }

    private void addToCart(Integer stockId, Integer quantity)
    {
        User buyer = SecurityUtils.getUser();
        BuyerHistory cart = historyRepository.findActiveCart(buyer.getId());

        // TODO: check if null, then create new

        if (cart == null)
        {
            cart = new BuyerHistory();
            cart.setBuyer(buyer);
            cart.setActive(true);
            historyRepository.save(cart);
        }

        TraderStock stock = stockRepository.getStockById(stockId);
        CartItem item = new CartItem();
        item.setHistory(cart);
        item.setAmount(quantity);
        item.setProductName(stock.getName());
        item.setProductPrice(stock.getPrice());
        item.setTrader(stock.getTrader());

        cartItemRepository.save(item);
    }

//    private void doBuy(Integer stockId, Integer quantity)
//    {
//        TraderStock stock = stockRepository.getStockById(stockId);
//
//        stock.buyFromStock(quantity);
//        stockRepository.save(stock);
//
//        // TODO: add to history
//
//        BuyerHistory history = new BuyerHistory();
//        history.setProductName(stock.getName());
//        history.setTrader(stock.getTrader());
//        history.setBuyer(SecurityUtils.getUser());
//        history.setDate(new Date());
//        history.setAmount(quantity);
//        history.setProductPrice(stock.getPrice());
//        historyRepository.save(history);
//
//        if (stock.getAmount() < stock.getMinStock())
//        {
//            socketsService.notifyUser(stock.getTrader().getId(), "stock refilled!");
//            stock.setAmount(stock.getMaxStock());
//            stockRepository.save(stock);
//        }
//    }
}
