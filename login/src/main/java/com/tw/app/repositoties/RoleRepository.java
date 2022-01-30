package com.tw.app.repositoties;

import com.tw.app.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role getRoleByName(@Param("name") String name);
}
