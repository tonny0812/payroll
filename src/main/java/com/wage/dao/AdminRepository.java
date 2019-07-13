package com.wage.dao;

import com.wage.model.Admin;
import com.wage.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findDistinctByUname(String username);

    List<Admin> findAllByType(Integer type);

    List<Admin> findAllByDepartment(Department department);
}
