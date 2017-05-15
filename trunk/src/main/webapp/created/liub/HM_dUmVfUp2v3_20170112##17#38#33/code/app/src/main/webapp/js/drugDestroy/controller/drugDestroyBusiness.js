
var drugDestroy={
	url:{
		//页面路径
		homePage:"/drugDestroy/home",
		newPage:"/drugDestroy/new",
		editPage:"/drugDestroy/edit/",
		viewPage:"/drugDestroy/view/",
		//数据请求路径
		list:"/drugDestroy/list/",
		getByInfo:"/drugDestroy/getByInfo",
		add:"/drugDestroy/add",
		update:"/drugDestroy/update",
		del:"/drugDestroy/del/"
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
				$("[name=id]").val(msg.id);
				$("[name=destroyCode]").val(msg.destroyCode);
				$("[name=drugId]").val(msg.drugId);
				$("[name=destroyTime]").val(msg.destroyTimeStr);
				$("[name=destroyNumber]").val(msg.destroyNumber);
				$("[name=manufacturer]").val(msg.manufacturer);
				$("[name=source]").val(msg.source);
				$("[name=destroyType]").val(msg.destroyType);
				$("[name=compliance]").val(msg.compliance);
				$("[name=remark]").val(msg.remark);
				$("[name=approveStatus]").val(msg.approveStatus);
				$("[name=status]").val(msg.status);
				$("[name=deleteFlag]").val(msg.deleteFlag);
				$("[name=partIn]").val(msg.partIn);
				//如果关联表是单条数据
				if(msg.drugBasic){
					$("[name='drugBasic.drugType']").val(msg.drugBasic.drugType);
					$("[name='drugBasic.drugName']").val(msg.drugBasic.drugName);
					$("[name='drugBasic.drugUnit']").val(msg.drugBasic.drugUnit);
					$("[name='drugBasic.drugModel']").val(msg.drugBasic.drugModel);
				}else if (msg.drugBasicList){//关联多条数据,载入模板
						//数据填充
					$('#drugBasicTable').setTemplateElement("drugDestroydrugBasicTableTemplateTxt");
					$('#drugBasicTable').processTemplate(msg.drugBasicList);
					checkEvent();
				}
			
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
function delDrugDestroyRow(obj){
	delFromListOneRow(obj,1);
}
