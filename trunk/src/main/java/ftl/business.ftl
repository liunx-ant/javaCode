
var ${mainObject.objectName}={
	url:{
		//页面路径
		homePage:"/${mainObject.objectName}/home",
		newPage:"/${mainObject.objectName}/new",
		editPage:"/${mainObject.objectName}/edit/",
		viewPage:"/${mainObject.objectName}/view/",
		//数据请求路径
		list:"/${mainObject.objectName}/list/",
		getByInfo:"/${mainObject.objectName}/getByInfo",
		add:"/${mainObject.objectName}/add",
		update:"/${mainObject.objectName}/update",
		del:"/${mainObject.objectName}/del/"
	},
	list:function(data,callback){
		ajaxService.postAjax(this.url.list+data.pageNum+"/"+data.pageSize,data.params,function(data_){
			if(callback){
				callback(data_);
			}
		});
	},
	getByInfo:function(data,callback){
		ajaxService.postAjax(this.url.getByInfo,data,function(data_){
			if(data_.result==resultMesage.sucess){
				var msg=(data_.data);
<#list mainObject.properties as property>
	<#if property.propertyView.isExist || property.propertyEdit.isExist>
				$("[name=${property.name}]").val(msg.${property.name}<#if property.type =='Date'>Str</#if>);
		<#if property.propertyView.showType=='ORGTREE' || property.propertyView.showType=='ORG'>
				$("#${property.name}").val(queryOrgInfoById(msg.${property.name}));
		</#if>
		<#if property.propertyView.showType=='USERTREE' || property.propertyView.showType=='USER'>
				$("#${property.name}").val(queryUserInfoById(msg.${property.name}));
		</#if>
	</#if>
</#list>
<#list relObjects as object>
		<#assign show="false"/>
		<#list object.properties as property>
			<#if property.propertyView.isExist>
				<#assign show="true"/>
			</#if>
		</#list>
		<#if show=='true'>
				//如果关联表是单条数据
				if(msg.${object.objectName}){
	<#list object.properties as property>
		<#if property.propertyView.isExist || property.propertyEdit.isExist>
					$("[name='${object.objectName}.${property.name}']").val(msg.${object.objectName}.${property.name}<#if property.type =='Date'>Str</#if>);
			<#if property.propertyView.showType=='ORGTREE'>
					$("#${object.objectName}${property.name}").val(queryOrgInfoById(msg.${object.objectName}.${property.name}}));
			</#if>
			<#if property.propertyView.showType=='USERTREE' >
					$("#${object.objectName}${property.name}}").val(queryUserInfoById(msg.${object.objectName}.${property.name}));
			</#if>
		</#if>
	</#list>
				}else if (msg.${object.objectName}List){//关联多条数据,载入模板
						//数据填充
					$('#${object.objectName}Table').setTemplateElement("${mainObject.objectName}${object.objectName}TableTemplateTxt");
					$('#${object.objectName}Table').processTemplate(msg.${object.objectName}List);
					checkEvent();
				}
		</#if>
			
</#list>
				callback(data_);
			}else{
				//返回错误时提示
			}
		});
	},
	add:function(data,callback){
		ajaxService.postAjax(this.url.add,data,function(data_){
			if(callback){
				callback(data_);
			}
		});
	},
	update:function(data,callback){
		ajaxService.postAjax(this.url.update,data,function(data_){
			if(callback){
				callback(data_);
			}
		});
	},
	del:function(id,callback){
		ajaxService.getAjax(this.url.del+id,null,function(data_){
			if(callback){
				callback(data_);
			}
		});
	}
};

/**
 * 删除数据行
 */
function del${mainObject.className}Row(obj){
	delFromListOneRow(obj,1);
}
