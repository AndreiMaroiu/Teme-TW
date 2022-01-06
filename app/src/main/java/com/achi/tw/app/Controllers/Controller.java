package com.achi.tw.app.Controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller
{
    @GetMapping({"/", "/home"})
    public ModelAndView hello(Model model)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        model.addAttribute("name", "Achi");

        return mav;
    }

    @GetMapping("/form")
    public ModelAndView myForm(Model model)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("myForm");
        return mav;
    }
}
