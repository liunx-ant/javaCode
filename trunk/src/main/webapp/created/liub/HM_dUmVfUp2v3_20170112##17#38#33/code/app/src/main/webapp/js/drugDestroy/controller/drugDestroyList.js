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
			$('#drugDestroyTable').setTemplateElement("drugDestroyTableTemplateTxt");
			$('#drugDestroyTable').processTemplate((data_.data));
		 	    $('#drugDestroyPage').setTemplateElement("pageTemplate");
				$('#drugDestroyPage').processTemplate((data_.data));
				pageBind("drugDestroyPage",pb,function(){drugDestroy.list(pb,listCallback);});
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
	drugDestroy.list(pb,listCallback);
	
	$("#searchDrugDestroy").bind("click",function(){
		pb.pageNum=1;
		pb.params.drugBasic={};
		pb.params.drugBasic.drugType=$("[name='drugBasic.drugType']").val();
		pb.params.drugBasic.drugName=$("[name='drugBasic.drugName']").val();
		pb.params.drugBasic.drugUnit=$("[name='drugBasic.drugUnit']").val();
		pb.params.drugBasic.drugModel=$("[name='drugBasic.drugModel']").val();
		drugDestroy.list(pb,listCallback);
	});
	
	$("#newDrugDestroy").bind("click",function(){
		location.href=drugDestroy.url.newPage;
	});
	
	$("#editDrugDestroy").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugDestroy.url.editPage+id;
		}
	});
	
	$("#viewDrugDestroy").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugDestroy.url.viewPage+id;
		}
	});
	
	$("#delDrugDestroy").bind("click",function(){
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
					drugDestroy.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		drugDestroy.del(id.substring(0,id.length-1),delCallback);
	});
});