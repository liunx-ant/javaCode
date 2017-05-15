${author}
${nowDate}
${mainObject.appPackageName}
<#list objects as object>
	<#list object.properties as property>
		property name:${property.name}
	</#list>
	<#list object.objectRels as objectRel>
		<#list objects as relObject>
			 <#if objectRel.relObjetClassName==relObject.className>
			  	if_objecClasstName:${objectRel.relObjetClassName}
			 </#if>
		</#list>
	</#list>
</#list> 


<#list mainObject.objectRels as objectRel>
		<#list objects as relObject>
			 <#if objectRel.relObjetClassName==relObject.className>
			  	if_objecClasstName:${objectRel.relObjetClassName}
			 </#if>
		</#list>
</#list>

<#include "/form/input.ftl"/>
