package com.tw.app.controllers;

import com.tw.app.repositoties.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{
    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @GetMapping({"/home", "/"})
    public ModelAndView home(Model model)
    {
        model.addAttribute("products", productRepository.findAll());
        return new ModelAndView("index");
    }
}
