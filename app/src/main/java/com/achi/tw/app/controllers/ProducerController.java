package com.achi.tw.app.controllers;

import com.achi.tw.app.entity.ProducerStock;
import com.achi.tw.app.entity.Product;
import com.achi.tw.app.entity.User;
import com.achi.tw.app.repositories.ProducerStockRepository;
import com.achi.tw.app.repositories.ProductRepository;
import com.achi.tw.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = SecurityUtils.getUser();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("stocks", stockRepository.getStocksByUser(user.getId()));

        return new ModelAndView("producer");
    }

    @GetMapping("/newStock")
    public ModelAndView newStock(Model model)
    {
        User user = SecurityUtils.getUser();

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("stocks", stockRepository.getStocksByUser(user.getId()));

        return new ModelAndView("newProducerStockPage");
    }

    @PostMapping("/addStock")
    public ModelAndView addStock(@RequestParam String name, @RequestParam Float price, @RequestParam String description)
    {
        try
        {
            ProducerStock producerStock = new ProducerStock();
            Product product = new Product();

            product.setName(name);
            product.setDescription(description);

            productRepository.save(product);

            producerStock.setProduct(product);
            producerStock.setPrice(price);
            producerStock.setProducer(SecurityUtils.getUser());

            stockRepository.save(producerStock);

            return new ModelAndView("redirect:/producer");
        }
        catch (Exception e)
        {
            return new ModelAndView("redirect:/newStock?error=" + e.getMessage());
        }
    }
}
