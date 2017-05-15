$(function(){
	<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyView.isExist && property.dictType !=''>
	//初始化${property.dictType}
	$("[name=<#if ! object.isMain>${object.objectName}.</#if>${property.name}]").html(getSelectByKey("${property.dictType}"));
		</#if>
	</#list>
</#list>
	//回显数据
	${mainObject.objectName}.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=${mainObject.objectName}.url.homePage;
			});
		}
	};
	//修改数据
	$("#update${mainObject.className}").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		${mainObject.objectName}.update($("form").serializeObject(),callback);
	});
	
	$("#home${mainObject.className}").bind("click",function(){
		location.href=${mainObject.objectName}.url.homePage;
	});
	
});

