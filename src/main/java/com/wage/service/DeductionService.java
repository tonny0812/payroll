package com.wage.service;

import com.wage.model.Deduction;

import java.util.List;

/**
* @Description: DeductionService接口
* @author zb
* @date 2018/06/22 14:23
*/
public interface DeductionService extends Service<Deduction> {

    List<Deduction> selectTitles();

    List<Deduction> selectListByState();

    List<Deduction> selectTitlesByState();

    List<Deduction> findDeductionsByEmployeeId(Integer eId);

    List<Deduction> findDeductionsByTitle(String title);

    void deleteAllByEmployeeId(Integer eId);
}