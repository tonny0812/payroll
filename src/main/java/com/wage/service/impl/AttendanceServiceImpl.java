package com.wage.service.impl;

import com.wage.dao.AttendanceRepository;
import com.wage.model.Attendance;
import com.wage.model.Employee;
import com.wage.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description: AttendanceService接口实现类
* @author zb
* @date 2018/06/22 14:23
*/
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> selectTitles() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> findAttendancesByEmployeeId(Integer eId) {
        Employee e = new Employee();
        e.setId(eId);
        return attendanceRepository.findAllByEmployee(e);
    }

    @Override
    public List<Attendance> findAttendancesByTitle(String title) {
        return attendanceRepository.findAttendancesByATitle(title);
    }

    @Override
    @Transactional
    public void deleteAllByEmployeeId(Integer eId) {
        Employee e = new Employee();
        e.setId(eId);
        attendanceRepository.deleteAttendancesByEmployee(e);
    }

    @Override
    public Attendance insert(Attendance model) {
        return attendanceRepository.save(model);
    }

    @Override
    public void deleteById(Integer id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {

    }

    @Override
    public Attendance update(Attendance model) {
        return attendanceRepository.saveAndFlush(model);
    }

    @Override
    public Attendance selectById(Integer id) {
        return attendanceRepository.findById(id).get();
    }

    @Override
    public List<Attendance> selectAll() {
        return attendanceRepository.findAll();
    }

}