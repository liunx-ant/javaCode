$(function(){
	var callback = function(data_){
		if(data_.result==resultMesage.sucess){
			$('[name=id]').val((data_.data).id);
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugOrder.url.homePage;
			});
		}
	};
	$("#addDrugOrder").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugOrder.add($("form").serializeObject(),callback);
	});
	
	$("#homeDrugOrder").bind("click",function(){
		location.href=drugOrder.url.homePage;
	});
});