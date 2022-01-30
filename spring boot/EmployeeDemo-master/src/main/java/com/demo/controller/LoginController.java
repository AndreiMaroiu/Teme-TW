package com.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied(Authentication authentication, Model model) {
        ModelAndView mav = new ModelAndView();
        model.addAttribute("loggedUser", authentication.getName());
        mav.setViewName("accessDenied");
        return mav;
    }

    @GetMapping(value="/logout")
    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        mav.setViewName("redirect:/login");
        return mav;
    }

    @GetMapping(value="/")
    public ModelAndView index (Authentication authentication, HttpServletRequest request, HttpServletResponse response, Model model) {
        ModelAndView mav = new ModelAndView();
        model.addAttribute("loggedUser", authentication.getName());

        mav.setViewName("redirect:/index");
        return mav;
    }
}
