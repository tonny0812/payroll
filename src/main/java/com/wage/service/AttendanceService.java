package com.wage.service;

import com.wage.model.Attendance;

import java.util.List;

/**
* @Description: AttendanceService接口
* @author zb
* @date 2018/06/22 14:23
*/
public interface AttendanceService extends Service<Attendance> {

    List<Attendance> selectTitles();

    List<Attendance> findAttendancesByEmployeeId(Integer eId);

    List<Attendance> findAttendancesByTitle(String title);

    void deleteAllByEmployeeId(Integer eId);
}