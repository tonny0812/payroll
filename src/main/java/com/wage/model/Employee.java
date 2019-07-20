package com.wage.model;

import com.wage.annotation.EntityColumn;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="t_employee")
public class Employee {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Integer id;

    /**
     * 工号
     */
    @Column(name = "e_number")
    private String eNumber;

    /**
     * 员工名
     */
    @Column(name = "e_name")
    private String eName;

    /**
     * 性别
     */
    @Column(name = "e_sex")
    private Byte eSex;

    /**
     * 职称
     */
    @Column(name = "e_title")
    private String eTitle;

    /**
     * 身份证号
     */
    @Column(name = "e_ID_card")
    private String eIdCard;

    /**
     * 银行卡号
     */
    @Column(name = "e_bank_card")
    private String eBankCard;

    @Transient
    private Integer dId;

    @ManyToOne
    @JoinColumn(name="department_id",referencedColumnName="id")
    private Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", eNumber='" + eNumber + '\'' +
                ", eName='" + eName + '\'' +
                ", eSex=" + eSex +
                ", eTitle='" + eTitle + '\'' +
                ", eIdCard='" + eIdCard + '\'' +
                ", eBankCard='" + eBankCard + '\'' +
                '}';
    }
}