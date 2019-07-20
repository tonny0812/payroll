package com.wage.model;

import com.wage.annotation.EntityColumn;
import com.wage.annotation.ExcelEntity;
import com.wage.contants.ColumnType;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@ExcelEntity("员工")
public class Employee2 {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @EntityColumn(id=true)
    private Integer id;

    /**
     * 工号
     */
    @Column(name = "e_number")
    @EntityColumn(name="工号", type = ColumnType.LONG)
    private Long eNumber;

    /**
     * 员工名
     */
    @Column(name = "e_name")
    @EntityColumn(name="员工名", type = ColumnType.STRING)
    private String eName;

    /**
     * 性别
     */
    @Column(name = "e_sex")
    @EntityColumn(name="性别", type = ColumnType.BYTE)
    private Byte eSex;

    /**
     * 职称
     */
    @Column(name = "e_title")
    @EntityColumn(name="职称", type = ColumnType.STRING)
    private String eTitle;

    /**
     * 身份证号
     */
    @Column(name = "e_ID_card")
    @EntityColumn(name="身份证号", type = ColumnType.STRING)
    private String eIdCard;

    /**
     * 银行卡号
     */
    @Column(name = "e_bank_card")
    @EntityColumn(name="银行卡号", type = ColumnType.STRING)
    private String eBankCard;

    /**
     * 等级
     */
    @Column(name = "e_rank")
    @EntityColumn(name="等级", type = ColumnType.DOUBLE)
    private Double eRank;

    /**
     * 时间
     */
    @Column(name = "e_date")
    @EntityColumn(name="时间", type = ColumnType.DATE)
    private Date eDate;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setENumber(Long eNumber) {
        this.eNumber = eNumber;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public void setESex(Byte eSex) {
        this.eSex = eSex;
    }

    public void setETitle(String eTitle) {
        this.eTitle = eTitle;
    }

    public void setEIdCard(String eIdCard) {
        this.eIdCard = eIdCard;
    }

    public void setEBankCard(String eBankCard) {
        this.eBankCard = eBankCard;
    }

    public void setERank(Double eRank) {
        this.eRank = eRank;
    }

    public void setEDate(Date eDate) {
        this.eDate = eDate;
    }

    @Override
    public String toString() {
        return "Employee2{" +
                "id=" + id +
                ", eNumber=" + eNumber +
                ", eName='" + eName + '\'' +
                ", eSex=" + eSex +
                ", eTitle='" + eTitle + '\'' +
                ", eIdCard='" + eIdCard + '\'' +
                ", eBankCard='" + eBankCard + '\'' +
                ", eRank=" + eRank +
                ", eDate=" + eDate +
                '}';
    }
}
