package com.achi.tw.app.controllers;


import com.achi.tw.app.repositories.ProductRepository;
import com.achi.tw.app.repositories.ProducerStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController
{
    private final ProducerStockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public HomeController(ProducerStockRepository stockRepository, ProductRepository productRepository)
    {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/login")
    public ModelAndView login()
    {
        return new ModelAndView("login");
    }

    @GetMapping({"/", "/home"})
    public ModelAndView hello(Model model)
    {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("stocks", stockRepository.findAll());
        return new ModelAndView("home");
    }

    @GetMapping("/form")
    public ModelAndView myForm(Model model)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("myForm");
        return mav;
    }



    @GetMapping("/accessDenied")
    public ModelAndView accessDenied()
    {
        return new ModelAndView("accessDenied");
    }
}
