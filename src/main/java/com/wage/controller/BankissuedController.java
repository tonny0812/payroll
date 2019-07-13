package com.wage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.model.Attendance;
import com.wage.model.Deduction;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.DeductionService;
import com.wage.service.DepartmentService;
import com.wage.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
* @Description: BankissuedController
* @author zb
* @date 2018/06/22 14:24
*/
@Controller
@RequestMapping("/bankissued")
public class BankissuedController {
    @Resource
    private DeductionService deductionService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Deduction>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Deduction> list = deductionService.selectListByState();
        list = list == null ? new ArrayList<>() : list;
        PageInfo<Deduction> pageInfo = new PageInfo<>(list);

        Set<String> titles = new HashSet<>();
        for(Deduction deduction : list) {
            titles.add(deduction.getDTitle());
        }
        List<String> titlesList = new ArrayList<>(titles);
        Collections.sort(titlesList);

        model.addAttribute("titles", titlesList);
        model.addAttribute("pageInfo", pageInfo);
        return "bankissuedList";
    }
}