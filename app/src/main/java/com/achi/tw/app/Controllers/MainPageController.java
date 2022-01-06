package com.achi.tw.app.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainPageController
{
    @RequestMapping("/onClick")
    public ModelAndView onClick(Model model)
    {
        System.out.println("merge!");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        model.addAttribute("ceva", "ceva");
        return mav;
    }
}
