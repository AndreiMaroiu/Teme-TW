package com.demo.serviceimpl;

import com.demo.entity.Department;
import com.demo.model.DepartmentDto;
import com.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl extends BaseService implements DepartmentService {

	@Override
	public List<DepartmentDto> getDepartmentList() {
		return modelMapper.mapAll(departmentRepository.findAll(), DepartmentDto.class);
	}

	@Override
	public int saveDepartment(DepartmentDto departmentDto) {
		Department department = modelMapper.map(departmentDto, Department.class);
		return departmentRepository.save(department).getId();
	}

	@Override
	public int updateDepartment(DepartmentDto departmentDto) {
		return saveDepartment(departmentDto);
	}

	@Override
	public int deleteDepartment(int id) {
		return departmentRepository.deleteById(id);
	}

	@Override
	public DepartmentDto getDepartmentById(int id) {
		Department department = departmentRepository.findById(id).orElse(null);
		return modelMapper.map(department, DepartmentDto.class);
	}



	

	
}