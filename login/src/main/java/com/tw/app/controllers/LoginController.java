package com.tw.app.controllers;

import com.tw.app.dto.NewUser;
import com.tw.app.entities.Role;
import com.tw.app.entities.User;
import com.tw.app.services.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class LoginController
{
    private final SignUpService signUpService;

    public LoginController(SignUpService signUpService)
    {
        this.signUpService = signUpService;
    }

    @GetMapping("/login")
    public ModelAndView login()
    {
        return new ModelAndView("login");
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied()
    {
        return new ModelAndView("accessDenied");
    }

    @GetMapping("/signUp")
    public ModelAndView signUp(Model model)
    {
        model.addAttribute("newUser", new NewUser());
        model.addAttribute("roles", signUpService.findAll());

        return new ModelAndView("signUp");
    }

    @PostMapping("/submit")
    public ModelAndView submit(@ModelAttribute NewUser newUser)
    {
        ModelAndView mav = new ModelAndView("redirect:/signUp");

        if (signUpService.save(newUser))
        {
            mav.setViewName("redirect:/login");
        }

        return mav;
    }
}
