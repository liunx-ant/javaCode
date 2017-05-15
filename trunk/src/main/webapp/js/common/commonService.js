var resultMesage={
		sucess:"success",
		sucessMsg:"操作成功"
};
var pb={
		pageSize:10,
		pageNum:1,
		list:[]
};
var defaultFunction=function(data){};
var disabledFunction=function(data){
	$("input[type=text]").attr("disabled",true);
	$("select").attr("disabled",true);
	$('form  textarea').attr("disabled",true);
};
var defaultSelected='<option value="">请选择</option>';
/**
 * 通用服务
 */
var ajaxService={
	url:{
		dic:"/dic/",
		queryUserInfoById:"/queryUserInfoById/",
		queryOrgInfoById:"/queryOrgInfoById/"
	},
	getAjax:function(url,data,callback,async){
		$.ajax({
			type:"GET",
			url: url,
			async:async,
			data: data, 
			dataType: "JSON",
			success: callback,
			error:function(data){
				layer.msg("服务器异常",{icon:2});
			}
		});
	},
	postAjax:function(url,data,callback,async){
		 $.ajax({
             type:"POST",
             url: url,
             async:async,
             data: data, 
             dataType: "JSON",
             success: callback,
             error:function(data){
            	 layer.msg("服务器异常",{icon:2});
             }
         });
	 },
	 ajaxAnnotation:function(url,data,callback,async){
		 $.ajax({
			 type:"POST",
			 url: url,
			 async:async,
             data: JSON.stringify(data), 
             dataType: "JSON",
             contentType:"application/json;charset=UTF-8",
			 success: callback,
			 error:function(data){
				 layer.msg("服务器异常",{icon:2});
			 }
		 });
	 }
};

/**
 * 字典变量
 */
var dicts={};

/**
 * 获取字典数据
 * @param type
 * getDict("SAMPLE_GRADE");
 */
function getDict(type){
	if(dicts[type]){
		return dicts[type];
	}else{
		return getDictByAjax(type);
	}

}


function getDictByAjax(type){
	var dictsTemp={};
	ajaxService.getAjax(ajaxService.url.dic+type,null,function(data_){
		dictsTemp=data_;
		dicts[type]=data_;
	},false);
	return dictsTemp;
}

/**
 * 根据type类型和value值获取name
 * 	console.log(getNameByKey("SAMPLE_GRADE","4"));
 */
function getNameByKey(type,value){
	var name="";
	var list=(getDict(type));
	if(list.length>0){
		$(list).each(function(i,item){
			if(item.value==value){
				name= item.name;
			}
		});
	}
	return name;
}

/**
 * 获取type对应的select
 * console.log(getSelectByKey("SAMPLE_GRADE"));
 * @param type
 * @returns
 */
function getSelectByKey(type){
	var list=(getDict(type));
	
	var select=[];
	select.push(defaultSelected);
	if(list.length>0){
		$(list).each(function(i,item){
			select.push('<option value="'+item.value+'">'+item.name+'</option>');
		});
	}
	return select;
}
/**
 * 获取type对应的返回对象
 * console.log(getModelByKey("SAMPLE_GRADE"));
 * @param type
 * @returns
 */
function getModelByKey(type){
	return getDict(type);
}
/**
 * 获取用户名称
 */
var userMap=[];
function queryUserInfoById(id){
	var name="";
	if(userMap[id]){
		name= userMap[id];
	}else{
		ajaxService.postAjax(ajaxService.url.queryUserInfoById+id, null,function(data){
			var d=$.parseJSON(data.data);
			if(d){
				userMap[id]=d.displayName;
				name=userMap[id];
			}
		},false);
	}
	return name;
}
/**
 * 获取组织名称
 */
var orgMap=[];
function queryOrgInfoById(id){
	var name="";
	if(orgMap[id]){
		name= orgMap[id];
	}else{
		ajaxService.postAjax(ajaxService.url.queryOrgInfoById+id, null,function(data){
			var d=$.parseJSON(data.data);
			if(d){
				orgMap[id]=d.displayName;
				name=orgMap[id];
			}
		},false);
	}
	return name;
}

/**
 * 去除data中间的空值
 * @param data
 */
function toNullValue(data){
	for(d in data){
		var isObj=(Object.prototype.toString.call(data[d])=='[object Object]');
		var isArray=(Object.prototype.toString.call(data[d])=='[object Array]');
		if(isObj){
			toNullValue(data[d]);
		} 
		if(isArray){
			$(data[d]).each(function(i,item){
				toNullValue(item);
			});
		}
		if(isArray||isObj){
			var flag=true;
			for(t in data[d]){
				flag=false;
			}
			if(flag){
				delete data[d];
			}
		}
		if(!data[d]){
			delete data[d];
		}
	}
}

/**
 * 删除行
 * @param obj
 */
function delFromListOneRow(obj,minRowCount){
	if(!minRowCount){minRowCount=0;}
	obj=$(obj);
	if(obj.parent().parent().parent().children("tr").length>minRowCount){
		obj.parent().parent().remove();
	}else{
		layer.msg("最少保留"+minRowCount+"条数据",{icon:7});
	}
}
/**
 * 全选
 */
function checkEvent(){
	$("#checkAll").bind("click",function(){
		$("tbody :checkbox").each(function(i,item){
				$(item)[0].checked=$("#checkAll")[0].checked;
		});
	});
	$("[name=check]").bind("click",function(){
			$("#checkAll")[0].checked=($("tbody :checkbox").length==$("tbody :checked").length);
	});
}

$(function(){
	$("form").validate({
		onfocusout: function(element){
			$(element).valid();
		},
		errorPlacement: function(error, element) {
		    var errorMsg='<div class="form-tips"><span class="text-red">'+error[0].outerHTML+'</span></div>';
		    $(element.parent()).append(errorMsg);
		}
	});
	
	$("#clean").bind("click",function(){
		$(':input','form') 
//		 .not(':hidden')
		 .val('')  
		 .removeAttr('checked')
		 .removeAttr('selected'); 
	});
});