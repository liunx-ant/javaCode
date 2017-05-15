<%@ page language="java" pageEncoding="utf-8"%>
<!-- ${mainObject.title}表格模版 (列表)-->
	<textarea id="${mainObject.objectName}TableTemplateTxt" style="display: none">
	<![CDATA[
		{#template MAIN}
			 <table class="table-form table-bordered table-hover table-ellipsis table-fixed" style="min-width: 1000px">
				<thead>
				<tr>
					<th width="3%" ><input type="checkbox" id="checkAll"></th>
					<th width="5%">序号</th>
<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyList.isExist && property.propertyList.showType != 'CHECKBOX'>
					<th>${property.title}</th>
		</#if>				
	</#list> 
</#list> 
				</tr>
				</thead>
				<tbody>
			<!-- 增加序号定义
				$P startNum 定义每页开始行号
				$P index 定义行索引
				-->
				{#param name=startNum value=($T.pageNum - 1) * $T.pageSize }
				{#param name=sumWeigth value=0}
				{#param name=sumPrice value=0}
				{#foreach $T.list as row}
					{#param name=index value=($P.startNum + $T.row$index + 1)}
						<tr>
							<td><input type="checkbox" value="<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.showType == 'CHECKBOX'>{$T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name}}</#if></#list></#list>" index="{$P.index}" name="check"></td>		
							<td>{$P.index}</td>
<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyList.isExist && property.propertyList.showType != 'CHECKBOX'>
			<#assign hasDic=0/>
			<#if property.dictType !=''>
				<#assign hasDic=1/>
							<td class="text-left" title="{getNameByKey("${p.dictType}",$T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name})||''}">
								{getNameByKey("${p.dictType}",$T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name}) ||''}
			</#if>
			<#if property.propertyNew.showType=='ORGTREE'|| property.propertyNew.showType=='ORG'>
				<#assign hasDic=2/>
							<td class="text-left" title="{queryOrgInfoById($T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name})||''}">
								{queryOrgInfoById($T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name}) ||''}
			</#if>
			<#if property.propertyNew.showType=='USERTREE'|| property.propertyNew.showType=='USER' >
				<#assign hasDic=3/>
							<td class="text-left" title="{queryUserInfoById($T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name})||''}">
								{queryUserInfoById($T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name}) ||''}
			</#if>
			<#if hasDic==0>
							<td class="text-left" title="{#if $T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name} !=null}{$T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name}<#if property.type =='Date'>Str</#if><#if property.colType =='DECIMAL'>.toFixed(2)</#if>}{#/if}">
								{#if $T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name} !=null}
									{$T.row.<#if ! object.isMain>${object.objectName}.</#if>${property.name}<#if property.type =='Date'>Str</#if><#if property.colType =='DECIMAL'>.toFixed(2)</#if>}
								{#/if}
			</#if>
						</td>
		</#if>				
	</#list> 
</#list> 
					</tr>
				{#/for}
					{#if $T.list.length>0}
					{#else}
						<tr>    
<#assign titleNum=0/>
<#list objects as object>
	<#list object.properties as property>
		<#if property.propertyList.isExist>
			<#assign titleNum=titleNum+1/>
		</#if>				
	</#list> 
</#list> 
							<td colspan=${titleNum+1} class="text-left">无符合条件数据</td>
						</tr>
					{#/if}
				</tbody>
			</table>
		{#/template MAIN}
	]]>
	</textarea>

<#list relObjects as object>
	<!-- ${object.title}附属表模版 (详情/修改用)-->
	<textarea id="${mainObject.objectName}${object.objectName}TableTemplateTxt" style="display: none">
	<![CDATA[
		{#template MAIN}
			 <table class="table-form table-bordered table-hover table-ellipsis table-fixed" style="min-width: 1000px">
				<thead>
				<tr>
					<th width="5%">序号</th>
	<#list object.properties as property>
		<#if property.propertyNew.isExist || property.propertyEdit.isExist || property.propertyView.isExist>
					<th><#if property.propertyNew.isMust || property.propertyEdit.isMust><em class="text-red mr5">*</em></#if>${property.title}</th>
		</#if>				
	</#list> 
					<th width="14%">操作</th>
				</tr>
				</thead>
				<tbody>
			<!-- 增加序号定义
				$P startNum 定义每页开始行号
				$P index 定义行索引
				-->
				{#param name=startNum value=0 }
				{#foreach $T as row}
					{#param name=index value=($P.startNum + $T.row$index + 1)}
						<tr>           
							<td>{$P.index}</td>
	<#list object.properties as property>
		<#if property.propertyNew.isExist || property.propertyView.isExist>
							<td class="text-left">
								<input type="text" name="${object.objectName}List[{$P.index-1}].${property.name}" value="{$T.row.${property.name}<#if property.type =='Date'>Str</#if> ||''}" class="w100 required "></input>
							</td>
		</#if>				
	</#list> 
							<td class="text-left">
								<a href="javascript:void(0)" class="link-blue" onclick="del${mainObject.className}Row(this)"><i class="fa fa-times mr5"></i>删除</a>
							</td>
						</tr>
				{#/for}
					{#if $T.length>0}
						{#else}
						<tr>    
<#assign titleNum=0/>
<#list relObjects as object>
	<#list object.properties as property>
		<#if property.propertyNew.isExist || property.propertyView.isExist>
			<#assign titleNum=titleNum+1/>
		</#if>				
	</#list> 
</#list> 
							<td colspan=${titleNum+2} class="text-left">无符合条件数据</td>
						</tr>
					{#/if}
				</tbody>
			</table>
		{#/template MAIN}
	]]>
	</textarea>
</#list>
