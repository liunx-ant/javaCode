<#if property.propertyEdit.isExist>

					<li <#if property.propertyEdit.showType=='HIDDEN'>style="display:none"</#if>>
					    <label class="ser-label"><#if property.propertyEdit.isMust><em class="text-red mr5">*</em></#if>${property.title}：</label>
					    <div class="form-content">
		<#if property.propertyEdit.showType=='SELECT'>
						<select class="<#if property.propertyEdit.isMust>required </#if>form-control-select" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" id="${mainObject.objectName}${property.name}s" placeholder="请选择" >
			            </select>
		</#if>			            
		<#if property.propertyEdit.showType=='TEXT' || property.propertyEdit.showType=='HIDDEN'>
						<input class="<#if property.propertyEdit.isMust>required </#if>inputText " <#if property.lenth != ''>maxlength=${property.lenth} </#if>name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" type="text">
		</#if>	
		<#if property.propertyEdit.showType=='ORG'>
					<input class="<#if property.propertyEdit.isMust>required </#if>inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" readonly type="text">
					<input type="hidden" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}">
		</#if>		
		<#if property.propertyEdit.showType=='USER'>
						<input class="<#if property.propertyEdit.isMust>required </#if>inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" readonly type="text">
						<input type="hidden" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}">
		</#if>			            
		<#if property.propertyEdit.showType=='TIMES'>
						<input name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}"  type="text" class="<#if property.propertyEdit.isMust>required </#if>Wdate pull-left inputText" placeholder="请选择" onClick="WdatePicker()">
		</#if>
		<#if property.propertyEdit.showType=='ORGTREE' || property.propertyEdit.showType=='USERTREE' >
			           	<input class="<#if property.propertyEdit.isMust>required </#if> inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}"  type="text"  onkeydown="return false;">
			           	<input class="inputText " name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" type="hidden">
						<ul id="<#if ! object.isMain>${object.objectName}</#if>${property.name}Tree" treeName="${property.propertyEdit.showType}" class="ztree orgTree"></ul>
		</#if>	
					    </div>
					</li>
</#if>