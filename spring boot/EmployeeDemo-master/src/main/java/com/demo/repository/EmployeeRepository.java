package com.demo.repository;

import com.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Modifying
	@Query("delete from Employee where id=:id")
	public int deleteById(@Param("id")int id);
	
	@Query("select e from Employee e WHERE e.firstName=:firstName AND e.lastName=:lastName")
	public Employee getByFirstnameAndLastName(String firstName, String lastName);
}