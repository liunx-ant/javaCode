$(function(){
		//回显数据
	drugBasic.getByInfo({id:$("#id").val()},defaultFunction);


	var callback=function(data_){
		if(data_.result==resultMesage.sucess){
			layer.alert(resultMesage.sucessMsg,function(){
				location.href=drugBasic.url.homePage;
			});
		}
	};
	//修改数据
	$("#updateDrugBasic").bind("click",function(){
		if(!$("form").valid()){
			return;
		}
		drugBasic.update($("form").serializeObject(),callback);
	});
	
	$("#homeDrugBasic").bind("click",function(){
		location.href=drugBasic.url.homePage;
	});
	
});

