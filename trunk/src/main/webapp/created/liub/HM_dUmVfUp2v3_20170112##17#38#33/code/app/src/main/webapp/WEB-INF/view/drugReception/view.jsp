<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="drugReceptionTemplate.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>药剂领用</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugReception/controller/drugReceptionBusiness.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugReception/controller/drugReceptionInfo.js"></script>
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

					<li style="display:none">
					    <label class="ser-label">主键：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="id" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">领用单号：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="receptionCode" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">登记时间：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=19 name="registrationTime" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">部门：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="receptionDep" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">部门名称：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="receptionDepname" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">领用人：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="receptionUser" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">领用人名称：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="receptionUsername" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">施药仓库：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="warehouse" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">施药货位：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="allocation" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">用途：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=192 name="purpose" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">审批状态：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="approveStatus" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">状态：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="status" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">删除状态：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="deleteFlag" type="text">
			            
					    </div>
					</li>
				</ul>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                		<input type="hidden" id="id" value="${id}">
	                         <button class="btn btn-default" id="homeDrugReception" type="button">
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