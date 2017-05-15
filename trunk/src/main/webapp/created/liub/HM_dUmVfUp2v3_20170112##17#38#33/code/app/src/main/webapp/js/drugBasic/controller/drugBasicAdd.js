$(function(){
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugBasic.url.homePage;
			});
		}
	};
	$("#addDrugBasic").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugBasic.add($("form").serializeObject(),callback);
	});
	
	$("#homeDrugBasic").bind("click",function(){
		location.href=drugBasic.url.homePage;
	});
});