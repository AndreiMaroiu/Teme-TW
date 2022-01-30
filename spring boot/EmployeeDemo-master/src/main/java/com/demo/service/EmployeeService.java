package com.demo.service;


import com.demo.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getEmployeeList();

    int saveEmployee(EmployeeDto employee);

    int updateEmployee(EmployeeDto employee);

    int deleteEmployee(int employeeId);

    EmployeeDto getEmployeeById(int employeeId);
}