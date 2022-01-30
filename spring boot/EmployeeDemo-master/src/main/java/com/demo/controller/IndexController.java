package com.demo.controller;

import com.demo.model.BarDataDto;
import com.demo.model.DonutDataDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController extends BaseController{

    @GetMapping(value="/index")
    public ModelAndView index (Authentication authentication, Model model) {
        ModelAndView mav = new ModelAndView();
        addModelAttributes(model, authentication);

        List<DonutDataDto> donutData = chartDataService.getDonutData();
        model.addAttribute("donutDataLabels", donutData.stream().map(DonutDataDto::getLabel).toArray());
        model.addAttribute("donutDataValues", donutData.stream().map(DonutDataDto::getValue).toArray());
        model.addAttribute("donutDataColors", donutData.stream().map(DonutDataDto::getColor).toArray());

        List<BarDataDto> barData = chartDataService.getBarData();
        model.addAttribute("barDataLabels", barData.stream().map(BarDataDto::getLabel).toArray());
        model.addAttribute("barDataValues", barData.stream().map(BarDataDto::getValue).toArray());
        mav.setViewName("index");
        return mav;
    }
}
