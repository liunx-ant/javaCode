$(function(){
	disabledFunction();
	drugInStorage.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#homeDrugInStorage").bind("click",function(){
		location.href=drugInStorage.url.homePage;
	});
});