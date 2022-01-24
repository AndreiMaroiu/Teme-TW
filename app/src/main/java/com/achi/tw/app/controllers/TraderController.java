package com.achi.tw.app.controllers;

import com.achi.tw.app.entity.ProducerStock;
import com.achi.tw.app.entity.TraderStock;
import com.achi.tw.app.repositories.ProducerStockRepository;
import com.achi.tw.app.repositories.TraderNotificationRepository;
import com.achi.tw.app.repositories.TraderStockRepository;
import com.achi.tw.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class TraderController
{
    private final ProducerStockRepository stockRepository;
    private final TraderStockRepository traderStockRepository;
    private final TraderNotificationRepository notificationRepository;

    @Autowired
    public TraderController(ProducerStockRepository stockRepository, TraderStockRepository traderStockRepository, TraderNotificationRepository notificationRepository)
    {
        this.stockRepository = stockRepository;
        this.traderStockRepository = traderStockRepository;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/trader")
    public ModelAndView trader(Model model, @RequestParam(name = "name", required = false) String name)
    {
        Iterable<ProducerStock> stocks = null;

        if (name != null)
        {
            stocks = stockRepository.getStocksByProductName(name);
            model.addAttribute("name", name);
        }
        else
        {
            stocks = stockRepository.findAll();
        }

        model.addAttribute("stocks", stocks);
        return new ModelAndView("trader");
    }

    @GetMapping("/trader/inventory")
    public ModelAndView traderInventory(Model model, @RequestParam(required = false) String refilledStock)
    {
        model.addAttribute("stocks", traderStockRepository.getStocksByUser(SecurityUtils.getUser().getId()));
        return new ModelAndView("traderInventory");
    }

    @GetMapping("/trader/confirm")
    public ModelAndView confirm(Model model, @RequestParam(name = "stockId") int stockId,
                                @RequestParam(name = "error", required = false) String error)
    {
        Optional<ProducerStock> cond = stockRepository.findById(stockId);
        cond.ifPresent(producerStock -> model.addAttribute("stock", producerStock));

        if (error != null)
        {
            model.addAttribute("error", error);
        }

        return new ModelAndView("traderConfirm");
    }

    @PostMapping("/trader/confirm")
    public ModelAndView confirmBuy(@RequestParam int stockId, @RequestParam float sellingPrice,
                                   @RequestParam int min, @RequestParam int max)
    {
        try
        {
            ProducerStock stock = stockRepository.findById(stockId).get();
            TraderStock entity = new TraderStock();

            entity.setTrader(SecurityUtils.getUser());
            entity.setPrice(stock.getPrice() + sellingPrice);
            entity.setAmount(max);
            entity.setMaxStock(max);
            entity.setMinStock(min);
            entity.setProduct(stock.getProduct());

            traderStockRepository.save(entity);

            return new ModelAndView("redirect:/trader/inventory");
        }
        catch (Exception e)
        {
            return new ModelAndView("redirect:/trader/confirm?stockId=" + stockId + "?error=" + e.getMessage());
        }
    }

    @PostMapping("/trader/buy")
    public ModelAndView traderBuy(@RequestParam(value = "name", required = false) String name)
    {
        if (name == null)
        {
            return new ModelAndView("redirect:/trader");
        }

        List<ProducerStock> list = stockRepository.getStocksByProductName(name);

        if (list != null && list.size() > 0)
        {
            ProducerStock first = list.get(0);
            return new ModelAndView("redirect:/trader/confirm?stockId=" + first.getId());
        }

        return new ModelAndView("redirect:/trader");
    }

    @PostMapping("/trader/findByName")
    public ModelAndView findByName(@RequestParam(name = "name") String name)
    {
        return new ModelAndView("redirect:/trader?name=" + name);
    }

    @GetMapping("/trader/refill")
    public ModelAndView refill(@RequestParam(name = "id") Integer id)
    {
        TraderStock stock = traderStockRepository.findById(id).get();
        stock.refill();
        traderStockRepository.save(stock);
        return new ModelAndView("redirect:/trader/inventory?refilledStock=" + stock.getName());
    }

    @GetMapping("/trader/refillAll")
    public ModelAndView refillAll()
    {
        var stocks = traderStockRepository.getStocksByUser(SecurityUtils.getUser().getId());

        for (var stock : stocks)
        {
            stock.refill();
            traderStockRepository.save(stock);
        }

        return new ModelAndView("redirect:/trader/inventory?refilledStock=all");
    }

    @GetMapping("/trader/notifications")
    public ModelAndView notifications(Model model)
    {
        model.addAttribute("notifications", notificationRepository.findAllByUser(SecurityUtils.getUser().getId()));

        return  new ModelAndView("traderNotifications");
    }

    @GetMapping("/trader/notification/delete")
    public ModelAndView deleteNotification(@RequestParam Integer id)
    {
        notificationRepository.deleteById(id);

        return new ModelAndView("redirect:/trader/notifications");
    }
}
