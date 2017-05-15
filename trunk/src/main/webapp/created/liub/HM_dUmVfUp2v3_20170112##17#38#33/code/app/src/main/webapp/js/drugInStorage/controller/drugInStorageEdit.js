$(function(){
		//回显数据
	drugInStorage.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugInStorage.url.homePage;
			});
		}
	};
	//修改数据
	$("#updateDrugInStorage").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugInStorage.update($("form").serializeObject(),callback);
	});
	
	$("#homeDrugInStorage").bind("click",function(){
		location.href=drugInStorage.url.homePage;
	});
	
});

