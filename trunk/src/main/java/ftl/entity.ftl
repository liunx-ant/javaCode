package ${mainObject.domainPackageName};

import java.io.Serializable;
import java.util.Date;
import ${projStructurePath}.util.domain.Model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.foriseland.fjf.sequence.annotation.SequenceField;
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
public class ${mainObject.className} implements Serializable {
    
 	/**
     * 序列
     */
    private static final long serialVersionUID = 1L;
<#list mainObject.properties as property>
	
	/**
     * ${property.title}
     */
     <#if  property.isPk>
	@SequenceField
	</#if>
	private ${property.type} ${property.name};
	
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