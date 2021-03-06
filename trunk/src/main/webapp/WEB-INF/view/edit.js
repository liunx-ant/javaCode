$(function(){
	/**
	 * 弹出框中的下拉数据
	 */
	var collCode=function(){
		var classNames=$("[name$=className");
		if(classNames.length>1){
			classNames.each(function(i,item){
				//循环字段
				var tempProperties=[];
				$("#"+$(item).val()+"Table tbody tr").each(function(i,item){
					var propertySelect="<option value=\""+$(item).find("[name$=colName]").val()+"\">"+$(item).find("[name$=name]").val()+"</option>";
					tempProperties.push(propertySelect);
				});
				
				tempList.push({
					name:$(item).val(),
					parentName:$("[name$=isMain] option:selected[value=true]").parent().attr("name").replace(".isMain",""),//主表的值
					isMain:$("[name^='"+$(item).attr("name").replace("className","isMain")+"']").val(),
					properties:tempProperties
				});
				tableNames.push("<option value=\""+$(item).val()+"\">"+$(item).val()+"</option>");
			});
		}
	};
	var collType=["<option value=\"ONETOONE\">单条关联</option>","<option value=\"ONETOMANY\">多条关联</option>"];
	var tempList=[];
	var tableNames=[];
	//关联关系设置
	var collShow=function(){
		tableNames=[];
		tempList=[];
		collCode();
		var trs="";
		var relSum=0;
		var objRel="";
		$(tempList).each(function(i,item){
			if(item.isMain=='false'){
				trs+="<tr><td><input type=\"text\" readonly class=\"w85\" name=\""+item.parentName+".objectRels["+relSum+"].relClassName\" value="+item.name+"></td><td><select class=\"form-control-select\" name=\""+item.parentName+".objectRels["+relSum+"].relProperty\">"+item.properties+"</select></td><td><select class=\"form-control-select\" name=\""+item.parentName+".objectRels["+relSum+"].relToClassName\">"+tableNames+"</select></td><td><select class=\"form-control-select\" name=\""+item.parentName+".objectRels["+relSum+"].relToProperty\"></select></td><td><select class=\"form-control-select\" name=\""+item.parentName+".objectRels["+relSum+"].relType\">"+collType+"</select></td></tr>";
				relSum++;
			}else{
				objRel=$("#"+item.name+"ObjectRel");
			}
		});			
		if(!trs||trs.length==0){
			pageConfig.method(0);
			return;
		}
		var th = '<thead>' + '<tr>' + '<th width="20%">对象</th>' + '<th width="20%">属性</th>' + '<th width="20%">关联对象</th>' + '<th width="20%">关联对象的属性</th>' + '<th width="20%">关联方式</th>' + '</tr>' + '</thead>';
		var layerIndex=layerOpen("关联信息",getPageHtml("saveColl",th,trs));
		//表名变化,改对应的字段
		$("[name$=relToClassName]").bind("change",function(){
			var selectObj=$(this);
			$(tempList).each(function(i,item){
				if(item.name==selectObj.val()){
					selectObj.parent().next().find("select").html(item.properties);
				}
			});
		});
		//保存
		$("#saveColl").bind("click",function(){
			var relArray=($(this).parent().parent().next().serializeArray());
			objRel.html("<tr></tr>");
			$(relArray).each(function(i,item){
				//数据存在第一个table的objectRel了,没有放在主表下面,实际生成xml不受影响
				objRel.find("tr").append('<td><input type="text" name="'+item.name+'" value="'+item.value+'"> </td>');
			});
			layer.close(layerIndex);
			ajaxService.postAjax("/saveXML",$("form").serialize(),function(data){
				if(data.result=='sucess'){
					pageConfig.method(0);
				}
			});
		});
		$("[name$=relToClassName]").change();
		
		objRel.find(":input").each(function(i,item){
			$("[name='"+$(item).attr("name")+"']").val($(item).val());
			$("[name='"+$(item).attr("name")+"']").change();
		});
	};
	/**
	 * 获取弹窗格式
	 */
	function getPageHtml(id,thead,trs){
		return  '<div class="table-scroll">' + '<div class="mb10" style="overflow:hidden;">' + '<span class="pull-left">' + '<button class="btn btn-default"  id="' + id + '"><i class="fa fa-check mr5"></i>确认</button>' + '</span>' + '</div>' + '<form><table class="table-form table-bordered">' + thead + '<tbody>' + trs + '</tbody>' + '</table></form>' + '</div>'; 
	};
	/**
	 * 弹窗
	 */
	var layerOpen=function(title,pageHtml,callbak){
		var layerIndex=layer.open({
			type: 1,
			title: title,
			maxmin: false,
			shadeClose: false, //点击遮罩关闭层
			area : ['1000px' , '550px'],
			content: pageHtml
		});
		if(callbak){
			callbak(layerIndex);
		}
		return layerIndex;
	};
	/**
	 * 页面配置绑定事件
	 */
	var pageBindEvent=function(opId,name,index,callback){
		$("#"+opId).bind("click",function(){
			var form=$(this).parent().parent().next();
			if(opId=='saveList' && form.find("[name$=showType]").find("option:selected[value='CHECKBOX']").length!=1){
				layer.msg("请设置一个显示方式为选择框",{icon:7});
				return;
			}
			var addPageList=form.serializeArray();
			$("[name="+name+"] :checkbox").each(function(i,item){
				$(item)[0].checked=false;
				$(item).removeAttr('checked');
			});
			$(addPageList).each(function(i,item){
				if($("[name='"+item.name+"']").attr("type")=='checkbox'){
					$("[name='"+item.name+"']").attr("checked",$("[name='"+item.name+"']")[1].checked);
				}else{
					$("[name='"+item.name+"']").val(item.value);
				}
			});
			layer.close(index);
			callback();
		});
	};
	
	$("#save").bind("click",function(){
		//主表判断
		if($("[name$=isMain] option:selected[value=true]").length==1){
			//关联关系设置
			collShow();
		}else{
			layer.msg((($("[name$=isMain] option:selected[value=true]").length>1)?"只能有一个主表":"请选择一个主表"),{icon:7});
		}
	});
	
	/**
	 * 页面配置对象
	 */
	var pageConfig={
			options:[{
				opId:"saveAdd",
				name:"addPage",
				th:'<thead>' + '<tr>' + '<th width="10%">显示</th>' + '<th width="20%">名称</th>' + '<th width="20%">显示方式</th>' + '<th width="10%">必填</th>' + '</tr>' + '</thead>',
				title:"添加页面配置信息"
			},{
				opId:"saveEdit",
				name:"editPage",
				th:'<thead>' + '<tr>' + '<th width="10%">显示</th>' + '<th width="20%">名称</th>' + '<th width="20%">显示方式</th>' + '<th width="10%">必填</th>' + '</tr>' + '</thead>',
				title:"编辑页面配置信息"
			},{
				opId:"saveView",
				name:"viewPage",
				th:'<thead>' + '<tr>' + '<th width="10%">显示</th>' + '<th width="20%">名称</th>' + '<th width="20%">显示方式</th>' + '</tr>' + '</thead>',
				title:"详情页面配置信息"
			},{
				opId:"saveQuery",
				name:"queryPage",
				th:'<thead>' + '<tr>' + '<th width="10%">显示</th>' + '<th width="20%">名称</th> ' + '<th width="20%">显示方式</th> ' + '<th width="10%">查询方式</th> ' + '</tr>' + '</thead>',
				title:"列表页面查询条件配置信息"
			},{
				opId:"saveList",
				name:"listPage",
				th:'<thead>' + '<tr>' + '<th width="10%">显示</th>' + '<th width="20%">名称</th> ' + '<th width="20%">显示方式</th> ' + '<th width="10%">排序方式</th> ' + '</tr>' + '</thead>',
				title:"列表页面显示配置信息"
			}],
			method:function(index){
				var opId=this.options[index].opId;
				var trs="";
				$("[name="+this.options[index].name+"]").each(function(i,item){
					trs+="<tr>"+$(item).html().replace("/tad","</td><td>"+$(item).parent().find("[name$=title]").val()+"</td>").replace("/tad","</td>").replace("/tad","</td>").replace("/tad","</td>").replace("/tad","</td>").replace("tad","<td>").replace("tad","<td>").replace("tad","<td>").replace("tad","<td>").replace("tad","<td>")+"</tr>";
				});
				var th = this.options[index].th;
				layerOpen(this.options[index].title,getPageHtml(opId,th,trs),function(layerIndex){
					pageBindEvent(opId,pageConfig.options[index].name,layerIndex,function(){
						if(index==pageConfig.options.length-1){
							ajaxService.postAjax("/saveXML",$("form").serialize(),function(data){
								if(data.result=='sucess'){
									window.location="/list";
								}
							});
						}else{
							pageConfig.method(index+1);
						}
					});
				});
			}
	};
});

