package com.wage.service.impl;

import com.wage.dao.EmployeeRepository;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: EmployeeService接口实现类
* @author zb
* @date 2018/06/22 14:46
*/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee insert(Employee model) {
        return employeeRepository.save(model);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {

    }

    @Override
    public Employee update(Employee model) {
        return employeeRepository.saveAndFlush(model);
    }

    @Override
    public Employee selectById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> selectAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeeByDepartmentId(Integer dId) {
        Department department = new Department();
        department.setId(dId);
        return employeeRepository.findAllByDepartment(department);
    }
}