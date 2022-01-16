package com.achi.tw.app.Controllers;

import com.achi.tw.app.Entity.User;
import com.achi.tw.app.Repositories.StockRepository;
import com.achi.tw.app.Services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProducerController
{
    private final StockRepository stockRepository;

    @Autowired
    public ProducerController(StockRepository stockRepository)
    {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/producer")
    public ModelAndView main(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = ((MyUserDetails)principal).getUser();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("stocks", stockRepository.getStocksByUser(user.getId()));

        ModelAndView mav = new ModelAndView("producer");
        return mav;
    }
}
