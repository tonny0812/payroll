package com.wage.controller;

import com.wage.model.Attendance;
import com.wage.model.Deduction;
import com.wage.service.AttendanceService;
import com.wage.service.DeductionService;
import com.wage.util.DateUtil;
import com.wage.util.ExcelData;
import com.wage.util.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zb
 * @Date: Created in 2018/6/8 16:32
 * @Description:
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private DeductionService deductionService;

    @RequestMapping("/attendance")
    public void attendance(HttpServletResponse response, String title){
        List<Attendance> list = attendanceService.findAttendancesByTitle(title);

        ExcelData data = new ExcelData();
        data.setName("attendance");

        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("工号");
        titles.add("姓名");
        titles.add("所属部门");
        titles.add("应出勤次数/月");
        titles.add("实际出勤次数/月");
        titles.add("缺勤次数");
        titles.add("缺勤原因");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Attendance attendance = list.get(i);
            List<Object> row = new ArrayList();
            row.add(i + 1);
            row.add(attendance.getATitle());
            row.add(attendance.getEmployee().getENumber());
            row.add(attendance.getEmployee().getEName());
            row.add(attendance.getEmployee().getDepartment().getDName());
            row.add(attendance.getAShould());
            row.add(attendance.getAReal());
            row.add(attendance.getAAbsences());
            row.add(attendance.getAAbsencesCause());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            StringBuilder fileName = new StringBuilder();
            fileName.append("出勤情况表")
                    .append("(").append(DateUtil.formatYYYYMMDD(new Date()))
                    .append(")");
            ExcelUtil.exportExcel(response, fileName.toString(),data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/deduction")
    public void deduction(HttpServletResponse response, String title){
        List<Deduction> list = deductionService.selectAll();
        ExcelData data = new ExcelData();
        data.setName("deduction");

        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("工号");
        titles.add("姓名");
        titles.add("所属部门");
        titles.add("基本工资");
        titles.add("奖金");
        titles.add("罚金");
        titles.add("税金");
        titles.add("实际工资");
        titles.add("是否确认");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Deduction deduction = list.get(i);
            List<Object> row = new ArrayList();
            row.add(i + 1);
            row.add(deduction.getDTitle());
            row.add(deduction.getEmployee().getENumber());
            row.add(deduction.getEmployee().getEName());
            row.add(deduction.getEmployee().getDepartment().getDName());
            row.add(deduction.getDBasicWage());
            row.add(deduction.getDBonus());
            row.add(deduction.getDFine());
            row.add(deduction.getDTax());
            row.add(deduction.getDRealWage());
            row.add(deduction.getDState()==0?"否":"是");
            rows.add(row);
        }
        data.setRows(rows);
        try{
            StringBuilder fileName = new StringBuilder();
            fileName.append("工资汇总表")
                    .append("(").append(DateUtil.formatYYYYMMDD(new Date()))
                    .append(")");
            ExcelUtil.exportExcel(response, fileName.toString(),data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/bankIssued")
    public void bankIssued(HttpServletResponse response, String title){
        List<Deduction> list = deductionService.findDeductionsByTitle(title);
        ExcelData data = new ExcelData();
        data.setName("bankIssued");

        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("工号");
        titles.add("姓名");
        titles.add("所属部门");
        titles.add("身份证号");
        titles.add("银行卡号");
        titles.add("实际工资");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Deduction deduction = list.get(i);
            List<Object> row = new ArrayList();
            row.add(i + 1);
            row.add(deduction.getDTitle());
            row.add(deduction.getEmployee().getENumber());
            row.add(deduction.getEmployee().getEName());
            row.add(deduction.getEmployee().getDepartment().getDName());
            row.add(deduction.getEmployee().getEIdCard());
            row.add(deduction.getEmployee().getEBankCard());
            row.add(deduction.getDRealWage());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            StringBuilder fileName = new StringBuilder();
            fileName.append("银行发放表")
                    .append("(").append(DateUtil.formatYYYYMMDD(new Date()))
                    .append(")");
            ExcelUtil.exportExcel(response,fileName.toString(),data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
