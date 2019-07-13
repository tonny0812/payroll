package com.wage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.model.*;
import com.wage.service.AttendanceService;
import com.wage.service.DeductionService;
import com.wage.service.DepartmentService;
import com.wage.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: EmployeeController类
* @author zb
* @date 2018/06/22 14:46
*/
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private DeductionService deductionService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Employee>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model, HttpServletRequest request) throws Exception {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        List<Employee> list;

        PageHelper.startPage(page, size);
        if (admin == null || admin.getType() == 0){
            list = employeeService.selectAll();
        }else {
            list = employeeService.findEmployeeByDepartmentId(admin.getDepartment().getId());
        }
        list = list == null ? new ArrayList<>() : list;
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "employeeList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model) throws Exception {
        List<Department> departments = departmentService.selectAll();
        model.addAttribute("departments", departments);
        return "employeeAdd";
    }

    @PostMapping("/add")
    public String add(Employee employee, Model model) throws Exception {
        Department d = new Department();
        d.setId(employee.getDId());
        employee.setDepartment(d);
        employee = employeeService.insert(employee);
        if (employee == null){
            model.addAttribute("message", "添加职员失败");
            return "message";
        }else {
            model.addAttribute("message", "添加职员成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, Integer id) throws Exception {
        Employee employee = employeeService.selectById(id);
        List<Department> departments = departmentService.selectAll();

        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        return "employeeUpdate";
    }

    @PostMapping("/update")
    public String update(Employee employee, Model model) throws Exception {
        Department d = new Department();
        d.setId(employee.getDId());
        employee.setDepartment(d);
        employee = employeeService.update(employee);
        if (employee == null){
            model.addAttribute("message", "修改职员失败");
            return "message";
        }else {
            model.addAttribute("message", "修改职员成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(Integer id, Model model) throws Exception {
        attendanceService.deleteAllByEmployeeId(id);
        deductionService.deleteAllByEmployeeId(id);
        employeeService.deleteById(id);
        model.addAttribute("message", "删除职员成功");
        return "message";
    }
}