package com.wage.service;

import com.wage.model.Employee;

import java.util.List;

/**
* @Description: EmployeeService接口
* @author zb
* @date 2018/06/22 14:46
*/
public interface EmployeeService extends Service<Employee> {

    List<Employee> findEmployeeByDepartmentId(Integer dId);
}