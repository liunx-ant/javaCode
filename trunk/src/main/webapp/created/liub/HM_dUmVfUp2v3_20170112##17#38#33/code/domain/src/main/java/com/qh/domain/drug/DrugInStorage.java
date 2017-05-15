package com.qh.domain.drug;

import com.qh.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
  * 采购入库类
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public class DrugInStorage extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
	
	/**
     * 入库单号/交还单号
     */
	private String inStorageCode;
	
	
	/**
     * 采购单id/领用id
     */
	private String orderId;
	
	
	/**
     * 采购申请单号/领用单号
     */
	private String orderCode;
	
	
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
     * 采购人/交还人
     */
	private String buyer;
	
	
	/**
     * 
     */
	private String buyerName;
	
	
	/**
     * 审批状态
     */
	private Integer approveStatus;
	

    
	public String getInStorageCode() {
        return inStorageCode;
    }

    public void setInStorageCode(String inStorageCode) {
        this.inStorageCode = inStorageCode; 
    }
    
    
	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId; 
    }
    
    
	public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode; 
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
    
	public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer; 
    }
    
    
	public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName; 
    }
    
    
    
	public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus; 
    }
    
    
    
    
    
    
    
    
    
    
}