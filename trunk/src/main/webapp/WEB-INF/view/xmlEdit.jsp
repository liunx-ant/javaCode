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
		<form onsubmit="return false" enctype="application/json" >
			<c:forEach items="${objects}" var="objs" varStatus="objsIndex">
				<div class="crumbs">
                    <p>
                        <span class="active mt12"><i class="fa fa-book mr5"></i>${objs.objects[0].title}-基本信息</span>
                    </p>
                    <div class="crumbs-line"></div>
                </div>
				<c:forEach items="${objs.objects}" var="obj" varStatus="objIndex">
	                <c:if test="${objects.size()==1}">
	                	<c:set var="objsIndex" value="${objIndex}"/>
	                </c:if>
					<ul class="w100">	
						<li>
						    <label class="ser-label"><em class="text-red mr5">*</em>类名：</label>
						    <div class="form-content">
								<input class=" inputText required" name="objects[${objsIndex.index}].className" value="${obj.className}" type="text">
						    </div>
						</li>
						<li>
						    <label class="ser-label"><em class="text-red mr5">*</em>表名：</label>
						    <div class="form-content">
								<input class=" inputText required" readonly name="objects[${objsIndex.index}].tableName" value="${obj.tableName}" type="text">
						    </div>
						</li>
						<li>
						    <label class="ser-label"><em class="text-red mr5">*</em>标题：</label>
						    <div class="form-content">
								<input class=" inputText required" name="objects[${objsIndex.index}].title" value="${obj.title}" type="text">
						    </div>
						</li>
						<li>
						    <label class="ser-label"><em class="text-red mr5">*</em>是否是主表：</label>
						    <div class="form-content">
						    	<select class="required form-control-select" name="objects[${objsIndex.index}].isMain" placeholder="请选择" >
						    		<option value="true">是</option>
						    		<option value="false" <c:if test="${! obj.isMain}">selected</c:if>>否</option>
				           	 	</select>
						    </div>
						</li>
					</ul>
					<div class="zk-tab mt15" id="${obj.className}Table">
						<table class="table-form table-bordered table-hover table-ellipsis table-fixed" style="min-width: 1000px">
							<thead>
								<tr>
									<th width="5%">主键</th>
									<th width="13%"><em class="text-red mr5">*</em>显示名称</th>
									<th width="13%"><em class="text-red mr5">*</em>属性名称</th>
									<th width="13%"><em class="text-red mr5">*</em>字段名称</th>
									<th width="13%"><em class="text-red mr5">*</em>长度限制</th>
									<th width="13%"><em class="text-red mr5">*</em>数据类型</th>
									<th width="13%">字典值</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${obj.properties}" var="properties" varStatus="propertiesIndex">
									<tr> 
										<td>
											<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].isPk" <c:if test="${properties.isPk}">checked</c:if> class="w85 "></input>
										</td>
										<td class="">
											<input type="text" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].title" value="${properties.title}" class="w85 required"></input>
<!-- 										    新增 -->
											<div name="addPage" style="display: none">
												tad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyNew.isExist" <c:if test="${properties.propertyNew.isExist}">checked=true</c:if> class="w85 "></input>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyNew.showType" placeholder="请选择" >
										    		<option <c:if test="${properties.propertyNew.showType=='TEXT'}">selected</c:if> value="TEXT">文本</option>
										    		<option <c:if test="${properties.propertyNew.showType=='HIDDEN'}">selected</c:if> value="HIDDEN">隐藏</option>
										    		<option <c:if test="${properties.propertyNew.showType=='SELECT'}">selected</c:if> value="SELECT">下拉</option>
										    		<option <c:if test="${properties.propertyNew.showType=='TIMES'}">selected</c:if> value="TIMES">时间</option>
										    		<option <c:if test="${properties.propertyNew.showType=='ORGTREE'}">selected</c:if> value="ORGTREE">组织树</option>
										    		<option <c:if test="${properties.propertyNew.showType=='USERTREE'}">selected</c:if> value="USERTREE">组织树</option>
										    		<option <c:if test="${properties.propertyNew.showType=='ORG'}">selected</c:if> value="ORG">登录人组织</option>
										    		<option <c:if test="${properties.propertyNew.showType=='USER'}">selected</c:if> value="USER">登录人</option>
								           	 	</select>
												/tadtad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyNew.isMust" <c:if test="${properties.propertyNew.isMust}">checked=true</c:if> class="w85 "></input>
											/tad</div>
