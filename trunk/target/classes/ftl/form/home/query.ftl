<#if property.propertyQuery.isExist>
		<#if property.propertyQuery.showType=='TIMES'>
							<div class="form-group-Wdate">
		<#else>		
					        <div class="form-group">
		</#if>
					            <label>${property.title}：</label>
		<#if property.propertyQuery.showType=='SELECT'>
					            <select class="form-control-select" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" id="<#if ! object.isMain>${object.objectName}</#if>${property.name}" placeholder="请选择" >
					            </select>
		</#if>			            
		<#if property.propertyQuery.showType=='TEXT'>
					           	<input type="text" class="inputText" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}" id="<#if ! object.isMain>${object.objectName}</#if>${property.name}"/>
		</#if>			            
		<#if property.propertyQuery.showType=='ORGTREE' || property.propertyQuery.showType=='USERTREE' >
					           	<input type="text" class="inputText" id="<#if ! object.isMain>${object.objectName}</#if>${property.name}"  onkeydown="return false;"/>
					           	<input type="hidden" class="inputText" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}"/>
								<ul id="<#if ! object.isMain>${object.objectName}</#if>${property.name}Tree" treeName="${property.propertyQuery.showType}" class="ztree orgTree"></ul>
		</#if>			            
		<#if property.propertyQuery.showType=='TIMES'>
					           	<input id="<#if ! object.isMain>${object.objectName}</#if>${property.name}StartDate" type="text" class="Wdate" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}StartDate" placeholder="请选择" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'<#if ! object.isMain>${object.objectName}.</#if>${property.name}EndDate\')}'})"> 至
                                <input id="<#if ! object.isMain>${object.objectName}</#if>${property.name}EndDate" type="text" class="Wdate" name="<#if ! object.isMain>${object.objectName}.</#if>${property.name}EndDate" placeholder="请选择" onclick="WdatePicker({minDate:'#F{$dp.$D(\'<#if ! object.isMain>${object.objectName}.</#if>${property.name}StartDate\')}'})">
		</#if>			            
					        </div>
</#if>