<#if property.propertyView.isExist>

					<li <#if property.propertyView.showType=='HIDDEN'>style="display:none"</#if>>
					    <label class="ser-label">${property.title}：</label>
					    <div class="form-content">
		<#if property.propertyView.showType=='SELECT'>
						<select disabled="disabled" class="form-control-select" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" id="${mainObject.objectName}${property.name}s" placeholder="请选择" >
			            </select>
		</#if>			            
		<#if property.propertyView.showType=='TEXT' || property.propertyView.showType=='HIDDEN'>
						<input readonly class="inputText " <#if property.lenth != ''>maxlength=${property.lenth} </#if>name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" type="text">
		</#if>		
		<#if property.propertyView.showType=='ORG'>
						<input class="<#if property.propertyView.isMust>required </#if>inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" type="text">
		</#if>		
		<#if property.propertyView.showType=='USER'>
						<input class="<#if property.propertyView.isMust>required </#if>inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" type="text">
		</#if>			            
			            
		<#if property.propertyView.showType=='TIMES'>
						<input disabled name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}"  type="text" class="Wdate pull-left inputText" placeholder="请选择" onClick="WdatePicker()">
		</#if>
		<#if property.propertyView.showType=='ORGTREE' || property.propertyView.showType=='USERTREE' >
			           	<input class="<#if property.propertyView.isMust>required </#if> inputText " id="<#if ! object.isMain>${object.objectName}</#if>${property.name}"  type="text">
		</#if>	
					    </div>
					</li>
</#if>