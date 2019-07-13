package com.wage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.model.Attendance;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.AttendanceService;
import com.wage.service.DepartmentService;
import com.wage.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

/**
* @Description: AttendanceController类
* @author zb
* @date 2018/06/22 14:23
*/
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn RetResult<PageInfo<Attendance>>
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size, Model model) throws Exception {
        PageHelper.startPage(page, size);
        List<Attendance> list = attendanceService.selectAll();
        PageInfo<Attendance> pageInfo = new PageInfo<Attendance>(list);

        Set<String> titles = new HashSet<>();
        for(Attendance attendance : list) {
            titles.add(attendance.getATitle());
        }
        List<String> titlesList = new ArrayList<>(titles);
        Collections.sort(titlesList);

        model.addAttribute("titles", titlesList);
        model.addAttribute("pageInfo", pageInfo);
        return "attendanceList";
    }

    @GetMapping("/addPage")
    public String addPage(Model model, Integer id) throws Exception {
        Employee employee = employeeService.selectById(id);
        model.addAttribute("employee", employee);
        return "attendanceAdd";
    }

    @PostMapping("/add")
    public String add(Attendance attendance, Model model) throws Exception {
        Employee e = new Employee();
        e.setId(attendance.getEId());
        attendance.setEmployee(e);
        int aAbsence = attendance.getAAbsence() == null ? 0 : attendance.getAAbsence();
        int aLateEarly = attendance.getALateEarly() == null ? 0 : attendance.getALateEarly();
        int aReal = attendance.getAShould() - aAbsence - aLateEarly;
        attendance.setAReal(aReal);
        attendance.setAAbsences(aAbsence + aLateEarly);
        attendance = attendanceService.insert(attendance);
        if (attendance == null){
            model.addAttribute("message", "添加出勤情况失败");
            return "message";
        }else {
            model.addAttribute("message", "添加出勤情况成功");
            return "message";
        }
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, Integer id) throws Exception {
        Attendance attendance = attendanceService.selectById(id);
        model.addAttribute("attendance", attendance);
        return "attendanceUpdate";
    }

    @PostMapping("/update")
    public String update(Attendance attendance, Model model) throws Exception {
        Employee e = new Employee();
        e.setId(attendance.getEId());
        attendance.setEmployee(e);
        int aAbsence = attendance.getAAbsence() == null ? 0 : attendance.getAAbsence();
        int aLateEarly = attendance.getALateEarly() == null ? 0 : attendance.getALateEarly();
        int aReal = attendance.getAShould() - aAbsence - aLateEarly;
        attendance.setAReal(aReal);
        attendance.setAAbsences(aAbsence + aLateEarly);
        attendance = attendanceService.update(attendance);
        if (attendance == null){
            model.addAttribute("message", "修改出勤情况失败");
            return "message";
        }else {
            model.addAttribute("message", "修改出勤情况成功");
            return "message";
        }
    }

    @GetMapping("/delete")
    public String delete(Integer id, Model model) throws Exception {
        attendanceService.deleteById(id);
        model.addAttribute("message", "删除出勤情况成功");
        return "message";
    }
}