package ${mainObject.apiPackageName}.consumer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
<#list relObjects as object>
import ${mainObject.apiPackageName}.consumer.${object.className}Dto;
</#list>

/**
 * 
  * ${mainObject.title}类
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
public class ${mainObject.className}Dto implements Serializable {
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
<#list mainObject.properties as property>
	
	/**
     * ${property.title}
     */
	private ${property.type} ${property.name};
	
		<#if property.type =='Date'>
	/**
     *  ${property.title}开始时间
     */
	private ${property.type} ${property.name}StartDate;
	/**
     *  ${property.title}结束时间
     */
	private ${property.type} ${property.name}EndDate;
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
	<#--第二个字母是大写 -->
	<#if ("ABCDEFGHIGKLMNOPQRSTUVWXYZ"?index_of("${property.name?substring(1,2)}") != -1)>
	public ${property.type} get${property.name}() {
	<#else>
	public ${property.type} get${property.name?cap_first}() {
	</#if>
        return ${property.name};
    }
	<#--第二个字母是大写 -->
	<#if ("ABCDEFGHIGKLMNOPQRSTUVWXYZ"?index_of("${property.name?substring(1,2)}") != -1)>
    public void set${property.name}(${property.type} ${property.name}) {
	<#else>
    public void set${property.name?cap_first}(${property.type} ${property.name}) {
	</#if>
        this.${property.name} = ${property.name}; 
    }
    
	<#if property.type =='Date'>
   		<#--第二个字母是大写 -->
   		<#if ("ABCDEFGHIGKLMNOPQRSTUVWXYZ"?index_of("${property.name?substring(1,2)}") != -1)>
    public ${property.type} get${property.name}StartDate() {
		<#else>
    public ${property.type} get${property.name?cap_first}StartDate() {
		</#if>	
        return ${property.name}StartDate;
    }
    <#--第二个字母是大写 -->
		<#if ("ABCDEFGHIGKLMNOPQRSTUVWXYZ"?index_of("${property.name?substring(1,2)}") != -1)>
    public void set${property.name}StartDate(${property.type} ${property.name}StartDate) {
		<#else>
    public void set${property.name?cap_first}StartDate(${property.type} ${property.name}StartDate) {
		</#if>
        this.${property.name}StartDate = ${property.name}StartDate; 
    }
    <#--第二个字母是大写 -->
    	<#if ("ABCDEFGHIGKLMNOPQRSTUVWXYZ"?index_of("${property.name?substring(1,2)}") != -1)>
    public ${property.type} get${property.name}EndDate() {
		<#else>
    public ${property.type} get${property.name?cap_first}EndDate() {
		</#if>
        return ${property.name}EndDate;
    }
    <#--第二个字母是大写 -->
		<#if ("ABCDEFGHIGKLMNOPQRSTUVWXYZ"?index_of("${property.name?substring(1,2)}") != -1)>
    public void set${property.name}EndDate(${property.type} ${property.name}EndDate) {
		<#else>
    public void set${property.name?cap_first}EndDate(${property.type} ${property.name}EndDate) {
		</#if>
        this.${property.name}EndDate = ${property.name}EndDate; 
    }
    </#if>
    
</#list>
<#list relObjects as object>
	public ${object.className}Dto get${object.className}() {
        return ${object.objectName};
    }

    public void set${object.className}(${object.className}Dto ${object.objectName}) {
        this.${object.objectName} = ${object.objectName}; 
    }
    
	public List<${object.className}Dto> get${object.className}List() {
        return ${object.objectName}List;
    }

    public void set${object.className}List(List<${object.className}Dto> ${object.objectName}List) {
        this.${object.objectName}List = ${object.objectName}List; 
    }
</#list>
}