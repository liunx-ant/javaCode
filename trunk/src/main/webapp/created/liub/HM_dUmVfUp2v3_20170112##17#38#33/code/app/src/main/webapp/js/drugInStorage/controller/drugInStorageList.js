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
			$('#drugInStorageTable').setTemplateElement("drugInStorageTableTemplateTxt");
			$('#drugInStorageTable').processTemplate((data_.data));
		 	    $('#drugInStoragePage').setTemplateElement("pageTemplate");
				$('#drugInStoragePage').processTemplate((data_.data));
				pageBind("drugInStoragePage",pb,function(){drugInStorage.list(pb,listCallback);});
			checkEvent();
		}else{
			//返回错误时提示
		}
	}
	drugInStorage.list(pb,listCallback);
	
	$("#searchDrugInStorage").bind("click",function(){
		pb.pageNum=1;
		drugInStorage.list(pb,listCallback);
	});
	
	$("#newDrugInStorage").bind("click",function(){
		location.href=drugInStorage.url.newPage;
	});
	
	$("#editDrugInStorage").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugInStorage.url.editPage+id;
		}
	});
	
	$("#viewDrugInStorage").bind("click",function(){
		var checkNum=0;
		var id="";
		$("tbody :checked").each(function(i,item){
				checkNum++;
				id=item.value;
		});
		if(checkNum !=1){
			layer.msg("请选择一条记录",{icon:7});
		}else{
			location.href=drugInStorage.url.viewPage+id;
		}
	});
	
	$("#delDrugInStorage").bind("click",function(){
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
					drugInStorage.list(pb,listCallback);
					layer.close(index);
				});
			}
		}
		drugInStorage.del(id.substring(0,id.length-1),delCallback);
	});
});