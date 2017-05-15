//根据orgId获取下面用户
var userTreeUrl = "/userTreeNode";
//初始化树
var orgTreeUrl = "/orgTreeNode";

var treeName={
		orgTree:"ORGTREE",
		userTree:"USERTREE"
};
//ztree 设置
var setting = {
		async: {
			enable: true,
			dataFilter: filter,
			type: "get"
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check: {
			enable: true,
			chkStyle: "radio",
			radioType : "all"
		},
		callback: {
			beforeAsync: beforeAsync,
			onAsyncSuccess: onAsyncSuccess,
			onAsyncError: onAsyncError,				
			onClick: onClick,
			onCheck: onCheck
		}
	};
	//单击radio时
	function onClick(e, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		if(treeObj.setting.treeName==treeName.orgTree){
			v = treeNode.name;
			id= treeNode.id;
			setReult("#"+treeId,id,v);
		}
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	//选中radio时
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		nodes = zTree.getCheckedNodes(true);
		if(nodes&&nodes.length>0){
			v = nodes[0].name;
			id= nodes[0].id;
			setReult("#"+treeId,id,v);
		}else{
			v = "";
			id= "";
			setReult("#"+treeId,id,v);
		}
	}
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		
		return childNodes;
	}

	function beforeAsync(treeId, treeNode) {
		//console.log("beforeAsync");
	}
	//请求成功时
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		//当前节点无子节点时
		if(treeNode.children.length==0){
			//设置无子节点属性
			treeNode.isParent=false;
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			//更新样式
			zTree.updateNode(treeNode);
		}
		
	}

	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		//console.log(errorThrown);
	}

	function toZNodes(list,name){
		var zNodes=new Array();
		$(list).each(function (index, treeNode) {
			if(name==treeName.orgTree){
				var isParent=false;
				$(list).each(function(i,item){
					if(item.parentId==treeNode.id){
						isParent=true;
					}
				});
			}else{
				isParent=true;
			}
			var node={id:treeNode.id,pId:treeNode.parentId,name:treeNode.name,isParent:isParent,nocheck:1,open:treeNode.open};
			zNodes.push(node);
		});
		return zNodes;
	}
	function initOrgTree(treeId,name){
		//设置位置
		$("#"+treeId).parent().addClass("ztreeDivParent");
		$("#"+treeId).addClass("ztreeDiv");
		//参数设置
		var orgSetting = {
				async: {
					enable: true,
					url:userTreeUrl,
					autoParam:["id=orgId"]/*请求参数设置*/,
					dataFilter: filter,
					type: "get"
				},
				treeName:name
		};
		//设置参数
		orgSetting=$.extend(setting,orgSetting );
		//获取组织数据
		var resultData=getOrgData();
		var zNodes=toZNodes(resultData,name);
		//初始化组织树
		$.fn.zTree.init($("#"+treeId), orgSetting, $(zNodes).toArray());
	}
	var orgData;
	function getOrgData(){
		if(!orgData){
			$.ajax({
				type : "POST",
				url : orgTreeUrl,
				async:false,
				success : function(resultData) {
					//console.log(resultData)
					orgData=resultData;
				},
				error :function(err) {
					console.log(err);
				}
			});
		}
		return (orgData);
	}
	
	/**
	* 接收返回结果，需要根据自己需要实现
	**/
	function setReult(treeId,id,value){
			$(treeId).prev().prev().val(value);
			$(treeId).prev().val(id);
			//$(treeId).fadeOut("fast");
			hideMenu(treeId);
	}
	/**
 	 * ztree 下拉框专用
 	 */
 	//显示
 	function showMenu(treeId) {
 		    var display=$("#"+treeId).css('display');
 		    if(display!="none"){
 		    	return;
 		    }
 			//调整位置
	 		$("#"+treeId).css("left",$("#"+treeId).prev().prev().position().left);
			//设置显示
 			$("#"+treeId).slideDown("fast");
 	 		var _height=$(".right-form").height();
 			$(".right-form").height(_height).css("overflow","visible");
 	 		$("body").bind("mousedown", function(event){
 	 			onBodyDown(event,$("#"+treeId).prev().prev().attr("id"),"#"+treeId);
 	 		});
 	}
 	//隐藏
 	function hideMenu(tree) {
 		$(tree).fadeOut("fast");
 		$("body").unbind("mousedown");
 		$(".right-form").attr("style","");
 	}
 	function onBodyDown(event,id,tree) {
 		if(!(event.target.id == "menuBtn"||event.target.id == id||event.target.id == tree || $(event.target).parents(tree).length>0)){
 			hideMenu(tree);
 		}
 	}
 	/*********************end**************************/
	