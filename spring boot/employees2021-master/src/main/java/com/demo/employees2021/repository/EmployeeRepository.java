package com.demo.employees2021.repository;

import com.demo.employees2021.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Modifying
    @Query("delete from Employee where id=:idEmployee")
    int deleteEmployeeById(int idEmployee);

    @Modifying
    @Query("delete from Employee where id_department=:departmentId")
    int deleteEmployeeByDeparmentId(int departmentId);
}
