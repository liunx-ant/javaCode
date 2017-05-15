<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="drugInStorageTemplate.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>采购入库</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugInStorage/controller/drugInStorageBusiness.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugInStorage/controller/drugInStorageEdit.js"></script>
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
					    <label class="ser-label"><em class="text-red mr5">*</em>主键：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=64 name="id" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>入库单号/交还单号：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=64 name="inStorageCode" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>采购单id/领用id：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=64 name="orderId" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>采购申请单号/领用单号：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=64 name="orderCode" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>登记时间：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=19 name="registrationTime" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>采购人/交还人：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=64 name="buyer" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=64 name="buyerName" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>状态：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=11 name="status" type="text">
					    </div>
					</li>

					<li >
					    <label class="ser-label"><em class="text-red mr5">*</em>审批状态：</label>
					    <div class="form-content">
						<input class="required inputText " maxlength=11 name="approveStatus" type="text">
					    </div>
					</li>

					<li >
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
	                	<input type="hidden" id="id" value="${id}">
	                        <button class="btn btn-orange" id="updateDrugInStorage"><i class="fa fa-save"></i>保存</button>
	                        	<button class="btn btn-default" id="homeDrugInStorage" type="button">
								<i class="fa fa-close mr5"></i>取消
							</button>
	                    </div>
	                </li>
				</ul>
			</form>
		</div>
	</div>    <!--子页面end-->
                 
</body>
</html>