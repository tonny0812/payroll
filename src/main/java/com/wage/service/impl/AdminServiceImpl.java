package com.wage.service.impl;

import com.wage.dao.AdminRepository;
import com.wage.model.Admin;
import com.wage.model.Department;
import com.wage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: AdminService接口实现类
* @author zb
* @date 2018/06/23 18:01
*/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin insert(Admin model) {
        return adminRepository.save(model);
    }

    @Override
    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
    }

    @Override
    public Admin update(Admin model) {
        return adminRepository.saveAndFlush(model);
    }

    @Override
    public Admin selectById(Integer id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public List<Admin> selectAll() {
        return adminRepository.findAllByType(1);
    }

    @Override
    public Admin findAdminByUname(String username) {
        return adminRepository.findDistinctByUname(username);
    }

    @Override
    public List<Admin> findAdminsByDepartmentId(Integer dId) {
        Department department = new Department();
        department.setId(dId);
        return adminRepository.findAllByDepartment(department);
    }
}