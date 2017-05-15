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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugDestroy/controller/drugDestroyAdd.js"></script>
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
					    <label class="ser-label"><em class="text-red mr5">*</em>销毁编码：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="destroyCode" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>药剂id：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="drugId" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>销毁日期：</label>
					    <div class="form-content">
							<input name="destroyTime"  type="text" class="required Wdate pull-left inputText" placeholder="请选择" onClick="WdatePicker()">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>数量：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=11 name="destroyNumber" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>生产厂家：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="manufacturer" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>残渣来源：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="source" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>销毁方式：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="destroyType" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>是否合规：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="compliance" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>备注：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=64 name="remark" type="text">
		
					    </div>
					</li>
					<li  >
					    <label class="ser-label"><em class="text-red mr5">*</em>参与人：</label>
					    <div class="form-content">
							<input class="required inputText " maxlength=20 name="partIn" type="text">
		
					    </div>
					</li>
				</ul>
				<div class="zk-tab mt15" id="drugBasicTable"></div>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                        <button class="btn btn-orange" id="addDrugDestroy"><i class="fa fa-save"></i>保存</button>
	                        	<button class="btn btn-default" id="homeDrugDestroy" type="button">
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