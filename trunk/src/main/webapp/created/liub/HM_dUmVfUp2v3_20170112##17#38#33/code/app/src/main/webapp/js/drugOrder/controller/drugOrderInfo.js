$(function(){
	disabledFunction();
	drugOrder.getByInfo({id:$("#id").val()},disabledFunction);
	
	$("#homeDrugOrder").bind("click",function(){
		location.href=drugOrder.url.homePage;
	});
});