<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mainObject.apiPackageName}.dao.${mainObject.className}Dao">
	<!--${mainObject.title} sql-->
 	<resultMap id="BaseResultMap" type="${mainObject.domainPackageName}.${mainObject.className}Vo">
<#list objects as object>
		<!--${object.title} ${object.tableName} column-->
	<#list object.properties as property>
		<result column="<#if !object.isMain>${object.objectName}$${property.name}</#if><#if object.isMain>${property.colName}</#if>" jdbcType="<#if property.colType == 'DATETIME'>TIMESTAMP<#else>${property.colType}</#if>" property="<#if !object.isMain>${object.objectName}.</#if>${property.name}" />
	</#list>
</#list>
	</resultMap>
	<!--插入字段-->
	<sql id="${mainObject.tableName}_column_insert">
<#list mainObject.properties as property>
	<#if property_has_next>
		${property.colName},
	</#if>
	<#if ! property_has_next>
		${property.colName}
	</#if>
</#list>
	</sql>
<#list objects as object>
	<!--${object.title} ${object.tableName}查询字段-->
	<sql id="${object.tableName}_column_query">
	<#list object.properties as property>
		<#if property_has_next>
		${object.objectName}.${property.colName}<#if !object.isMain> as ${object.objectName}$${property.name}</#if>,
		</#if>
		<#if ! property_has_next>
		${object.objectName}.${property.colName}<#if !object.isMain> as ${object.objectName}$${property.name}</#if>
		</#if>
	</#list>
	</sql>
</#list>
	<!--通用查询条件-->
	<sql id="where">
<#list objects as object>
	<#list object.properties as property>
		<if test="@Ognl@IsNotEmpty(<#if ! object.isMain>${object.objectName}) and @Ognl@IsNotEmpty(${object.objectName}.</#if>${property.name})">
			<#if property.propertyQuery.isExist && property.propertyQuery.queryType=='LIKE'>
			and ${object.objectName}.${property.colName} like CONCAT('%',${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}},'%') 
			<#elseif property.propertyQuery.isExist && property.propertyQuery.queryType=='MORE'>
			and FIND_IN_SET(${object.objectName}.${property.colName},${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}}) 
			<#else>
			and ${object.objectName}.${property.colName} = ${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}}
			</#if>
		</if>
			<#if property.type =='Date'>
		<if test="@Ognl@IsNotEmpty(<#if ! object.isMain>${object.objectName}) and @Ognl@IsNotEmpty(${object.objectName}.</#if>${property.name}StartDate)">
			and (${object.objectName}.${property.colName}) >= (${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}StartDate})
		</if>
		<if test="@Ognl@IsNotEmpty(<#if ! object.isMain>${object.objectName}) and @Ognl@IsNotEmpty(${object.objectName}.</#if>${property.name}EndDate)">
			and (${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}EndDate}) >= (${object.objectName}.${property.colName})
		</if>
			</#if>
		
	</#list>		
</#list>		
	</sql>
	<!--新增-->
	<insert id="save">
		insert into ${mainObject.tableName} (  <include refid="${mainObject.tableName}_column_insert" /> )
		values (
<#list mainObject.properties as property>
	<#if property_has_next>
			${r'#'}{${property.name}},
	</#if>
	<#if ! property_has_next>
			${r'#'}{${property.name}}
	</#if>
</#list>			
		)
	</insert>	
	
	<!--批量新增-->
	<insert id="saveBatch">
		insert into ${mainObject.tableName} (  <include refid="${mainObject.tableName}_column_insert" /> )
		values
		<foreach collection="list" item="item" index="index" separator="," > 
			(
<#list mainObject.properties as property>
	<#if property_has_next>
				${r'#'}{item.${property.name}},
	</#if>
	<#if ! property_has_next>
				${r'#'}{item.${property.name}}
	</#if>
</#list>			
			)
		</foreach>
	</insert>	
	
	<!--修改所有-->
	<update id="updateById">
		update ${mainObject.tableName}  set 
