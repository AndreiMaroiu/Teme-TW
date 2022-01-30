package com.demo.serviceimpl;

import com.demo.entity.Employee;
import com.demo.model.EmployeeDto;
import com.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {

	@Override
	public List<EmployeeDto> getEmployeeList() {
		return modelMapper.mapAll(employeeRepository.findAll(), EmployeeDto.class);
	}

	@Override
	public int saveEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		return employeeRepository.save(employee).getId();
	}

	@Override
	public int updateEmployee(EmployeeDto employeeDto) {
		return saveEmployee(employeeDto);
	}

	@Override
	public int deleteEmployee(int employeeId) {
		return employeeRepository.deleteById(employeeId);
	}

	@Override
	public EmployeeDto getEmployeeById(int employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		return modelMapper.map(employee, EmployeeDto.class);
	}

}