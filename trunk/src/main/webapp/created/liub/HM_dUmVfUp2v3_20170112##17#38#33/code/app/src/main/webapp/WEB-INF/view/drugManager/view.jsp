<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="drugManagerTemplate.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>药剂出入库</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugManager/controller/drugManagerBusiness.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugManager/controller/drugManagerInfo.js"></script>
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
					    <label class="ser-label">药剂id：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="drugId" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">关联ID：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="correlationId" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">关联单号：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="correlationCode" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">出入类型：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="accessType" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">生产厂家：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=64 name="manufacturer" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">单价：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=10 name="price" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">用途：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=21845 name="purpose" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">备注：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=21845 name="remark" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">状态：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="status" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">生产日期：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=19 name="producedTime" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">出入日期：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=19 name="optionTime" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">商标：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=20 name="digest" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">收入：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="income" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">支出：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="expenditure" type="text">
			            
					    </div>
					</li>

					<li >
					    <label class="ser-label">结存：</label>
					    <div class="form-content">
						<input readonly class="inputText " maxlength=11 name="balance" type="text">
			            
					    </div>
					</li>
				</ul>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                		<input type="hidden" id="id" value="${id}">
	                         <button class="btn btn-default" id="homeDrugManager" type="button">
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