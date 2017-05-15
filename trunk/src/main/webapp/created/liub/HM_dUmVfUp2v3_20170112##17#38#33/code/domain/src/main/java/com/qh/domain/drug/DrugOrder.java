package com.qh.domain.drug;

import com.qh.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
  * 采购订单类
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public class DrugOrder extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
	
	/**
     * 采购单号
     */
	private String orderCode;
	
	
	/**
     * 部门
     */
	private String department;
	
	
	/**
     * 部门名称
     */
	private String departmentName;
	
	
	/**
     * 申请人id
     */
	private String applicant;
	
	
	/**
     * 申请人名称
     */
	private String applicantName;
	
	
	/**
     * 申请时间
     */
	private Date applicantTime;
	
	/**
     * 申请时间
     */
	private String applicantTimeStr;
	/**
     *  申请时间开始时间
     */
	private Date applicantTimeStartDate;
	/**
     *  申请时间结束时间
     */
	private Date applicantTimeEndDate;
	
	/**
     * 审批状态
     */
	private Integer approveStatus;
	
	
	/**
     * 入库状态
     */
	private Integer inventoryStatus;
	

    
	public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode; 
    }
    
    
	public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department; 
    }
    
    
	public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName; 
    }
    
    
	public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant; 
    }
    
    
	public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName; 
    }
    
    
	public Date getApplicantTime() {
        return applicantTime;
    }

    public void setApplicantTime(Date applicantTime) {
        this.applicantTime = applicantTime; 
    }
    
	public String getApplicantTimeStr() {
    	if(applicantTime!=null &&(applicantTimeStr==null || "".equals(applicantTimeStr))){
    	    return sdf.format(applicantTime);
    	}
    	return applicantTimeStr;
    }	
   		
    public Date getApplicantTimeStartDate() {
        return applicantTimeStartDate;
    }

    public void setApplicantTimeStartDate(Date applicantTimeStartDate) {
        this.applicantTimeStartDate = applicantTimeStartDate; 
    }
    
    public Date getApplicantTimeEndDate() {
        return applicantTimeEndDate;
    }

    public void setApplicantTimeEndDate(Date applicantTimeEndDate) {
        this.applicantTimeEndDate = applicantTimeEndDate; 
    }
    
	public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus; 
    }
    
    
	public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus; 
    }
    
    
    
    
    
    
    
    
    
    
}