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

    @GetMapping("/buyer")
    public ModelAndView buyer(Model model)
    {
        model.addAttribute("stocks", stockRepository.findAll());
        return new ModelAndView("buyer");
    }

    @GetMapping("/buyer/history")
    public ModelAndView history(Model model)
    {
        
        model.addAttribute("commands", historyRepository.findAllByBuyer(getUser().getId()));
        return new ModelAndView("buyerHistory");
    }

    @GetMapping("/buyer/buy")
    public ModelAndView buy(@RequestParam(name = "id") int id)
    {
        TraderStock stock = stockRepository.getStockById(id);

        // TODO: buy from stock

        if (stock.getAmount() > 0)
        {
            stock.buyFromStock();
            stockRepository.save(stock);

            // TODO: add to history

            BuyerHistory history = new BuyerHistory();
            history.setProductName(stock.getName());
            history.setTrader(stock.getTrader());
            history.setBuyer(getUser());
            history.setDate(new Date());
            history.setAmount(1);
            history.setProductPrice(stock.getPrice());
            historyRepository.save(history);
        }

        return new ModelAndView("redirect:/buyer");
    }

    private User getUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((MyUserDetails)principal).getUser();
    }
}
