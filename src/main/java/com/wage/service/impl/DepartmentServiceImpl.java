package com.wage.service.impl;

import com.wage.dao.DepartmentRepository;
import com.wage.model.Department;
import com.wage.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: DepartmentService接口实现类
* @author zb
* @date 2018/06/22 14:21
*/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department insert(Department model) {
        return departmentRepository.save(model);
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {

    }

    @Override
    public Department update(Department model) {
        return null;
    }

    @Override
    public Department selectById(Integer id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public List<Department> selectAll() {
        return departmentRepository.findAll();
    }

}