<!-- 											编辑 -->
											<div name="editPage" style="display: none">
												tad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyEdit.isExist" <c:if test="${properties.propertyEdit.isExist}">checked=true</c:if> class="w85 "></input>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyEdit.showType" placeholder="请选择" >
										    		<option <c:if test="${properties.propertyEdit.showType=='TEXT'}">selected</c:if> value="TEXT">文本</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='HIDDEN'}">selected</c:if> value="HIDDEN">隐藏</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='SELECT'}">selected</c:if> value="SELECT">下拉</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='TIMES'}">selected</c:if> value="TIMES">时间</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='ORGTREE'}">selected</c:if> value="ORGTREE">组织树</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='USERTREE'}">selected</c:if> value="USERTREE">组织树</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='ORG'}">selected</c:if> value="ORG">登录人组织</option>
										    		<option <c:if test="${properties.propertyEdit.showType=='USER'}">selected</c:if> value="USER">登录人</option>
								           	 	</select>
												/tadtad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyEdit.isMust" <c:if test="${properties.propertyEdit.isMust}">checked=true</c:if> class="w85 "></input>
											/tad</div>
<!-- 											详情 -->
											<div name="viewPage" style="display: none">
												tad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyView.isExist" <c:if test="${properties.propertyView.isExist}">checked=true</c:if> class="w85 "></input>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyView.showType" placeholder="请选择" >
										    		<option <c:if test="${properties.propertyView.showType=='TEXT'}">selected</c:if> value="TEXT">文本</option>
										    		<option <c:if test="${properties.propertyView.showType=='HIDDEN'}">selected</c:if> value="HIDDEN">隐藏</option>
										    		<option <c:if test="${properties.propertyView.showType=='SELECT'}">selected</c:if> value="SELECT">下拉</option>
										    		<option <c:if test="${properties.propertyView.showType=='TIMES'}">selected</c:if> value="TIMES">时间</option>
										    		<option <c:if test="${properties.propertyView.showType=='ORGTREE'}">selected</c:if> value="ORGTREE">组织树</option>
										    		<option <c:if test="${properties.propertyView.showType=='USERTREE'}">selected</c:if> value="USERTREE">组织树</option>
										    		<option <c:if test="${properties.propertyView.showType=='ORG'}">selected</c:if> value="ORG">登录人组织</option>
										    		<option <c:if test="${properties.propertyView.showType=='USER'}">selected</c:if> value="USER">登录人</option>
								           	 	</select>
											/tad</div>
<!-- 							           	 	查询条件 -->
											<div name="queryPage" style="display: none">
												tad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyQuery.isExist" <c:if test="${properties.propertyQuery.isExist}">checked=true</c:if> class="w85 "></input>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyQuery.showType" placeholder="请选择" >
										    		<option <c:if test="${properties.propertyQuery.showType=='TEXT'}">selected</c:if> value="TEXT">文本</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='HIDDEN'}">selected</c:if> value="HIDDEN">隐藏</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='SELECT'}">selected</c:if> value="SELECT">下拉</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='TIMES'}">selected</c:if> value="TIMES">时间</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='ORGTREE'}">selected</c:if> value="ORGTREE">组织树</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='USERTREE'}">selected</c:if> value="USERTREE">组织树</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='ORG'}">selected</c:if> value="ORG">登录人组织</option>
										    		<option <c:if test="${properties.propertyQuery.showType=='USER'}">selected</c:if> value="USER">登录人</option>
								           	 	</select>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyQuery.queryType" placeholder="请选择" >
													<option <c:if test="${properties.propertyQuery.queryType=='EQUAL'}">selected</c:if> value="EQUAL">等于</option>
											 		<option <c:if test="${properties.propertyQuery.queryType=='LIKE'}">selected</c:if> value="LIKE">模糊</option>
											 		<option <c:if test="${properties.propertyQuery.queryType=='MORE'}">selected</c:if> value="MORE">匹配</option>
								           	 	</select>
											/tad</div>
