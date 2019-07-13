package com.wage.service.impl;

import com.wage.dao.DeductionRepository;
import com.wage.model.Deduction;
import com.wage.model.Employee;
import com.wage.service.DeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description: DeductionService接口实现类
* @author zb
* @date 2018/06/22 14:23
*/
@Service
public class DeductionServiceImpl implements DeductionService {

    @Autowired
    private DeductionRepository deductionRepository;

    @Override
    public List<Deduction> selectTitles() {
        return deductionRepository.findAll();
    }

    @Override
    public List<Deduction> selectListByState() {
        return deductionRepository.findDeductionsByDState(1);
    }

    @Override
    public List<Deduction> selectTitlesByState() {
        return deductionRepository.findAll();
    }

    @Override
    public List<Deduction> findDeductionsByEmployeeId(Integer eId) {
        Employee e = new Employee();
        e.setId(eId);
        return deductionRepository.findAllByEmployee(e);
    }

    @Override
    public List<Deduction> findDeductionsByTitle(String title) {
        return deductionRepository.findDeductionsByDTitle(title);
    }

    @Override
    @Transactional
    public void deleteAllByEmployeeId(Integer eId) {
        Employee e = new Employee();
        e.setId(eId);
        deductionRepository.deleteDeductionsByEmployee(e);
    }

    @Override
    public Deduction insert(Deduction model) {
        return deductionRepository.save(model);
    }

    @Override
    public void deleteById(Integer id) {
        deductionRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {

    }

    @Override
    public Deduction update(Deduction model) {
        return deductionRepository.saveAndFlush(model);
    }

    @Override
    public Deduction selectById(Integer id) {
        return deductionRepository.findById(id).get();
    }

    @Override
    public List<Deduction> selectAll() {
        return deductionRepository.findAll();
    }

}