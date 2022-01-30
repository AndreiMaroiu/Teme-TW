package com.tw.app.controllers;

import com.tw.app.repositoties.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{
    private final AnimalRepository animalRepository;

    public MainController(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/")
    public ModelAndView test(Model model)
    {
        model.addAttribute("animals", animalRepository.findAll());
        return new ModelAndView("index");
    }

    @GetMapping("/test")
    public String test2()
    {
        return "redirect:/";
    }
}
