$(function(){
	disabledFunction();
	drugManager.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#homeDrugManager").bind("click",function(){
		location.href=drugManager.url.homePage;
	});
});