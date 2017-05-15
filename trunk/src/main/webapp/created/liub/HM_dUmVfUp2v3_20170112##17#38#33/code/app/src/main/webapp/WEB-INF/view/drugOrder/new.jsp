<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="drugOrderTemplate.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>采购订单</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugOrder/controller/drugOrderBusiness.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugOrder/controller/drugOrderAdd.js"></script>
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
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>主键：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="id" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>采购单号：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="orderCode" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>部门：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="department" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>部门名称：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="departmentName" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>申请人id：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="applicant" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>申请人名称：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="applicantName" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>申请时间：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=19 name="applicantTime" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>审批状态：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=11 name="approveStatus" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>入库状态：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=11 name="inventoryStatus" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>删除状态：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=11 name="deleteFlag" type="text">
		
					    </div>
					</li>
				</ul>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                        <button class="btn btn-orange" id="addDrugOrder"><i class="fa fa-save"></i>保存</button>
	                        	<button class="btn btn-default" id="homeDrugOrder" type="button">
								<i class="fa fa-close mr5"></i>取消
							</button>
	                    </div>
	                </li>
				</ul>	
			</form>
		</div>
	</div>				
    <!--子页面end-->
                 
</body>
</html>