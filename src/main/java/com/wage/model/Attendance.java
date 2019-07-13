package com.wage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="t_attendance")
public class Attendance {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "a_title")
    private String aTitle;

    /**
     * 应出勤次数/月
     */
    @Column(name = "a_should")
    private Integer aShould;

    /**
     * 实际出勤次数/月
     */
    @Column(name = "a_real")
    private Integer aReal;

    /**
     * 旷工次数/月
     */
    @Column(name = "a_absence")
    private Integer aAbsence ;

    /**
     * 迟到早退次数/月
     */
    @Column(name = "a_late_early")
    private Integer aLateEarly ;

    /**
     * 缺勤次数
     */
    @Column(name = "a_absences")
    private Integer aAbsences;

    /**
     * 缺勤原因
     */
    @Column(name = "a_absences_cause")
    private String aAbsencesCause;

    @Transient
    private Integer eId;

    @ManyToOne
    @JoinColumn(name="employee_id",referencedColumnName="id")
    private Employee employee;

}