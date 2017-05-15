<objects>
	<author>${author}</author>
	<version>${version}</version>
	<copyright>${copyright}</copyright>
<#list objects as object>
	<object>
		<isMain><#if object.isMain>true<#else>false</#if></isMain>
		<apiPackageName>${object.apiPackageName}</apiPackageName>
		<appPackageName>${object.appPackageName}</appPackageName>
		<domainPackageName>${object.domainPackageName}</domainPackageName>
		<className>${object.className}</className>
		<tableName>${object.tableName}</tableName>
		<title>${object.title}</title>
		<properties>
	<#list object.properties as property>
			<!--colType String Integer Timestamp BigDecimal-->
			<property colName="${property.colName}" name="${property.name}" colType="${property.colType}" lenth="${property.lenth}" isPk="${property.isPk?string('true','false')}" title="${property.title}" dictType="${property.dictType}">
				<!--showType:/text/hidden/select/times/orgtree/usertree/org/user -->
				<new isExist="${property.propertyNew.isExist?string('true','false')}" showType="${property.propertyNew.showType}" isMust="${property.propertyNew.isMust?string('true','false')}" />
				<edit isExist="${property.propertyEdit.isExist?string('true','false')}" showType="${property.propertyEdit.showType}" isMust="${property.propertyEdit.isMust?string('true','false')}" />
				<!--queryType:like[模糊]/equal[等于]/more[匹配] -->
				<query isExist="${property.propertyQuery.isExist?string('true','false')}" showType="${property.propertyQuery.showType}" queryType="${property.propertyQuery.showType}" />
				<list isExist="${property.propertyList.isExist?string('true','false')}" showType="${property.propertyList.showType}" orderBy="${property.propertyList.orderBy}"/>
				<view isExist="${property.propertyView.isExist?string('true','false')}" showType="${property.propertyView.showType}" />
			</property>
	</#list>
		</properties>
	<#if (object.objectRels?size>0) >  
		<objectRels>
		<#list object.objectRels as rel>
			<#if (rel.relClassName)??>
				<objectRel relClassName="${rel.relClassName}" relProperty="${rel.relProperty}" relToClassName="${rel.relToClassName}"  relToProperty="${rel.relToProperty}" relType="${rel.relType}"/>
			</#if>
		</#list>
		</objectRels>
	</#if>
	</object>
</#list>
	 


</objects>