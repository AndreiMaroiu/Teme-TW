package com.demo.employees2021.controller;

import com.demo.employees2021.entity.Department;
import com.demo.employees2021.entity.Employee;
import com.demo.employees2021.repository.EmployeeRepository;
import com.demo.employees2021.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/printHello")
    @ResponseBody
    public String printHello(){
        return "Hello World!";
    }

    @GetMapping(value = "/printParam")
    @ResponseBody
    public String printParam(@RequestParam("username") String name){
        return "Hello " + name;
    }

    @GetMapping(value = "/printPathVariable/{country}/index")
    @ResponseBody
    public String printPathVariable(@PathVariable String country){
        return "Site country " + country;
    }

    @GetMapping(value = "/saveEmployeeEntity")
    @ResponseBody
    public String saveEmployeeEntity(){
        Department department = Department.builder()
                .id(2)
                .build();

        Employee employee = Employee.builder()
                .firstName("Ion")
                .lastName("Vasilescu")
                .monthlySalary(3500)
                .department(department)
                .build();

        employeeService.saveEmployeeToDatabase(employee);

        return "Employee saved";
    }

    @GetMapping(value = "/updateEmployeeEntity")
    @ResponseBody
    public String updateEmployeeEntity(){
        Department department = Department.builder()
                .id(2)
                .build();

        Employee employee = Employee.builder()
                .id(3)
                .firstName("Ion")
                .lastName("Vasilescu")
                .monthlySalary(3500)
                .department(department)
                .build();

        employeeService.saveEmployeeToDatabase(employee);

        return "Employee updated";
    }

    @GetMapping(value = "/deleteEmployeeByDepartment")
    @ResponseBody
    public String deleteEmployeeByDepartment(){
        int deletedEmployeeId = employeeService.deleteEmployeeByDeparmentId(1);
        return "Number of deleted employees: " + deletedEmployeeId;
    }
}
