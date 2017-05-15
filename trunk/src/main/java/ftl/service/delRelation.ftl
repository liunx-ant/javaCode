<#assign show="false"/>
<#list relObjects as rb>
	<#list rb.properties as rbp>
		<#if rbp.propertyEdit.isExist>
			<#assign show="true"/>
		</#if>
	</#list>
</#list>

<#if show == 'true'>
<#if (relObjects?size>0) >		//先删除所有关联关系</#if>	
<#list relObjects as object>
		if((${mainObject.objectName}.get${object.className}List()!=null &&${mainObject.objectName}.get${object.className}List().size()>0)||${mainObject.objectName}.get${object.className}()!=null){
			${object.className} ${object.objectName}=new ${object.className}();
	<#list mainObject.objectRels as objectRel>
				<#if object.className == objectRel.relClassName>
					<#list objectRel.relObject.properties as property>
						<#if property.colName == objectRel.relProperty>
			${object.objectName}.set${property.name?cap_first}(${objectRel.relToClassName?uncap_first}.get<#list objectRel.relToObject.properties as property><#if property.colName == objectRel.relToProperty>${property.name?cap_first}());</#if></#list>
						</#if>
					</#list>
				</#if>
	</#list>
			${object.objectName}Dao.delete(${object.objectName});
		}
</#list>	
<#if (relObjects?size>0) >		//先删除所有关联关系结束</#if>	
</#if>