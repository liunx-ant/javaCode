$(function(){
	//初始化组织树
	$("[treeName="+treeName.orgTree+"]").each(function(i,item){
		var treeId=$(item).attr("id");
		initOrgTree(treeId,treeName.orgTree);
		/**
		 * 绑定选择按钮
		 */
		$("#"+treeId).prev().prev().bind("click",function(){
			showMenu(treeId);
		});
	});
});	