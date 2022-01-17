package com.achi.tw.app.Controllers;

import com.achi.tw.app.Entity.ProducerStock;
import com.achi.tw.app.Entity.Product;
import com.achi.tw.app.Entity.User;
import com.achi.tw.app.Repositories.ProducerStockRepository;
import com.achi.tw.app.Repositories.ProductRepository;
import com.achi.tw.app.Services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProducerController
{
    private final ProducerStockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProducerController(ProducerStockRepository stockRepository, ProductRepository productRepository)
    {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/producer")
    public ModelAndView main(Model model)
    {
        User user = getUser();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("stocks", stockRepository.getStocksByUser(user.getId()));

        return new ModelAndView("producer");
    }

    @GetMapping("/newStock")
    public ModelAndView newStock(Model model)
    {
        User user = getUser();

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("stocks", stockRepository.getStocksByUser(user.getId()));

        return new ModelAndView("newProducerStockPage");
    }

    @PostMapping("/addStock")
    public ModelAndView addStock(@RequestParam(name = "name") String name, @RequestParam Float price)
    {
        try
        {
            ProducerStock producerStock = new ProducerStock();

            producerStock.setName(name);
            producerStock.setPrice(price);
            producerStock.setProducer(getUser());

            stockRepository.save(producerStock);

            return new ModelAndView("redirect:/producer");
        }
        catch (Exception e)
        {
            return new ModelAndView("redirect:/newStock?error=" + e.getMessage());
        }
    }

    private User getUser()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((MyUserDetails)principal).getUser();
    }
}
