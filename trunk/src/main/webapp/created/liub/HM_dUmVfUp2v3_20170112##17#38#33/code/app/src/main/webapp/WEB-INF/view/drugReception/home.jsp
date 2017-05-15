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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/drugReception/controller/drugReceptionList.js"></script>
</head>
<body>
         <!--子页面内容部分-->
         <div class="iframe-cot pull-right">
              <div class="nav-tree">
              </div>
              <!--查询条件从这里开始-->
              <div class="right-form mt0">
                  <form class="form-inline" method="post" onsubmit="return false">
		            <div class="tb_search">
		                <div class="pull-left">
			             	<span class="search-more">更多条件<i class="fa fa-angle-down ml5"></i></span>
	                	</div>
		            </div>
		        </form>
              </div>
              <!--查询条件到这里结束-->
              <div class="line-e6e"></div>
              <div class="form-top">
				<div class="pull-left">
					<button class="btn btn-default" id="newDrugReception"><i class="fa fa-plus mr5"></i>新增</button>
					<button class="btn btn-default" id="editDrugReception"><i class="fa fa-pencil mr5"></i>编辑</button>
					<button class="btn btn-default" id="delDrugReception"><i class="fa fa-times mr5"></i>删除</button>
					<button class="btn btn-default" id="viewDrugReception"><i class="fa fa-eye mr5"></i>详情</button>
                </div>
 				<div class="pull-right">
		            <a class="btn btn-orange" id="searchDrugReception" ><i class="fa fa-search mr5"></i>查询</a>
		            <a class="btn btn-blue" id="clean"><i class="fa fa-trash-o mr5"></i>清空</a>
		        </div>
              </div>
              <div class="table-scroll pull-left w100" id="drugReceptionTable">
              </div>
              
              <!--pages begin-->
              <div class="pages" id="drugReceptionPage">
              </div>
              <!--pages end-->
          </div>
          
</body>
</html>