<!-- 							           	 	列表显示 -->
											<div name="listPage" style="display: none">
												tad<input type="checkbox" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyList.isExist" <c:if test="${properties.propertyList.isExist}">checked=true</c:if> class="w85 "></input>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyList.showType" placeholder="请选择" >
										    		<option <c:if test="${properties.propertyList.showType=='TEXT'}">selected</c:if> value="TEXT">文本</option>
										    		<option <c:if test="${properties.propertyList.showType=='CHECKBOX'}">selected</c:if> value="CHECKBOX">选择框</option>
								           	 	</select>
												/tadtad<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].propertyList.orderBy" placeholder="请选择" >
													<option  value=""></option>
													<option <c:if test="${properties.propertyList.orderBy=='ASC'}">selected</c:if> value="ASC">正序</option>
											 		<option <c:if test="${properties.propertyList.orderBy=='DESC'}">selected</c:if> value="DESC">倒序</option>
								           	 	</select>
											/tad</div>
										</td>
										<td class="">
											<input type="text" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].name" value="${properties.name}" class="w85 required"></input>
										</td>
										<td class="">
											<input type="text" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].colName" value="${properties.colName}" class="w85 required"></input>
										</td>
										<td class="">
											<input type="text" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].lenth" value="${properties.lenth}" class="w85 required digits" style="text-align: right;"></input>
										</td>
										<td class="">
											<select class="required form-control-select" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].colType" placeholder="请选择" >
									    		<option <c:if test="${properties.colType=='BIGINT'}">selected</c:if>  value="LONG" >长整数</option>
									    		<option <c:if test="${properties.colType=='INTEGER'}">selected</c:if>  value="INTEGER" >整数</option>
									    		<option <c:if test="${properties.colType=='DATETIME'}">selected</c:if>  value="DATETIME" >时间</option>
									    		<option <c:if test="${properties.colType=='DECIMAL'}">selected</c:if>  value="DECIMAL" >数字</option>
									    		<option <c:if test="${properties.colType=='VARCHAR'}">selected</c:if>  value="VARCHAR" >字符</option>
							           	 	</select>
										</td>
										<td class="">
											<input type="text" name="objects[${objsIndex.index}].properties[${propertiesIndex.index}].dictType" value="${properties.dictType}" class="w85 "></input>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tbody style="display:none;" id="${obj.className}ObjectRel">
								<c:forEach items="${obj.objectRels}" var="objectRel" varStatus="relIndex">
									<tr>
										<td><input type="text" name="objects[${objsIndex.index}].objectRels[${relIndex.index}].relClassName" value="${objectRel.relClassName}"> </td>
										<td><input type="text" name="objects[${objsIndex.index}].objectRels[${relIndex.index}].relProperty" value="${objectRel.relProperty}"> </td>
										<td><input type="text" name="objects[${objsIndex.index}].objectRels[${relIndex.index}].relToClassName" value="${objectRel.relToClassName}"> </td>
										<td><input type="text" name="objects[${objsIndex.index}].objectRels[${relIndex.index}].relToProperty" value="${objectRel.relToProperty}"> </td>
										<td><input type="text" name="objects[${objsIndex.index}].objectRels[${relIndex.index}].relType" value="${objectRel.relType}"> </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</c:forEach>
		</form>
		<ul>
               <li>
                   <label class="ser-label">&nbsp;</label>
                   <div class="form-content clearfix">
                       <button class="btn btn-orange" id="save"><i class="fa fa-save"></i>保存</button>
                   </div>
               </li>
		</ul>	
		</div>
	</div>				
    <!--子页面end-->
                 
</body>
<script type="text/javascript" src="/js/edit.js"></script>
</html>