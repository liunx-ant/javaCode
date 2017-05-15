$(function(){
	disabledFunction();
	drugBasic.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#homeDrugBasic").bind("click",function(){
		location.href=drugBasic.url.homePage;
	});
});