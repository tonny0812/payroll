package com.wage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="t_deduction")
public class Deduction {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "d_title")
    private String dTitle;

    /**
     * 基本工资
     */
    @Column(name = "d_basic_wage")
    private Integer dBasicWage;

    /**
     * 奖金
     */
    @Column(name = "d_bonus")
    private Integer dBonus;

    /**
     * 罚金
     */
    @Column(name = "d_fine")
    private Integer dFine;

    /**
     * 税金
     */
    @Column(name = "d_tax")
    private Integer dTax;

    /**
     * 实际工资
     */
    @Column(name = "d_real_wage")
    private Integer dRealWage;

    /**
     * 是否确认
     */
    @Column(name = "d_state")
    private Integer dState;

    @Transient
    private Integer eId;

    @ManyToOne
    @JoinColumn(name="employee_id",referencedColumnName="id")
    private Employee employee;
}