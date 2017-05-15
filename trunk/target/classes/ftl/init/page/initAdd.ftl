$(function(){
<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyNew.isExist && property.dictType !=''>
	//初始化${property.dictType}
	$("[name=<#if ! object.isMain>${object.objectName}.</#if>${property.name}]").html(getSelectByKey("${property.dictType}"));
		</#if>
	</#list>
</#list>
<#list relObjects as object>
	<#list mainObject.objectRels as objectRel>
		<#if object.className == objectRel.relClassName && objectRel.relType =='ONETOMANY'>
	$('#${object.objectName}Table').setTemplateElement("${mainObject.objectName}${object.objectName}TableTemplateTxt");
	$('#${object.objectName}Table').processTemplate(null);
	var ${object.objectName}Num=0;
	var ${object.objectName}StrTd='<tr><td>'+(${object.objectName}Num+1)+'</td>'+
	<#list object.properties as property>
		<#if property.propertyNew.isExist>
			'<td class="text-left"><input type="text" name="${object.objectName}List['+${object.objectName}Num+'].${property.name}" class="w100 <#if property.propertyNew.isMust>required </#if>"></input></td>' +
		</#if>
	</#list>			
			'<td class="text-left"> <a href="javascript:void(0)" class="link-blue" onclick="del${mainObject.className}Row(this)"><i class="fa fa-times mr5"></i>删除</a></td></tr>';
	$('#${object.objectName}Table tbody').append(${object.objectName}StrTd);
		</#if>
	</#list>
</#list>
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=${mainObject.objectName}.url.homePage;
			});
		}
	};
	$("#add${mainObject.className}").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		${mainObject.objectName}.add($("form").serializeObject(),callback);
	});
	
	$("#home${mainObject.className}").bind("click",function(){
		location.href=${mainObject.objectName}.url.homePage;
	});
});