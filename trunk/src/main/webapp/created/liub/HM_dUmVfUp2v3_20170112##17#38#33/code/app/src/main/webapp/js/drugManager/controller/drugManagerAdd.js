$(function(){
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugManager.url.homePage;
			});
		}
	};
	$("#addDrugManager").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugManager.add($("form").serializeObject(),callback);
	});
	
	$("#homeDrugManager").bind("click",function(){
		location.href=drugManager.url.homePage;
	});
});