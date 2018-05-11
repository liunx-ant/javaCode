package ${mainObject.domainPackageName}.domain;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.foriseland.fjf.sequence.annotation.SequenceField;

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

<#list mainObject.properties as property>
	public ${property.type} get${property.name?cap_first}() {
        return ${property.name};
    }

    public void set${property.name?cap_first}(${property.type} ${property.name}) {
        this.${property.name} = ${property.name}; 
    }
    
</#list>
}