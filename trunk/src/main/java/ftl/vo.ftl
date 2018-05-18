package ${mainObject.apiPackageName}.consumer.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
<#list relObjects as object>
import ${mainObject.domainPackageName}.consumer.${object.className}Vo;
</#list>

 /**
  * 
  * ${mainObject.title}Vo类
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
public class ${mainObject.className}Vo implements Serializable {
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
<#list mainObject.properties as property>
	
	/**
     * ${property.title}
     */
	private ${property.type} ${property.name};
	
</#list>
<#list relObjects as object>
	/**
     * ${object.title}
     */
	private ${object.className}Vo ${object.objectName} ;
	/**
     * ${object.title}集合
     */
	private List<${object.className}Vo> ${object.objectName}List ;
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
    
</#list>
<#list relObjects as object>
	public ${object.className}Vo get${object.className}() {
        return ${object.objectName};
    }

    public void set${object.className}(${object.className}Vo ${object.objectName}) {
        this.${object.objectName} = ${object.objectName}; 
    }
    
	public List<${object.className}Vo> get${object.className}List() {
        return ${object.objectName}List;
    }

    public void set${object.className}List(List<${object.className}Vo> ${object.objectName}List) {
        this.${object.objectName}List = ${object.objectName}List; 
    }
</#list>
}