package com.achi.tw.app.Controllers;

import com.achi.tw.app.Repositories.ProducerStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TraderController
{
    private final ProducerStockRepository stockRepository;

    @Autowired
    public TraderController(ProducerStockRepository stockRepository)
    {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/trader")
    public ModelAndView trader(Model model)
    {
        model.addAttribute("stocks", stockRepository.findAll());
        return new ModelAndView("trader");
    }
}
