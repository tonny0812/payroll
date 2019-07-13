package com.wage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="t_admin")
public class Admin {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "uName")
    private String uname;

    /**
     * 密码
     */
    @Column(name = "pwd")
    private String pwd;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;

    @Transient
    private Integer dId;

    @ManyToOne
    @JoinColumn(name="department_id",referencedColumnName="id")
    private Department department;

}