<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="drugBasicTemplate.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>药剂基本信息</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugBasic/controller/drugBasicBusiness.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugBasic/controller/drugBasicInfo.js"></script>
</head>
<body>
	<!--子页面begin-->
<div class="iframe-cot pull-right">
		<div class="right-form mt0">
			<form onsubmit="return false;">
				<div class="crumbs">
					<div class="crumbs-line"></div>
				</div>
				<ul class="w100">	

					<li >
					    <label class="ser-label">药剂类别：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=20 name="drugType" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">药剂名称：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=20 name="drugName" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">计量单位：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=20 name="drugUnit" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">规格型号：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=20 name="drugModel" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">数量：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=10 name="drugNumber" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">状态：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=10 name="status" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">删除状态：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=10 name="deleteFlag" type="text">
			            
					    </div>
					</li>
				</ul>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                		<input type="hidden" id="id" value="${id}">
	                         <button class="btn btn-default" id="homeDrugBasic" type="button">
									<i class="fa fa-reply mr5"></i>返回
							</button>
	                    </div>
	                </li>
				</ul>
			</form>
		</div>
	</div>    <!--子页面end-->
                 
</body>
</html>