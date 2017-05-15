<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../common/jspf/common.jspf"%>
<%@ include file="../common/jspf/pageTemplate.jspf"%>
<%@ include file="${mainObject.objectName}Template.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>${mainObject.title}</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/js/${mainObject.objectName}/controller/${mainObject.objectName}Business.js"></script>
<script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/js/${mainObject.objectName}/controller/${mainObject.objectName}Edit.js"></script>
</head>
<body>
	<!--子页面begin-->
<#include "/form/subPage/subPageTop.ftl"/>
	
<#list objects as object>
	<#if object.isMain>
		<#list object.properties as property>
							<#include "/form/subPage/editElement.ftl"/>
		</#list>
	</#if>
	<#list mainObject.objectRels as objectRel>
		<#if ! object.isMain && objectRel.relType == 'ONETOONE'>
			<#list object.properties as property>
							<#include "/form/subPage/addElement.ftl"/>
			</#list>
		</#if>
	</#list>
</#list>
							<#include "/form/subPage/editPageBtn.ftl"/>
    <!--子页面end-->
                 
</body>
</html>