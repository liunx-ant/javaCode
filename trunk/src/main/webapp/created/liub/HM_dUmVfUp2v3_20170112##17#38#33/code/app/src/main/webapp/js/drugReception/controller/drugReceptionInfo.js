$(function(){
	disabledFunction();
	drugReception.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#homeDrugReception").bind("click",function(){
		location.href=drugReception.url.homePage;
	});
});