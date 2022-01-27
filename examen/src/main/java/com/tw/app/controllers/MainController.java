package com.tw.app.controllers;

import com.tw.app.repositoties.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController
{
    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public ModelAndView test(Model model)
    {
        model.addAttribute("products", productRepository.findAll());
        return new ModelAndView("index");
    }
}
