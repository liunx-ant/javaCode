package com.qh.util.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.JSON;

/**
 * 实体类
 */
public class Model implements Serializable {
	/**
	 * 时间格式化
	 */
	protected SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 主键id
     */
    private String id;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 创建人id
     */
    private String createUserid;
    /**
     * 创建人部门id
     */
    private String createDeptid;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 创建时间
     */
    private String createDateStr;
    /**
     * 创建时间开始时间
     */
    private Date createDateStartDate;
    /**
     * 创建时间开始时间
     */
    private Date createDateEndDate;
    /**
     * 修改人id
     */
    private String modifyUserid;
    /**
     * 修改人部门id
     */
    private String modifyDeptid;
    /**
     * 修改时间
     */
    private Date modifyDate;
    /**
     * 修改时间
     */
    private String modifyDateStr;
    /**
     * 修改时间开始时间
     */
    private Date modifyDateStartDate;
    /**
     * 修改时间结束时间
     */
    private Date modifyDateEndDate;
    
    /**
     * 状态
     */
	private Integer status;
	
	
	/**
     * 删除状态
     */
	private Integer deleteFlag;

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public String getCreateDeptid() {
        return createDeptid;
    }

    public void setCreateDeptid(String createDeptid) {
        this.createDeptid = createDeptid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreateDateStr() {
        if(createDate!=null &&(createDateStr==null || "".equals(createDateStr))){
            return sdf.format(createDate);
        }
        return createDateStr;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDateStartDate() {
        return createDateStartDate;
    }

    public void setCreateDateStartDate(Date createDateStartDate) {
        this.createDateStartDate = createDateStartDate;
    }

    public Date getCreateDateEndDate() {
        return createDateEndDate;
    }

    public void setCreateDateEndDate(Date createDateEndDate) {
        this.createDateEndDate = createDateEndDate;
    }

    public String getModifyUserid() {
        return modifyUserid;
    }

    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }

    public String getModifyDeptid() {
        return modifyDeptid;
    }

    public void setModifyDeptid(String modifyDeptid) {
        this.modifyDeptid = modifyDeptid;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public String getModifyDateStr() {
    	if(modifyDate!=null &&(modifyDateStr==null || "".equals(modifyDateStr))){
    	    return sdf.format(modifyDate);
    	}
    	return modifyDateStr;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getModifyDateStartDate() {
        return modifyDateStartDate;
    }

    public void setModifyDateStartDate(Date modifyDateStartDate) {
        this.modifyDateStartDate = modifyDateStartDate;
    }

    public Date getModifyDateEndDate() {
        return modifyDateEndDate;
    }

    public void setModifyDateEndDate(Date modifyDateEndDate) {
        this.modifyDateEndDate = modifyDateEndDate;
    }

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status; 
    }
    
    
	public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag; 
    }

    public String toString() {
		return JSON.toJSONString(this);
	}

}
