$(function(){
	//初始化组织树
	$("[treeName="+treeName.userTree+"]").each(function(i,item){
		var treeId=$(item).attr("id");
		initOrgTree(treeId,treeName.userTree);
		/**
		 * 绑定选择按钮
		 */
		$("#"+treeId).prev().prev().bind("click",function(){
			showMenu(treeId);
		});
	});
});	