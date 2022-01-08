package com.achi.tw.app.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SignUpController
{
    @GetMapping("/signUp")
    public ModelAndView signUp()
    {
        return new ModelAndView("signUp");
    }
}
