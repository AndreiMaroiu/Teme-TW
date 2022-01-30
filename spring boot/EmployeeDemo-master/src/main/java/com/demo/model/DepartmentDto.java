package com.demo.model;

import com.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private int id;

    @Pattern(regexp = "[A-Z]{2}\\d{2}", message = "Department code must contain 2 characters and 2 digits.")
    private String code;

    @Size(min = 2, max = 50, message = "The department must have between {min} and {max} characters.")
    private String description;

    private List<Employee> employeeList;

}