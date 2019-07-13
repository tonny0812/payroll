package com.wage.dao;

import com.wage.model.Deduction;
import com.wage.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeductionRepository extends JpaRepository<Deduction, Integer> {

    List<Deduction> findDeductionsByDTitle(String title);

    List<Deduction> findDeductionsByDState(Integer state);

    List<Deduction> findAllByEmployee(Employee employee);

    void deleteDeductionsByEmployee(Employee employee);

}
