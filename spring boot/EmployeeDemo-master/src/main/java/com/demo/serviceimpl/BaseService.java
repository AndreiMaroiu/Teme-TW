package com.demo.serviceimpl;

import com.demo.config.ObjectMapperUtils;
import com.demo.repository.DepartmentRepository;
import com.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    ObjectMapperUtils modelMapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

}
