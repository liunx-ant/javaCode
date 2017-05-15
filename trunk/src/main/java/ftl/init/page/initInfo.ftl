$(function(){
	disabledFunction();
<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyView.isExist && property.dictType !=''>
	//初始化${property.dictType}
	$("[name=<#if ! object.isMain>${object.objectName}.</#if>${property.name}]").html(getSelectByKey("${property.dictType}"));
		</#if>
	</#list>
</#list>
	${mainObject.objectName}.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#home${mainObject.className}").bind("click",function(){
		location.href=${mainObject.objectName}.url.homePage;
	});
});