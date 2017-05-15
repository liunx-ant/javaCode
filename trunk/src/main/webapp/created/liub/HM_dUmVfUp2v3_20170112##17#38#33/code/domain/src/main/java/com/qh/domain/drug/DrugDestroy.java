package com.qh.domain.drug;

import com.qh.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.qh.domain.drug.DrugBasic;

/**
 * 
  * 药剂销毁类
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public class DrugDestroy extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
	
	/**
     * 领用id
     */
	private String receptionId;
	
	
	/**
     * 领用编号
     */
	private String receptionCode;
	
	
	/**
     * 销毁编码
     */
	private String destroyCode;
	
	
	/**
     * 药剂id
     */
	private String drugId;
	
	
	/**
     * 销毁日期
     */
	private Date destroyTime;
	
	/**
     * 销毁日期
     */
	private String destroyTimeStr;
	/**
     *  销毁日期开始时间
     */
	private Date destroyTimeStartDate;
	/**
     *  销毁日期结束时间
     */
	private Date destroyTimeEndDate;
	
	/**
     * 数量
     */
	private Integer destroyNumber;
	
	
	/**
     * 生产厂家
     */
	private String manufacturer;
	
	
	/**
     * 残渣来源
     */
	private String source;
	
	
	/**
     * 销毁方式
     */
	private String destroyType;
	
	
	/**
     * 是否合规
     */
	private String compliance;
	
	
	/**
     * 备注
     */
	private String remark;
	
	
	/**
     * 审批状态
     */
	private Integer approveStatus;
	
	
	/**
     * 参与人
     */
	private String partIn;
	
	/**
     * 药剂基本信息
     */
	private DrugBasic drugBasic ;
	/**
     * 药剂基本信息集合
     */
	private List<DrugBasic> drugBasicList ;

    
	public String getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId; 
    }
    
    
	public String getReceptionCode() {
        return receptionCode;
    }

    public void setReceptionCode(String receptionCode) {
        this.receptionCode = receptionCode; 
    }
    
    
	public String getDestroyCode() {
        return destroyCode;
    }

    public void setDestroyCode(String destroyCode) {
        this.destroyCode = destroyCode; 
    }
    
    
	public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId; 
    }
    
    
	public Date getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(Date destroyTime) {
        this.destroyTime = destroyTime; 
    }
    
	public String getDestroyTimeStr() {
    	if(destroyTime!=null &&(destroyTimeStr==null || "".equals(destroyTimeStr))){
    	    return sdf.format(destroyTime);
    	}
    	return destroyTimeStr;
    }	
   		
    public Date getDestroyTimeStartDate() {
        return destroyTimeStartDate;
    }

    public void setDestroyTimeStartDate(Date destroyTimeStartDate) {
        this.destroyTimeStartDate = destroyTimeStartDate; 
    }
    
    public Date getDestroyTimeEndDate() {
        return destroyTimeEndDate;
    }

    public void setDestroyTimeEndDate(Date destroyTimeEndDate) {
        this.destroyTimeEndDate = destroyTimeEndDate; 
    }
    
	public Integer getDestroyNumber() {
        return destroyNumber;
    }

    public void setDestroyNumber(Integer destroyNumber) {
        this.destroyNumber = destroyNumber; 
    }
    
    
	public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer; 
    }
    
    
	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source; 
    }
    
    
	public String getDestroyType() {
        return destroyType;
    }

    public void setDestroyType(String destroyType) {
        this.destroyType = destroyType; 
    }
    
    
	public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance; 
    }
    
    
	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark; 
    }
    
    
	public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus; 
    }
    
    
    
    
    
    
    
    
    
    
    
	public String getPartIn() {
        return partIn;
    }

    public void setPartIn(String partIn) {
        this.partIn = partIn; 
    }
    
    
	public DrugBasic getDrugBasic() {
        return drugBasic;
    }

    public void setDrugBasic(DrugBasic drugBasic) {
        this.drugBasic = drugBasic; 
    }
    
	public List<DrugBasic> getDrugBasicList() {
        return drugBasicList;
    }

    public void setDrugBasicList(List<DrugBasic> drugBasicList) {
        this.drugBasicList = drugBasicList; 
    }
}