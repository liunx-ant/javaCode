$(function(){
		//回显数据
	drugManager.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugManager.url.homePage;
			});
		}
	};
	//修改数据
	$("#updateDrugManager").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugManager.update($("form").serializeObject(),callback);
	});
	
	$("#homeDrugManager").bind("click",function(){
		location.href=drugManager.url.homePage;
	});
	
});

