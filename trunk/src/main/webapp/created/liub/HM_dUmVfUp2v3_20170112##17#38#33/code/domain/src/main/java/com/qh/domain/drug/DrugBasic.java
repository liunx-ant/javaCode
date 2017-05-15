package com.qh.domain.drug;

import com.qh.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
  * 药剂基本信息类
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public class DrugBasic extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
	
	/**
     * 药剂类别
     */
	private String drugType;
	
	
	/**
     * 药剂名称
     */
	private String drugName;
	
	
	/**
     * 计量单位
     */
	private String drugUnit;
	
	
	/**
     * 规格型号
     */
	private String drugModel;
	
	
	/**
     * 数量
     */
	private Integer drugNumber;
	

    
	public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType; 
    }
    
    
	public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName; 
    }
    
    
	public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit; 
    }
    
    
	public String getDrugModel() {
        return drugModel;
    }

    public void setDrugModel(String drugModel) {
        this.drugModel = drugModel; 
    }
    
    
	public Integer getDrugNumber() {
        return drugNumber;
    }

    public void setDrugNumber(Integer drugNumber) {
        this.drugNumber = drugNumber; 
    }
    
    
    
    
    
    
    
    
    
    
    
}