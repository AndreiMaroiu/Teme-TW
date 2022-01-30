package com.demo.repository;

import com.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Modifying
	@Query("delete from Department where id=:id")
	public int deleteById(@Param("id") int id);

}