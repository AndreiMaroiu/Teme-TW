package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAccountController {

    @GetMapping("/userAccountOverview")
    public ModelAndView userAccountOverview() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userAccountOverview");
        return mav;
    }
}
