<#assign show="false"/>
<#list relObjects as rb>
	<#list rb.properties as rbp>
		<#if rbp.propertyNew.isExist>
			<#assign show="true"/>
		</#if>
	</#list>
</#list>

<#if show == 'true'>
<#list relObjects as object>
	//创建${object.title}关联关系
	<#list mainObject.objectRels as objectRel>
		<#if objectRel.relClassName == object.className>
			<#if objectRel.relType == 'ONETOMANY'>
		if(${mainObject.objectName}.get${object.className}List()!=null &&${mainObject.objectName}.get${object.className}List().size()>0){
			for(${object.className} ${object.objectName} :${mainObject.objectName}.get${object.className}List()){
				<#list mainObject.objectRels as objectRel>
					<#if object.className == objectRel.relClassName>
						<#list objectRel.relObject.properties as property>
							<#if property.colName == objectRel.relProperty>
				${object.objectName}.set${property.name?cap_first}(${objectRel.relToClassName?uncap_first}.get<#list objectRel.relToObject.properties as property><#if property.colName == objectRel.relToProperty>${property.name?cap_first}());</#if></#list>
							</#if>
						</#list>
				${object.objectName}.setId(UUIDUtil.uuid());
				${object.objectName}.setCreateDate(new Date());
				${object.objectName}.setModifyDate(new Date());
				${object.objectName}.setCompanyId(${mainObject.objectName}.getCompanyId());
	            ${object.objectName}.setCreateUserid(${mainObject.objectName}.getModifyUserid());
	            ${object.objectName}.setModifyUserid(${mainObject.objectName}.getModifyUserid());
				${object.objectName}.setCreateDeptid(${mainObject.objectName}.getModifyDeptid());
				${object.objectName}.setModifyDeptid(${mainObject.objectName}.getModifyDeptid());
					</#if>
				</#list>
			}
			${object.objectName}Dao.insertBatch(${mainObject.objectName}.get${object.className}List());
		}
			<#else>
		if(${mainObject.objectName}.get${object.className}()!=null){
				<#list mainObject.objectRels as objectRel>
					<#if object.className == objectRel.relClassName>
						<#list objectRel.relObject.properties as property>
							<#if property.colName == objectRel.relProperty>
			${mainObject.objectName}.get${object.className}().set${property.name?cap_first}(${objectRel.relToClassName?uncap_first}.get<#list objectRel.relToObject.properties as property><#if property.colName == objectRel.relToProperty>${property.name?cap_first}());</#if></#list>
							</#if>
						</#list>
			${mainObject.objectName}.get${object.className}().setId(UUIDUtil.uuid());
			${mainObject.objectName}.get${object.className}().setCompanyId(${mainObject.objectName}.getCompanyId());
            ${mainObject.objectName}.get${object.className}().setCreateUserid(${mainObject.objectName}.getModifyUserid());
            ${mainObject.objectName}.get${object.className}().setModifyUserid(${mainObject.objectName}.getModifyUserid());
			${mainObject.objectName}.get${object.className}().setCreateDeptid(${mainObject.objectName}.getModifyDeptid());
			${mainObject.objectName}.get${object.className}().setModifyDeptid(${mainObject.objectName}.getModifyDeptid());
			${mainObject.objectName}.get${object.className}().setCreateDate(new Date());
			${mainObject.objectName}.get${object.className}().setModifyDate(new Date());
					</#if>
				</#list>
			${object.objectName}Dao.insert(${mainObject.objectName}.get${object.className}());
		}	
			</#if>
		</#if>
	</#list>
		//创建${object.title}关联关系结束
</#list>
</#if>
	