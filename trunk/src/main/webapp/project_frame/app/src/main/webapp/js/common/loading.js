var waitingDiv = "<div id='waiting' class='modal-2' style='display:none'>"
		+ "<div class='modal-2-bg'></div>" + "<div class='waite'></div>"
		+ "</div>";

var selfDisabled=[];   //自己页面 disabled 的按钮
/**
 * 打开遮罩，防止重复提交和数据修改
 */
function openShade() {
	if (document.getElementById("waiting") == undefined) {
		$("body").append(waitingDiv);
	}
	$("#waiting").show();
	$("input[type=submit]:disabled,input[type=button]:disabled,button:disabled").each(function(){
		selfDisabled.push(this);
	});
	$("input[type=submit],input[type=button],button").attr("disabled",true);
}
/**
 * 关闭遮罩
 */
function closeShade() {
	$("#waiting").hide();
	$("input[type=submit],input[type=button],button").not(selfDisabled).attr("disabled",false);
	selfDisabled=[];
}

function forbidRepeatValid(result) {
	openShade();
	if (typeof (result) != undefined && !result) {
		closeShade();
		return false;
	}
	return true;
}
