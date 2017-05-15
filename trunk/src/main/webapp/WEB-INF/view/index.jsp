<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/jspf/common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>功能配置</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<!--子页面begin-->
<div class="iframe-cot pull-right">
	<div class="right-form mt0">
			<form action="getXML" method="post" onsubmit="return false">
<!-- 				<div class="crumbs"> -->
<!--                     <div class="crumbs-line"></div> -->
<!--                 </div> -->
  				<div class="form-top">
					<div class="pull-left">
						<input type="button" class="btn btn-default" onclick="javascript:location.href='config';" value="后台配置"></input>
             		</div>
           		</div>
				<div class="zk-tab mt15" id="drugManagerTable">
						<table class="table-form table-bordered table-hover table-ellipsis table-fixed" style="min-width: 1000px">
							<thead>
							<tr>
								<th width="5%"><input type="checkbox" id="checkAll"></th>
								<th><em class="text-red mr5">*</em>文件</th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${tables}" var="table">
									<tr> 
										<td>
											<input type="checkbox" name="fileNames" value="${table}" class="w85 "></input>
										</td>
										<td class="text-left">
											${table}
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<ul>
		                <li>
		                    <label class="ser-label">&nbsp;</label>
		                    <div class="form-content clearfix">
		                        <button class="btn btn-orange" src="getXML"><i class="fa fa-edit"></i>文件配置</button>
		                        <button class="btn btn-orange" src="createCode"><i class="fa fa-download"></i>代码下载</button>
		                    </div>
		                </li>
					</ul>	
			</form>
		</div>
	</div>				
    <!--子页面end-->
</body>
<script type="text/javascript">
$(function(){
	$("button").bind("click",function(){
		if($("tbody :checked").length==0){
			return false;			
		}
		$("form").attr("action",$(this).attr("src"));
		$("form").removeAttr("onsubmit");
		$("form").submit();
	});
	
//     $("tbody").delegate(":checkbox","click",function() {
//         $("#checkAll")[0].checked = ($("tbody :checkbox").length == $("tbody :checked").length);
//     });
    $("tbody").delegate(":checkbox","click",function() {
        $("#checkAll")[0].checked = ($("tbody :checkbox").length == $("tbody :checked").length);
    });
 	$("#checkAll").bind("click",function() {
        $("tbody :checkbox").each(function(i, item) {
            $(item)[0].checked = $("#checkAll")[0].checked;
        });
    });
});
</script>
</html>