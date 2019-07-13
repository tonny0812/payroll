package com.wage.service;

import java.util.List;

/**
 * @Author: zb
 * @Date: Created in 2018/6/22 21:04
 * @Description: 通用service
 */
public interface Service<T> {

    /**
     * @param model
     * @Description: 插入
     * @Reutrn
     */
    T insert(T model);

    /**
     * @param id
     * @Description: 通过主鍵刪除
     * @Reutrn void
     */
    void deleteById(Integer id);

    /**
     * @param ids
     * @Description: 批量刪除 eg：ids -> “1,2,3,4”
     * @Reutrn Integer
     */
    void deleteByIds(List<Integer> ids);

    /**
     * @param model
     * @Description: 更新
     * @Reutrn Integer
     */
    T update(T model);

    /**
     * @param id
     * @Description: 通过ID查找
     * @Reutrn T
     */
    T selectById(Integer id);


    /**
     * @Description: 获取所有
     * @Reutrn List<T>
     */
    List<T> selectAll();

}