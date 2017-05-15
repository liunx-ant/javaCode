package com.qh.domain.drug;

import com.qh.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
  * 药剂出入库类
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public class DrugManager extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
	
	/**
     * 药剂id
     */
	private String drugId;
	
	
	/**
     * 关联ID
     */
	private String correlationId;
	
	
	/**
     * 关联单号
     */
	private String correlationCode;
	
	
	/**
     * 出入类型
     */
	private String accessType;
	
	
	/**
     * 生产厂家
     */
	private String manufacturer;
	
	
	/**
     * 单价
     */
	private BigDecimal price;
	
	
	/**
     * 用途
     */
	private String purpose;
	
	
	/**
     * 备注
     */
	private String remark;
	
	
	/**
     * 生产日期
     */
	private Date producedTime;
	
	/**
     * 生产日期
     */
	private String producedTimeStr;
	/**
     *  生产日期开始时间
     */
	private Date producedTimeStartDate;
	/**
     *  生产日期结束时间
     */
	private Date producedTimeEndDate;
	
	/**
     * 出入日期
     */
	private Date optionTime;
	
	/**
     * 出入日期
     */
	private String optionTimeStr;
	/**
     *  出入日期开始时间
     */
	private Date optionTimeStartDate;
	/**
     *  出入日期结束时间
     */
	private Date optionTimeEndDate;
	
	/**
     * 商标
     */
	private String digest;
	
	
	/**
     * 收入
     */
	private Integer income;
	
	
	/**
     * 支出
     */
	private Integer expenditure;
	
	
	/**
     * 结存
     */
	private Integer balance;
	

    
	public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId; 
    }
    
    
	public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId; 
    }
    
    
	public String getCorrelationCode() {
        return correlationCode;
    }

    public void setCorrelationCode(String correlationCode) {
        this.correlationCode = correlationCode; 
    }
    
    
	public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType; 
    }
    
    
	public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer; 
    }
    
    
	public BigDecimal getPrice() {
		if(price==null){
			//price=BigDecimal.ZERO;
		}
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price; 
    }
    
    
	public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose; 
    }
    
    
	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark; 
    }
    
    
    
	public Date getProducedTime() {
        return producedTime;
    }

    public void setProducedTime(Date producedTime) {
        this.producedTime = producedTime; 
    }
    
	public String getProducedTimeStr() {
    	if(producedTime!=null &&(producedTimeStr==null || "".equals(producedTimeStr))){
    	    return sdf.format(producedTime);
    	}
    	return producedTimeStr;
    }	
   		
    public Date getProducedTimeStartDate() {
        return producedTimeStartDate;
    }

    public void setProducedTimeStartDate(Date producedTimeStartDate) {
        this.producedTimeStartDate = producedTimeStartDate; 
    }
    
    public Date getProducedTimeEndDate() {
        return producedTimeEndDate;
    }

    public void setProducedTimeEndDate(Date producedTimeEndDate) {
        this.producedTimeEndDate = producedTimeEndDate; 
    }
    
	public Date getOptionTime() {
        return optionTime;
    }

    public void setOptionTime(Date optionTime) {
        this.optionTime = optionTime; 
    }
    
	public String getOptionTimeStr() {
    	if(optionTime!=null &&(optionTimeStr==null || "".equals(optionTimeStr))){
    	    return sdf.format(optionTime);
    	}
    	return optionTimeStr;
    }	
   		
    public Date getOptionTimeStartDate() {
        return optionTimeStartDate;
    }

    public void setOptionTimeStartDate(Date optionTimeStartDate) {
        this.optionTimeStartDate = optionTimeStartDate; 
    }
    
    public Date getOptionTimeEndDate() {
        return optionTimeEndDate;
    }

    public void setOptionTimeEndDate(Date optionTimeEndDate) {
        this.optionTimeEndDate = optionTimeEndDate; 
    }
    
	public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest; 
    }
    
    
	public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income; 
    }
    
    
	public Integer getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Integer expenditure) {
        this.expenditure = expenditure; 
    }
    
    
	public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance; 
    }
    
    
    
    
    
    
    
    
    
}