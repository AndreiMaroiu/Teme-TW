package com.demo.controller;

import com.demo.model.UserAccountDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController{

    @GetMapping(value = "/profile")
    public ModelAndView viewProfile(Authentication authentication, final Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);
        UserAccountDto userAccount = principalDetailsService.getUserDataById(authentication.getName());
        model.addAttribute("userAccount", userAccount);
        mav.setViewName("profilePage");
        return mav;
    }
}
