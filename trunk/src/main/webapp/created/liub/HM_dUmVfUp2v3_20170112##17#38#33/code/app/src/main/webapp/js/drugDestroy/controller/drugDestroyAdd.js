$(function(){
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugDestroy.url.homePage;
			});
		}
	};
	$("#addDrugDestroy").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugDestroy.add($("form").serializeObject(),callback);
	});
	
	$("#homeDrugDestroy").bind("click",function(){
		location.href=drugDestroy.url.homePage;
	});
});