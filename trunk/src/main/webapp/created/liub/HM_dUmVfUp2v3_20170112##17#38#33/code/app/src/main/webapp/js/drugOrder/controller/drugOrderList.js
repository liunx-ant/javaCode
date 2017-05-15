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
			$('#drugOrderTable').setTemplateElement("drugOrderTableTemplateTxt");
			$('#drugOrderTable').processTemplate((data_.data));
		 	    $('#drugOrderPage').setTemplateElement("pageTemplate");
				$('#drugOrderPage').processTemplate((data_.data));
				pageBind("drugOrderPage",pb,function(){drugOrder.list(pb,listCallback);});
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
	drugOrder.list(pb,listCallback);
	
	$("#searchDrugOrder").bind("click",function(){
		pb.pageNum=1;
		drugOrder.list(pb,listCallback);
	});
	
	$("#newDrugOrder").bind("click",function(){
		location.href=drugOrder.url.newPage;
	});
	
	$("#editDrugOrder").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugOrder.url.editPage+id;
		}
	});
	
	$("#viewDrugOrder").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugOrder.url.viewPage+id;
		}
	});
	
	$("#delDrugOrder").bind("click",function(){
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
					drugOrder.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		drugOrder.del(id.substring(0,id.length-1),delCallback);
	});
});