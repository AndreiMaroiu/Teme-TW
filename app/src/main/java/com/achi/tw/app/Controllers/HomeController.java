package com.achi.tw.app.Controllers;


import com.achi.tw.app.Repositories.ProductRepository;
import com.achi.tw.app.Repositories.ProducerStockRepository;
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
        ModelAndView mav = new ModelAndView("home");
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("stocks", stockRepository.findAll());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView myForm(Model model)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("myForm");
        return mav;
    }

    @GetMapping("/buyer")
    public ModelAndView buyer(Model model)
    {
        ModelAndView mav = new ModelAndView("buyer");
        return mav;
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied()
    {
        return new ModelAndView("accessDenied");
    }
}
