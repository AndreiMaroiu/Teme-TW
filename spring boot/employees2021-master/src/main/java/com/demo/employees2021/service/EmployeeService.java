package com.demo.employees2021.service;

import com.demo.employees2021.entity.Employee;
import com.demo.employees2021.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployeeToDatabase(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int idEmployee) {
        Optional<Employee> employee = employeeRepository.findById(idEmployee);
        return employee.get();
    }

    @Transactional
    public int deleteEmployeeById(int idEmployee) {
        return employeeRepository.deleteEmployeeById(idEmployee);
    }

    @Transactional
    public int deleteEmployeeByDeparmentId(int departmentId) {
        return employeeRepository.deleteEmployeeByDeparmentId(departmentId);
    }
}
