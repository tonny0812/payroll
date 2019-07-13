package com.wage.dao;

import com.wage.model.Attendance;
import com.wage.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findAllByEmployee(Employee employee);

    void deleteAttendancesByEmployee(Employee employee);

    List<Attendance> findAttendancesByATitle(String title);
}
