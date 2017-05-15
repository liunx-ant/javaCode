$(function(){
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugInStorage.url.homePage;
			});
		}
	};
	$("#addDrugInStorage").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugInStorage.add($("form").serializeObject(),callback);
	});
	
	$("#homeDrugInStorage").bind("click",function(){
		location.href=drugInStorage.url.homePage;
	});
});