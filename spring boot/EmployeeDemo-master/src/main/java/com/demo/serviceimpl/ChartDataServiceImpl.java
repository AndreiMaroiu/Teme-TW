package com.demo.serviceimpl;

import com.demo.entity.Department;
import com.demo.entity.Employee;
import com.demo.model.BarDataDto;
import com.demo.model.DonutDataDto;
import com.demo.service.ChartDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ChartDataServiceImpl extends BaseService implements ChartDataService {


    @Override
    public List<DonutDataDto> getDonutData() {
        AtomicInteger index = new AtomicInteger();

        List<Department> departmentList = departmentRepository.findAll();

        List<DonutDataDto> donutDataDtoList = departmentList.stream()
                .map(dep -> new DonutDataDto(dep.getCode() + " " + dep.getDescription(), dep.getEmployeeList().size(), index.getAndIncrement()))
                .collect(Collectors.toList());
        return donutDataDtoList;
    }

    @Override
    public List<BarDataDto> getBarData() {
        List<Department> departmentList = departmentRepository.findAll();

        List<BarDataDto> barDataDtoList = departmentList.stream()
                .map(dep -> new BarDataDto(dep.getCode() + "\n" + dep.getDescription(), dep.getEmployeeList().stream().map(Employee::getMonthlySalary).reduce(0, Integer::sum)))
                .collect(Collectors.toList());
        return barDataDtoList;
    }
}
