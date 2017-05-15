$(function(){
		//回显数据
	drugOrder.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugOrder.url.homePage;
			});
		}
	};
	//修改数据
	$("#updateDrugOrder").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugOrder.update($("form").serializeObject(),callback);
	});
	
	$("#homeDrugOrder").bind("click",function(){
		location.href=drugOrder.url.homePage;
	});
	
});

