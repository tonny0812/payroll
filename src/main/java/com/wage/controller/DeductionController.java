package com.wage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.model.Deduction;
import com.wage.model.Department;
import com.wage.model.Employee;
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
import java.util.*;

/**
* @Description: DeductionController类
* @author zb
* @date 2018/06/22 14:23
*/
@Controller
@RequestMapping("/deduction")
public class DeductionController {

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
        List<Deduction> list = deductionService.selectAll();
        list = list == null ? new ArrayList<>() : list;
        PageInfo<Deduction> pageInfo = new PageInfo<Deduction>(list);

        Set<String> titles = new HashSet<>();
        for(Deduction deduction : list) {
            titles.add(deduction.getDTitle());
        }
        List<String> titlesList = new ArrayList<>(titles);
        Collections.sort(titlesList);

        model.addAttribute("titles", titlesList);
        model.addAttribute("pageInfo", pageInfo);
        return "deductionList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model, Integer id) throws Exception {
        Employee employee = employeeService.selectById(id);
        model.addAttribute("employee", employee);
        return "deductionAdd";
    }

    @PostMapping("/add")
    public String add(Deduction deduction, Model model) throws Exception {
        Employee e = new Employee();
        e.setId(deduction.getEId());
        deduction.setEmployee(e);
        int baseWage = deduction.getDBasicWage() == null ? 0 : deduction.getDBasicWage();
        int bonus = deduction.getDBonus() == null ? 0 : deduction.getDBonus();
        int fine = deduction.getDFine() == null ? 0 : deduction.getDFine();
        deduction.setDRealWage(baseWage + bonus - fine);
        deduction.setDState(0);
        deduction = deductionService.insert(deduction);
        if (null == deduction){
            model.addAttribute("message", "添加工资详情失败");
            return "message";
        }else {
            model.addAttribute("message", "添加工资详情成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, Integer id) throws Exception {
        Deduction deduction = deductionService.selectById(id);
        model.addAttribute("deduction", deduction);
        return "deductionUpdate";
    }

    @PostMapping("/update")
    public String update(Deduction deduction, Model model) throws Exception {
        Employee e = new Employee();
        e.setId(deduction.getEId());
        deduction.setEmployee(e);
        int baseWage = deduction.getDBasicWage() == null ? 0 : deduction.getDBasicWage();
        int bonus = deduction.getDBonus() == null ? 0 : deduction.getDBonus();
        int fine = deduction.getDFine() == null ? 0 : deduction.getDFine();
        deduction.setDRealWage(baseWage + bonus - fine);
        deduction.setDState(0);
        deduction = deductionService.update(deduction);
        if (deduction == null){
            model.addAttribute("message", "修改工资详情失败");
            return "message";
        }else {
            model.addAttribute("message", "修改工资详情成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(Integer id, Model model) throws Exception {
        deductionService.deleteById(id);
        model.addAttribute("message", "删除工资详情成功");
        return "message";
    }

    @GetMapping("/updateState")
    public String updateState(Integer id, String dState, Model model) throws Exception {
        Deduction deduction = deductionService.selectById(id);
        deduction.setDState(Integer.valueOf(dState));
        deduction = deductionService.update(deduction);
        if (null == deduction){
            model.addAttribute("message", "修改状态失败");
            return "message";
        }else {
            return "redirect:/deduction/list?page=1&size=10";
        }
    }
}