package com.demo.controller;

import com.demo.config.security.PrincipalDetailsService;
import com.demo.service.ChartDataService;
import com.demo.service.DepartmentService;
import com.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import java.util.stream.Collectors;

public class BaseController {

    public static final String ALERT = "alert";

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public DepartmentService departmentService;

    @Autowired
    public PrincipalDetailsService principalDetailsService;

    @Autowired
    public ChartDataService chartDataService;

    public void addModelAttributes(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", principalDetailsService.getUserDataById(authentication.getName()));
        model.addAttribute("userPermissions", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }
}
