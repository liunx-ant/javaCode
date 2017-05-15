$(function(){
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugReception.url.homePage;
			});
		}
	};
	$("#addDrugReception").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugReception.add($("form").serializeObject(),callback);
	});
	
	$("#homeDrugReception").bind("click",function(){
		location.href=drugReception.url.homePage;
	});
});