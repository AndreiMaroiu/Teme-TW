package com.demo.service;

import com.demo.model.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getDepartmentList();

    int deleteDepartment(int id);

    DepartmentDto getDepartmentById(int id);

    int updateDepartment(DepartmentDto department);

    int saveDepartment(DepartmentDto department);
}