$(function(){
	disabledFunction();
	drugDestroy.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#homeDrugDestroy").bind("click",function(){
		location.href=drugDestroy.url.homePage;
	});
});