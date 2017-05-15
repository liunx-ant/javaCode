$(function(){
	/*
	 * 分页对象
	 */
	var pb={
			pageSize:10,
			pageNum:1,
			params:{}
	};
	
	var listCallback=function(data_){
		if(data_.result==resultMesage.sucess){
			//数据填充
			$('#${mainObject.objectName}Table').setTemplateElement("${mainObject.objectName}TableTemplateTxt");
			$('#${mainObject.objectName}Table').processTemplate((data_.data));
			<#include "pageVerify.ftl"/>
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyQuery.isExist && property.dictType !=''>
	//初始化${property.dictType}
	$("[name=<#if ! object.isMain>${object.objectName}.</#if>${property.name}]").html(getSelectByKey("${property.dictType}"));
		</#if>
	</#list>
</#list>
	${mainObject.objectName}.list(pb,listCallback);
	
	$("#search${mainObject.className}").bind("click",function(){
		pb.pageNum=1;
<#list objects as object>
	<#if ! object.isMain>
		pb.params.${object.objectName}={};
	</#if>
	<#list object.properties as property>
		<#if property.propertyQuery.isExist>
			<#if property.type =='Date'>
		pb.params.<#if ! object.isMain>${object.objectName}.</#if>${property.name}StartDate=$("[name='<#if ! object.isMain>${object.objectName}.</#if>${property.name}StartDate']").val();
		pb.params.<#if ! object.isMain>${object.objectName}.</#if>${property.name}EndDate=$("[name='<#if ! object.isMain>${object.objectName}.</#if>${property.name}EndDate']").val();
			</#if>
			<#if property.type !='Date'>
		pb.params.<#if ! object.isMain>${object.objectName}.</#if>${property.name}=$("[name='<#if ! object.isMain>${object.objectName}.</#if>${property.name}']").val();
			</#if>
		</#if>		
	</#list>
</#list>
		${mainObject.objectName}.list(pb,listCallback);
	});
	
	$("#new${mainObject.className}").bind("click",function(){
		location.href=${mainObject.objectName}.url.newPage;
	});
	
	$("#edit${mainObject.className}").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=${mainObject.objectName}.url.editPage+id;
		}
	});
	
	$("#view${mainObject.className}").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=${mainObject.objectName}.url.viewPage+id;
		}
	});
	
	$("#del${mainObject.className}").bind("click",function(){
		var id="";
		$("tbody :checked").each(function(i,item){
				id=id+item.value+",";
		});
		if(!id){
			layer.msg("请选择一条记录",{icon:7});
			return;
		}
		var delCallback=function(data_){
			if(data_.result==resultMesage.sucess){
				layer.alert(resultMesage.sucessMsg,function(index){
					${mainObject.objectName}.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		${mainObject.objectName}.del(id.substring(0,id.length-1),delCallback);
	});
});