package com.achi.tw.app.Controllers;


import com.achi.tw.app.Dao.ProductDao;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController
{
    @GetMapping("/login")
    public ModelAndView login()
    {
        return new ModelAndView("login");
    }

    @GetMapping({"/", "/home"})
    public ModelAndView hello(Model model)
    {
        ModelAndView mav = new ModelAndView("home");
        var products = ProductDao.getProducts();
        model.addAttribute("products", products);
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
