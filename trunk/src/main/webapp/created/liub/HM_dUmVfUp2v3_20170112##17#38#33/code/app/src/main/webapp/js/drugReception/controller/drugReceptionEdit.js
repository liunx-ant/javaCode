$(function(){
		//回显数据
	drugReception.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugReception.url.homePage;
			});
		}
	};
	//修改数据
	$("#updateDrugReception").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugReception.update($("form").serializeObject(),callback);
	});
	
	$("#homeDrugReception").bind("click",function(){
		location.href=drugReception.url.homePage;
	});
	
});

