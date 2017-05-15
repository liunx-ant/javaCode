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
			$('#drugManagerTable').setTemplateElement("drugManagerTableTemplateTxt");
			$('#drugManagerTable').processTemplate((data_.data));
		 	    $('#drugManagerPage').setTemplateElement("pageTemplate");
				$('#drugManagerPage').processTemplate((data_.data));
				pageBind("drugManagerPage",pb,function(){drugManager.list(pb,listCallback);});
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
	drugManager.list(pb,listCallback);
	
	$("#searchDrugManager").bind("click",function(){
		pb.pageNum=1;
		drugManager.list(pb,listCallback);
	});
	
	$("#newDrugManager").bind("click",function(){
		location.href=drugManager.url.newPage;
	});
	
	$("#editDrugManager").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugManager.url.editPage+id;
		}
	});
	
	$("#viewDrugManager").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugManager.url.viewPage+id;
		}
	});
	
	$("#delDrugManager").bind("click",function(){
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
					drugManager.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		drugManager.del(id.substring(0,id.length-1),delCallback);
	});
});