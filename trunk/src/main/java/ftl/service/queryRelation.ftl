<#if (relObjects?size>0) >		//查询关联信息</#if>		
<#list relObjects as object>
		${object.className} ${object.objectName} = new ${object.className}();
	<#list mainObject.objectRels as objectRel>
		<#if object.className == objectRel.relClassName>
		<#list objectRel.relObject.properties as property>
			<#if property.colName == objectRel.relProperty>
		${object.objectName}.set${property.name?cap_first}(${objectRel.relToClassName?uncap_first}.get<#list objectRel.relToObject.properties as property><#if property.colName == objectRel.relToProperty>${property.name?cap_first}());</#if></#list>
			</#if>
		</#list>
		</#if>
	</#list>
	
	<#list mainObject.objectRels as objectRel>
		<#if objectRel.relClassName == object.className>
			<#if objectRel.relType == 'ONETOMANY'>
		List<${object.className}> list = ${object.objectName}Dao.getAll(${object.objectName});
		${mainObject.objectName}.set${object.className}List(list);
			<#else>
		${object.className} ${object.objectName}Info = ${object.objectName}Dao.getInfo(${object.objectName});
		${mainObject.objectName}.set${object.className}(${object.objectName}Info);
			</#if>
		</#if>
	</#list>
</#list>
<#if (relObjects?size>0) >		//查询关联信息结束</#if>	
