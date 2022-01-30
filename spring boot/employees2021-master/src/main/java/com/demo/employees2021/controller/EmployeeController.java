package com.demo.employees2021.controller;

import com.demo.employees2021.entity.Employee;
import com.demo.employees2021.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employeeOverview")
    public ModelAndView viewEmployees(Model model) {
        ModelAndView mav = new ModelAndView();

        model.addAttribute("greetings", "Hello world!");

        List<String> employeeNameList = List.of("Popescu", "Georgescu", "Ionescu", "Alexandrescu");
        model.addAttribute("employeeNameList", employeeNameList);

        List<Employee> employeeList = employeeService.getEmployees();
        model.addAttribute("employeeList", employeeList);

        for (Employee employee: employeeList){
            System.out.println(employee.getId());
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getDepartment().getDepartmentCode());
            System.out.println(employee.getDepartment().getDepartmentName());
        }


        mav.setViewName("employeeOverviewPage");
        return mav;
    }

    @GetMapping(value = "/employeeForm")
    public ModelAndView getEmployeeForm(Model model){
        ModelAndView mav = new ModelAndView();

        Employee employee = Employee.builder().build();
        model.addAttribute("employee", employee);

        mav.setViewName("employeeForm");
        return mav;
    }

    @PostMapping(value = "/submitEmployee")
    public ModelAndView submitEmployee(@ModelAttribute Employee employee){
        ModelAndView mav = new ModelAndView();

        employeeService.saveEmployeeToDatabase(employee);

        mav.setViewName("redirect:/employeeOverview");
        return mav;
    }

    @PostMapping(value = "/editEmployee")
    public ModelAndView editEmployee(@RequestParam("employeeId") int idEmployee, Model model){
        ModelAndView mav = new ModelAndView();

        Employee employee = employeeService.getEmployeeById(idEmployee);
        model.addAttribute("employee", employee);

        mav.setViewName("employeeForm");
        return mav;
    }

    @PostMapping(value = "/deleteEmployee")
    public ModelAndView deleteEmployee(@RequestParam("employeeId") int idEmployee){
        ModelAndView mav = new ModelAndView();

        int deletedEmployeeId = employeeService.deleteEmployeeById(idEmployee);
        System.out.println("Number of deleted employees: " + deletedEmployeeId);

        mav.setViewName("redirect:/employeeOverview");
        return mav;
    }


}
