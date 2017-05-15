package ${mainObject.domainPackageName};

import ${projStructurePath}.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
<#list relObjects as object>
import ${mainObject.domainPackageName}.${object.className};
</#list>

/**
 * 
  * ${mainObject.title}类
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
public class ${mainObject.className} extends Model{
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
<#list mainObject.properties as property>
	<#if property.name !='id' && property.name !='companyId' 
		&& property.name !='createUserid'&& property.name !='createDeptid'&& property.name !='createDate'
		&& property.name !='modifyUserid'&& property.name !='modifyDeptid'&& property.name !='modifyDate'
		&& property.name !='status'&& property.name !='deleteFlag'>
	
	/**
     * ${property.title}
     */
	private ${property.type} ${property.name};
	
		<#if property.type =='Date'>
	/**
     * ${property.title}
     */
	private String ${property.name}Str;
	/**
     *  ${property.title}开始时间
     */
	private ${property.type} ${property.name}StartDate;
	/**
     *  ${property.title}结束时间
     */
	private ${property.type} ${property.name}EndDate;
		</#if>
	</#if>
</#list>
<#list relObjects as object>
	/**
     * ${object.title}
     */
	private ${object.className} ${object.objectName} ;
	/**
     * ${object.title}集合
     */
	private List<${object.className}> ${object.objectName}List ;
</#list>

<#list mainObject.properties as property>
	<#if property.name !='id' && property.name !='companyId' 
		&& property.name !='createUserid'&& property.name !='createDeptid'&& property.name !='createDate'
		&& property.name !='modifyUserid'&& property.name !='modifyDeptid'&& property.name !='modifyDate'
		&& property.name !='status'&& property.name !='deleteFlag'>
	public ${property.type} get${property.name?cap_first}() {
		<#if property.type == 'BigDecimal'>
		if(${property.name}==null){
			//${property.name}=BigDecimal.ZERO;
		}
		</#if>
        return ${property.name};
    }

    public void set${property.name?cap_first}(${property.type} ${property.name}) {
        this.${property.name} = ${property.name}; 
    }
    
   		<#if property.type =='Date'>
	public String get${property.name?cap_first}Str() {
    	if(${property.name}!=null &&(${property.name}Str==null || "".equals(${property.name}Str))){
    	    return sdf.format(${property.name});
    	}
    	return ${property.name}Str;
    }	
   		
    public ${property.type} get${property.name?cap_first}StartDate() {
        return ${property.name}StartDate;
    }

    public void set${property.name?cap_first}StartDate(${property.type} ${property.name}StartDate) {
        this.${property.name}StartDate = ${property.name}StartDate; 
    }
    
    public ${property.type} get${property.name?cap_first}EndDate() {
        return ${property.name}EndDate;
    }

    public void set${property.name?cap_first}EndDate(${property.type} ${property.name}EndDate) {
        this.${property.name}EndDate = ${property.name}EndDate; 
    }
    	</#if>
    </#if>
    
</#list>
<#list relObjects as object>
	public ${object.className} get${object.className}() {
        return ${object.objectName};
    }

    public void set${object.className}(${object.className} ${object.objectName}) {
        this.${object.objectName} = ${object.objectName}; 
    }
    
	public List<${object.className}> get${object.className}List() {
        return ${object.objectName}List;
    }

    public void set${object.className}List(List<${object.className}> ${object.objectName}List) {
        this.${object.objectName}List = ${object.objectName}List; 
    }
</#list>
}