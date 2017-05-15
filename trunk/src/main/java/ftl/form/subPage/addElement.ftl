<#if property.propertyNew.isExist>
					<li  <#if property.propertyNew.showType=='HIDDEN'>style="display:none"</#if>>
					    <label class="ser-label"><#if property.propertyNew.isMust><em class="text-red mr5">*</em></#if>${property.title}：</label>
					    <div class="form-content">
		<#if property.propertyNew.showType=='SELECT'>
							<select class="<#if property.propertyNew.isMust>required </#if>form-control-select" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" id="${mainObject.objectName}${property.name}s" placeholder="请选择" >
				            </select>
		</#if>			            
		<#if property.propertyNew.showType=='TEXT' || property.propertyNew.showType=='HIDDEN'>
							<input class="<#if property.propertyNew.isMust>required </#if>inputText " <#if property.lenth != ''>maxlength=${property.lenth} </#if>name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" type="text">
		</#if>			            
		<#if property.propertyNew.showType=='ORG'>
							<input class="<#if property.propertyNew.isMust>required </#if>inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" value="${userInfo.orgName}" readonly type="text">
							<input type="hidden" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" value="${userInfo.orgId}">
		</#if>			            
		<#if property.propertyNew.showType=='USER'>
							<input class="<#if property.propertyNew.isMust>required </#if>inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" value="${userInfo.userName}" readonly type="text">
							<input type="hidden" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" value="${userInfo.id}">
		</#if>			            
		<#if property.propertyNew.showType=='TIMES'>
							<input name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}"  type="text" class="<#if property.propertyNew.isMust>required </#if>Wdate pull-left inputText" placeholder="请选择" onClick="WdatePicker()">
		</#if>
		<#if property.propertyNew.showType=='ORGTREE' || property.propertyNew.showType=='USERTREE' >
				           	<input class="<#if property.propertyNew.isMust>required </#if> inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}"  type="text"  onkeydown="return false;">
				           	<input class="inputText " name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" type="hidden">
							<ul id="<#if ! object.isMain>${object.objectName}</#if>${property.name}Tree" treeName="${property.propertyNew.showType}" class="ztree orgTree"></ul>
		</#if>			            
		
					    </div>
					</li>
</#if>