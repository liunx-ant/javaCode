$(function(){
		//回显数据
	drugDestroy.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugDestroy.url.homePage;
			});
		}
	};
	//修改数据
	$("#updateDrugDestroy").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugDestroy.update($("form").serializeObject(),callback);
	});
	
	$("#homeDrugDestroy").bind("click",function(){
		location.href=drugDestroy.url.homePage;
	});
	
});

