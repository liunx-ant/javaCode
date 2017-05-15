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
			$('#drugBasicTable').setTemplateElement("drugBasicTableTemplateTxt");
			$('#drugBasicTable').processTemplate((data_.data));
		 	    $('#drugBasicPage').setTemplateElement("pageTemplate");
				$('#drugBasicPage').processTemplate((data_.data));
				pageBind("drugBasicPage",pb,function(){drugBasic.list(pb,listCallback);});
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
	drugBasic.list(pb,listCallback);
	
	$("#searchDrugBasic").bind("click",function(){
		pb.pageNum=1;
		pb.params.drugType=$("[name='drugType']").val();
		pb.params.drugName=$("[name='drugName']").val();
		pb.params.drugUnit=$("[name='drugUnit']").val();
		pb.params.drugModel=$("[name='drugModel']").val();
		pb.params.drugNumber=$("[name='drugNumber']").val();
		pb.params.status=$("[name='status']").val();
		drugBasic.list(pb,listCallback);
	});
	
	$("#newDrugBasic").bind("click",function(){
		location.href=drugBasic.url.newPage;
	});
	
	$("#editDrugBasic").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugBasic.url.editPage+id;
		}
	});
	
	$("#viewDrugBasic").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugBasic.url.viewPage+id;
		}
	});
	
	$("#delDrugBasic").bind("click",function(){
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
					drugBasic.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		drugBasic.del(id.substring(0,id.length-1),delCallback);
	});
});