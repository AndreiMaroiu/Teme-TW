package com.achi.tw.app.Controllers;

import com.achi.tw.app.Entity.BuyerHistory;
import com.achi.tw.app.Entity.TraderStock;
import com.achi.tw.app.Entity.User;
import com.achi.tw.app.Repositories.HistoryRepository;
import com.achi.tw.app.Repositories.TraderStockRepository;
import com.achi.tw.app.Services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
public class BuyerController
{
    private final TraderStockRepository stockRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public BuyerController(TraderStockRepository stockRepository, HistoryRepository historyRepository)
    {
        this.stockRepository = stockRepository;
        this.historyRepository = historyRepository;
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
        model.addAttribute("commands", historyRepository.findAllByBuyer(getUser().getId()));
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

    @PostMapping("/buyer/buyRemaining")
    public ModelAndView buyRemaningStock(@RequestParam Integer stockId)
    {
        var stock = stockRepository.findById(stockId);
        doBuy(stockId, stock.get().getAmount());
        return new ModelAndView("redirect:/buyer/confirm");
    }

    @PostMapping("/buyer/buy")
    public ModelAndView buy(@RequestParam Integer stockId, @RequestParam Integer quantity)
    {
        TraderStock stock = stockRepository.getStockById(stockId);

        if (stock.getAmount() >= quantity)
        {
            doBuy(stockId, quantity);
            return new ModelAndView("redirect:/buyer/confirm");
        }
        else
        {
            // TODO: redirect to confirm
            return new ModelAndView("redirect:/buyer/confirm?insufficientStocks=true&stockId=" + stockId);
        }
    }

    private void doBuy(Integer stockId, Integer quantity)
    {
        TraderStock stock = stockRepository.getStockById(stockId);

        stock.buyFromStock(quantity);
        stockRepository.save(stock);

        // TODO: add to history

        BuyerHistory history = new BuyerHistory();
        history.setProductName(stock.getName());
        history.setTrader(stock.getTrader());
        history.setBuyer(getUser());
        history.setDate(new Date());
        history.setAmount(quantity);
        history.setProductPrice(stock.getPrice());
        historyRepository.save(history);
    }

    private User getUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((MyUserDetails)principal).getUser();
    }
}