<#list mainObject.properties as property>
	<#if ! property.isPk>
		<#if property_has_next>
		${property.colName}=${r'#'}{${property.name}},
		</#if>
		<#if ! property_has_next>
		${property.colName}=${r'#'}{${property.name}}
		</#if>
	</#if>
</#list>
		where 1=1
<#list mainObject.properties as property>
	<#if  property.isPk>
		and ${property.colName}=${r'#'}{${property.name}}
	</#if>
</#list>
    </update>
    
    <!--部分修改-->
    <update id="updateByIdSelective">
		update ${mainObject.tableName}  
		<set>
<#list mainObject.properties as property>
	<#if ! property.isPk>
		  	<if test="@Ognl@IsNotEmpty(${property.name})">
				${property.colName}=${r'#'}{${property.name}},
			</if>
	</#if>
</#list>
		 </set>
		where 1=1
<#list mainObject.properties as property>
	<#if  property.isPk>
		<#if property_has_next>
		and FIND_IN_SET(${property.colName},${r'#'}{${property.name}})
		</#if>
	</#if>
</#list>
    </update>
    
    <!--删除-->
    <delete id="delete">
  		DELETE ${mainObject.objectName} FROM ${mainObject.tableName} ${mainObject.objectName} where 1=1 
		<include refid="where" /> 
  	</delete>

  	<!--查询详情-->
	<select id="getInfo" resultMap="BaseResultMap" >
    	select 
    	<include refid="${mainObject.tableName}_column_query" />
    	from 
    	${mainObject.tableName} ${mainObject.objectName}
		where 1=1 
		<include refid="where" /> 
  </select>
  
  <!--查询列表-->
	<select id="selectBySelective" resultMap="BaseResultMap" >
    	select 
    	<include refid="${mainObject.tableName}_column_query" />
    	from 
    	${mainObject.tableName} ${mainObject.objectName}
		where 1=1 
		<include refid="where" /> 
  	</select>
  	
	<select id="getAll" resultMap="BaseResultMap" >
    	select 
    	<include refid="${mainObject.tableName}_column_query" />
    	from 
    	${mainObject.tableName} ${mainObject.objectName}
		where 1=1 
		<include refid="where" /> 
  	</select>
  	
<#if (mainObject.objectRels?size>0) >  
  	<!--查询列表(内连接)-->
	<select id="getAllInnerJoin" resultMap="BaseResultMap" >
    	select 
<#list objects as object>
	<#if object_has_next>
    	<include refid="${object.tableName}_column_query" /> ,
    </#if>
	<#if ! object_has_next>
    	<include refid="${object.tableName}_column_query" /> 
    </#if>
</#list>
    	from 
<#list objects as object>
	<#if object_has_next>
    		${object.tableName} ${object.objectName},
    </#if>
	<#if ! object_has_next>
    		${object.tableName} ${object.objectName}
    </#if>
</#list>
		where 1=1 
<#list mainObject.objectRels as objectRel>
			and ${objectRel.relObject.objectName}.${objectRel.relProperty}=${objectRel.relToObject.objectName}.${objectRel.relToProperty} 
</#list>
		<include refid="where" /> 
  </select>

  	<!--查询列表(左连接)-->
	<select id="getAllLeftJoin" resultMap="BaseResultMap" >
    	select 
	<#list objects as object>
		<#if object_has_next>
    	<include refid="${object.tableName}_column_query" /> ,
   		</#if>
		<#if ! object_has_next>
    	<include refid="${object.tableName}_column_query" /> 
    	</#if>
	</#list>
    	from ${mainObject.tableName} ${mainObject.objectName} 
	<#list mainObject.objectRels as objectRel>
			left join ${objectRel.relObject.tableName} ${objectRel.relObject.objectName}  on ${objectRel.relToObject.objectName}.${objectRel.relToProperty}=${objectRel.relObject.objectName}.${objectRel.relProperty}  
	</#list>
		where 1=1 
		<include refid="where" /> 
  </select>
</#if>
</mapper>

