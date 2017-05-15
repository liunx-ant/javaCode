$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  

/*
 * 返回结果
 */
var resultMesage={
		sucess:"1",
		sucessMsg:"操作成功"
};

/*
 * 默认function
 */
var defaultFunction=function(data){};
/*
 * 不可操作
 */
var disabledFunction=function(data){
	$("input[type=text]").attr("readonly",true);
	$("input[onclick='WdatePicker()']").attr("readonly",false).attr("disabled",true);
	$("select").attr("disabled",true);
	$('form  textarea').attr("disabled",true);
};
/*
 * 默认selected
 */
var defaultSelected='<option value="">请选择</option>';
/**
 * 通用ajax服务
 */
var ajaxService={
	url:{
		dic:"/dic/",//字典
		queryUserInfoById:"/queryUserInfoById/",//获取用户信息
		queryOrgInfoById:"/queryOrgInfoById/"//获取组织信息
	},
	getAjax:function(url,data,callback,async){
		$.ajax({
			type:"GET",
			url: url,
			async:async,
			cache:false,
			data: data, 
			dataType: "JSON",
			success: callback,
			error:function(data){
				layer.msg("服务器异常",{icon:2});
			}
		});
	},
	postAjax:function(url,data,callback,async){
		 openShade();
		 $.ajax({
             type:"POST",
             url: url,
             async:async,
             data: JSON.stringify(data), 
             dataType: "JSON",
             contentType:"application/json;charset=UTF-8",
             success: function(data){
            	 closeShade();
            	 callback(data);
             },
             error:function(data){
            	 closeShade();
            	 layer.msg("服务器异常",{icon:2});
             }
         });
	 }
};

//分页事件
function pageBind(idStart,model, method) {
	if(idStart){
		idStart="#"+idStart+" ";
	}
    /**
	 * 选择页码
	 */
	$(idStart+"#selectPageSize").bind("change",function(){
		model.pageNum=1;
		model.pageSize=$(idStart+"#selectPageSize").val();
		method();
    });
	
	/**
	 * 跳转页面
	 */
	$(idStart+"#jumpPage").bind("click",function(){
		model.pageNum=$(idStart+"#pageNum").val();
		method();
	});	
	
	/**
	 * 首页
	 */
	$(idStart+"#startPage").bind("click",function(){
		model.pageNum=1;
		method();
	});	
	/**
	 * 上一页
	 */
	$(idStart+"#prePage").bind("click",function(){
		if($(idStart+"#pageNum").val()!=1){
			model.pageNum=Number($(idStart+"#pageNum").val())-1;
			method();
		}
	});	
	/**
	 * 下一页
	 */
	$(idStart+"#nextPage").bind("click",function(){
		if($(idStart+"#pageNum").val()!=$(idStart+"#jumpPage").attr("totalpageattr")){
			model.pageNum=Number($(idStart+"#pageNum").val())+1;
			method();
		}
	});	
	/**
	 * 尾页
	 */
	$(idStart+"#endPage").bind("click",function(){
		model.pageNum=$(idStart+"#jumpPage").attr("totalpageattr");
		method();
	});	
	
	
   /**
	 * 页面数据校验
	 */
	$(idStart+"#pageNum").bind("blur",function(){
		var flag=true;
		var $page = $(idStart+"#pageNum");
		var pageNo = parseInt($(idStart+"#pageNum").val());
		var hideCurPage=$page.attr("hide");
		if (isNaN(pageNo)) {
			pageNo = hideCurPage;
			$page.val(pageNo);
			flag=false;
		}else{
			if(pageNo==$page.val()){
				flag=true;
			}else{
				$page.val(hideCurPage);
				flag=false;
			}
		}
			
		var totalPageNo = $(idStart+"#jumpPage").attr("totalPageAttr");
		if (pageNo <= 0){
			pageNo = hideCurPage;
			$page.val(pageNo);
			flag=false;
		}
			
		if (pageNo > totalPageNo){
			$page.val(hideCurPage);
			flag=false;
		}
		if(!flag)	{
			layer.msg("请输入正确页数",{icon:7});
		}
	});
}


/*
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

/**
 * 获取字典信息
 * @param type
 */
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
			var d=(data.data);
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
			var d=(data.data);
			if(d){
				orgMap[id]=d.displayName;
				name=orgMap[id];
			}
		},false);
	}
	return name;
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
	//全选
//	$("#checkAll").bind("click",function(){
//		$("tbody :checkbox").each(function(i,item){
//				$(item)[0].checked=$("#checkAll")[0].checked;
//		});
//	});
//	$("[name=check]").bind("click",function(){
//			$("#checkAll")[0].checked=($("tbody :checkbox").length==$("tbody :checked").length);
//	});
	//单选
	$("#checkAll").hide();
	$("[name=check]").bind("click",function(){
		var id=$(this).val();
		$("tbody :checked").each(function(i,item){
			if($(item).val()!=id){
				$(item)[0].checked=false;
			}
		});
	});
	
	
}

$(function(){
	
	$.validator.addMethod("numVali", function(value, element) {  
		var num=$(element).attr("numvali").split("+");
		var tel = new RegExp('^(\\d{1,'+num[0]+'})(\\.\\d{1,'+num[1]+'})?$');
		return this.optional(element) || (tel.test(value));
	}, "请输入正确的数字");
	$.validator.addMethod("valVali", function(value, element) {  
		var tel = new RegExp("[`~!@#$%^&*+=|{}'\:;',\\[\\].<>/?~！@#￥%……&*+|{}【】‘；：\"”“’。，、？]");
		return this.optional(element) || (!tel.test(value));
	}, "请输入合法字符");
	//表单校验
	$("form").validate({
		onfocusout: function(element){
			if(!$(element).is("textarea")){
				$(element).valid();
			}
		},
		onkeyup:false,
		errorPlacement: function(error, element) {
			if(element.parent().is("td")){
				 if($("._valid").length>0){
					 return
				 }
				 $(".table-scroll").css("overflow","visible");
				 var _div=document.createElement("div");
				  // 修改提示内容text()里内容
				$(_div).addClass("_valid").text(error[0].innerText);
				$(_div).css({"top":"-63px","left":"-50px"});
				$(element).before($(_div)).parent().css("position", "relative");
				$(element).parent().css("overflow", "visible");
			}else{
				  var errorMsg='<div class="form-tips"><span class="text-red">'+error[0].outerHTML+'</span></div>';
				    $(element.parent()).append(errorMsg);
			}
		},
		success : function(label, element) {
			$(element).prev().remove();
			$(element).parent().attr("style", "");
		}
	});
	//清空
	$("#clean").bind("click",function(){
		$(':input','form') 
//		 .not(':hidden')
		 .val('')  
		 .removeAttr('checked')
		 .removeAttr('selected'); 
	});
});