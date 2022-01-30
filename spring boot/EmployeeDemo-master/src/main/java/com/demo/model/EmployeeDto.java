package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private int id;

    @Pattern(regexp = "[A-Z]{3}\\d{4}", message = "Employee number must contain 3 characters and 4 digits.")
    private String employeeNumber;

    @Size(min = 2, max = 50, message = "The lastname must have between {min} and {max} characters.")
    private String lastName;

    @Size(min = 2, max = 50, message = "The firstname must have between {min} and {max} characters.")
    private String firstName;

    @Min(value = 3000, message = "The monthly salary must be greater than {value}.")
    @Max(value = 999999, message = "The monthly salary must be lower than {value}.")
    private int monthlySalary;

    @Valid
    private DepartmentDto department;
}
