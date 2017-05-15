
var drugReception={
	url:{
		//页面路径
		homePage:"/drugReception/home",
		newPage:"/drugReception/new",
		editPage:"/drugReception/edit/",
		viewPage:"/drugReception/view/",
		//数据请求路径
		list:"/drugReception/list/",
		getByInfo:"/drugReception/getByInfo",
		add:"/drugReception/add",
		update:"/drugReception/update",
		del:"/drugReception/del/"
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
				$("[name=receptionCode]").val(msg.receptionCode);
				$("[name=registrationTime]").val(msg.registrationTimeStr);
				$("[name=receptionDep]").val(msg.receptionDep);
				$("[name=receptionDepname]").val(msg.receptionDepname);
				$("[name=receptionUser]").val(msg.receptionUser);
				$("[name=receptionUsername]").val(msg.receptionUsername);
				$("[name=warehouse]").val(msg.warehouse);
				$("[name=allocation]").val(msg.allocation);
				$("[name=purpose]").val(msg.purpose);
				$("[name=approveStatus]").val(msg.approveStatus);
				$("[name=status]").val(msg.status);
				$("[name=deleteFlag]").val(msg.deleteFlag);
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
function delDrugReceptionRow(obj){
	delFromListOneRow(obj,1);
}