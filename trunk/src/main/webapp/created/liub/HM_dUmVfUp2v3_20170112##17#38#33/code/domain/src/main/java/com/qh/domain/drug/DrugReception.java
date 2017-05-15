package com.qh.domain.drug;

import com.qh.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
  * 药剂领用类
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public class DrugReception extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
	
	/**
     * 领用单号
     */
	private String receptionCode;
	
	
	/**
     * 登记时间
     */
	private Date registrationTime;
	
	/**
     * 登记时间
     */
	private String registrationTimeStr;
	/**
     *  登记时间开始时间
     */
	private Date registrationTimeStartDate;
	/**
     *  登记时间结束时间
     */
	private Date registrationTimeEndDate;
	
	/**
     * 部门
     */
	private String receptionDep;
	
	
	/**
     * 部门名称
     */
	private String receptionDepname;
	
	
	/**
     * 领用人
     */
	private String receptionUser;
	
	
	/**
     * 领用人名称
     */
	private String receptionUsername;
	
	
	/**
     * 施药仓库
     */
	private String warehouse;
	
	
	/**
     * 施药货位
     */
	private String allocation;
	
	
	/**
     * 用途
     */
	private String purpose;
	
	
	/**
     * 审批状态
     */
	private Integer approveStatus;
	

    
	public String getReceptionCode() {
        return receptionCode;
    }

    public void setReceptionCode(String receptionCode) {
        this.receptionCode = receptionCode; 
    }
    
    
	public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime; 
    }
    
	public String getRegistrationTimeStr() {
    	if(registrationTime!=null &&(registrationTimeStr==null || "".equals(registrationTimeStr))){
    	    return sdf.format(registrationTime);
    	}
    	return registrationTimeStr;
    }	
   		
    public Date getRegistrationTimeStartDate() {
        return registrationTimeStartDate;
    }

    public void setRegistrationTimeStartDate(Date registrationTimeStartDate) {
        this.registrationTimeStartDate = registrationTimeStartDate; 
    }
    
    public Date getRegistrationTimeEndDate() {
        return registrationTimeEndDate;
    }

    public void setRegistrationTimeEndDate(Date registrationTimeEndDate) {
        this.registrationTimeEndDate = registrationTimeEndDate; 
    }
    
	public String getReceptionDep() {
        return receptionDep;
    }

    public void setReceptionDep(String receptionDep) {
        this.receptionDep = receptionDep; 
    }
    
    
	public String getReceptionDepname() {
        return receptionDepname;
    }

    public void setReceptionDepname(String receptionDepname) {
        this.receptionDepname = receptionDepname; 
    }
    
    
	public String getReceptionUser() {
        return receptionUser;
    }

    public void setReceptionUser(String receptionUser) {
        this.receptionUser = receptionUser; 
    }
    
    
	public String getReceptionUsername() {
        return receptionUsername;
    }

    public void setReceptionUsername(String receptionUsername) {
        this.receptionUsername = receptionUsername; 
    }
    
    
	public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse; 
    }
    
    
	public String getAllocation() {
        return allocation;
    }

    public void setAllocation(String allocation) {
        this.allocation = allocation; 
    }
    
    
	public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose; 
    }
    
    
	public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus; 
    }
    
    
    
    
    
    
    
    
    
    
    
}