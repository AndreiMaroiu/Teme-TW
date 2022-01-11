package com.achi.tw.app.Controllers;


import com.achi.tw.app.Dao.ProductDao;
import com.achi.tw.app.Repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController
{
    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/login")
    public ModelAndView login()
    {
        return new ModelAndView("login");
    }

    @GetMapping({"/", "/home"})
    public ModelAndView hello(Model model)
    {
        ModelAndView mav = new ModelAndView("home");
        model.addAttribute("products",  ProductDao.getProducts());
        model.addAttribute("stocks", stockRepository.findAll());
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
