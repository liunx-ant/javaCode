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
			$('#drugReceptionTable').setTemplateElement("drugReceptionTableTemplateTxt");
			$('#drugReceptionTable').processTemplate((data_.data));
		 	    $('#drugReceptionPage').setTemplateElement("pageTemplate");
				$('#drugReceptionPage').processTemplate((data_.data));
				pageBind("drugReceptionPage",pb,function(){drugReception.list(pb,listCallback);});
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
	drugReception.list(pb,listCallback);
	
	$("#searchDrugReception").bind("click",function(){
		pb.pageNum=1;
		drugReception.list(pb,listCallback);
	});
	
	$("#newDrugReception").bind("click",function(){
		location.href=drugReception.url.newPage;
	});
	
	$("#editDrugReception").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugReception.url.editPage+id;
		}
	});
	
	$("#viewDrugReception").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugReception.url.viewPage+id;
		}
	});
	
	$("#delDrugReception").bind("click",function(){
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
					drugReception.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		drugReception.del(id.substring(0,id.length-1),delCallback);
	});
});