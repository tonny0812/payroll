package com.wage.controller;

import com.github.pagehelper.PageHelper;
import com.wage.model.Admin;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.AdminService;
import com.wage.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import com.wage.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: DepartmentController类
* @author zb
* @date 2018/06/22 14:21
*/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private AdminService adminService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Department>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.selectAll();
        list = list == null ? new ArrayList<>() : list;
        PageInfo<Department> pageInfo = new PageInfo<Department>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "departmentList";
    }

    @GetMapping("/addPage")
    public String addPage() throws Exception {
        return "departmentAdd";
    }

    @PostMapping("/add")
    public String add(Department department, Model model) throws Exception {
        department = departmentService.insert(department);
        if (department == null){
            model.addAttribute("message", "添加部门失败");
            return "message";
        }else {
            model.addAttribute("message", "添加部门成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, Integer id) throws Exception {
        Department department = departmentService.selectById(id);
        model.addAttribute("department", department);
        return "departmentUpdate";
    }

    @PostMapping("/update")
    public String update(Department department, Model model) throws Exception {
        department = departmentService.update(department);
        if (department == null){
            model.addAttribute("message", "修改部门失败");
            return "message";
        }else {
            model.addAttribute("message", "修改部门成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(Integer id, Model model) throws Exception {

        List<Employee> list = employeeService.findEmployeeByDepartmentId(id);
        List<Admin> alist = adminService.findAdminsByDepartmentId(id);
        if(!CollectionUtils.isEmpty(list)) {
            model.addAttribute("message", "删除部门失败，有员工绑定！");
        } else if(!CollectionUtils.isEmpty(alist)){
            model.addAttribute("message", "删除部门失败，有管理员绑定！");
        } else {
            departmentService.deleteById(id);
            model.addAttribute("message", "删除部门成功");
        }
        return "message";
    }

}