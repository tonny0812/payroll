package com.wage.service;

import com.wage.model.Admin;

import java.util.List;

/**
* @Description: AdminService接口
* @author zb
* @date 2018/06/23 18:01
*/
public interface AdminService extends Service<Admin> {

    Admin findAdminByUname(String username);

    List<Admin> findAdminsByDepartmentId(Integer dId);
}