<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="drugDestroyTemplate.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>药剂销毁</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugDestroy/controller/drugDestroyBusiness.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugDestroy/controller/drugDestroyInfo.js"></script>
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
					    <label class="ser-label">销毁编码：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="destroyCode" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">销毁日期：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=19 name="destroyTime" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">数量：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="destroyNumber" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">生产厂家：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="manufacturer" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">残渣来源：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="source" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">销毁方式：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="destroyType" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">是否合规：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="compliance" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">备注：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="remark" type="text">
			            
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

					<li >
					    <label class="ser-label">参与人：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=20 name="partIn" type="text">
			            
					    </div>
					</li>
				</ul>
				<div class="zk-tab mt15" id="drugBasicTable"></div>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                		<input type="hidden" id="id" value="${id}">
	                         <button class="btn btn-default" id="homeDrugDestroy" type="button">
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