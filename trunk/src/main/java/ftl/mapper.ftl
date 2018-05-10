<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mainObject.apiPackageName}.dao.${mainObject.className}Dao">
	<!--${mainObject.title} sql-->
 	<resultMap id="BaseResultMap" type="${mainObject.domainPackageName}.${mainObject.className}">
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
		<#if (object.isMain && property.name == 'createUserid') || (object.isMain && property.name == 'createDeptid')>
		<#else>
		<if test="@Ognl@IsNotEmpty(<#if ! object.isMain>${object.objectName}) and @Ognl@IsNotEmpty(${object.objectName}.</#if>${property.name})">
			<#if property.propertyQuery.isExist && property.propertyQuery.queryType=='LIKE'>
			and ${object.objectName}.${property.colName} like CONCAT('%',${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name},jdbcType=${property.colType}},'%') 
			<#elseif property.propertyQuery.isExist && property.propertyQuery.queryType=='MORE'>
			and FIND_IN_SET(${object.objectName}.${property.colName},${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name},jdbcType=${property.colType}}) 
			<#else>
			and ${object.objectName}.${property.colName} = ${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name},jdbcType=${property.colType}}
			</#if>
		</if>
			<#if property.type =='Date'>
		<if test="@Ognl@IsNotEmpty(<#if ! object.isMain>${object.objectName}) and @Ognl@IsNotEmpty(${object.objectName}.</#if>${property.name}StartDate)">
			and TO_DAYS(${object.objectName}.${property.colName})-TO_DAYS(${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}StartDate,jdbcType=${property.colType}}) >= 0
		</if>
		<if test="@Ognl@IsNotEmpty(<#if ! object.isMain>${object.objectName}) and @Ognl@IsNotEmpty(${object.objectName}.</#if>${property.name}EndDate)">
			and TO_DAYS(${r'#'}{<#if ! object.isMain>${object.objectName}.</#if>${property.name}EndDate,jdbcType=${property.colType}}) - TO_DAYS(${object.objectName}.${property.colName}) >= 0
		</if>
			</#if>
		</#if>
		<#if object.isMain && property.name= 'createUserid'>
		<!--部门权限-->
		<if test="@Ognl@IsNotEmpty(createUserid) and @Ognl@IsNotEmpty(createDeptid)">
			and ${object.objectName}.create_userid = ${r'#'}{createUserid,jdbcType=VARCHAR}
		</if>
		</#if>
		<#if object.isMain && property.name= 'createDeptid'>
		<if test="@Ognl@IsNotEmpty(createDeptid)">
			and <if test="@Ognl@IsNotEmpty(createUserid)">( </if>FIND_IN_SET(${object.objectName}.create_deptid , ${r'#'}{createDeptid,jdbcType=VARCHAR}) <if test="@Ognl@IsNotEmpty(createUserid)">or ${object.objectName}.create_userid = ${r'#'}{createUserid,jdbcType=VARCHAR})</if>
		</if>
		<!--部门权限结束-->
		</#if>
		
	</#list>		
</#list>		
	</sql>
	<!--新增-->
	<insert id="insert" parameterType="${mainObject.domainPackageName}.${mainObject.className}">
		insert into ${mainObject.tableName} (  <include refid="${mainObject.tableName}_column_insert" /> )
		values (
<#list mainObject.properties as property>
	<#if property_has_next>
			${r'#'}{${property.name},jdbcType=${property.colType}},
	</#if>
	<#if ! property_has_next>
			${r'#'}{${property.name},jdbcType=${property.colType}}
	</#if>
</#list>			
		)
	</insert>	
	
	<!--批量新增-->
	<insert id="insertBatch" parameterType="${mainObject.domainPackageName}.${mainObject.className}">
		insert into ${mainObject.tableName} (  <include refid="${mainObject.tableName}_column_insert" /> )
		values
		<foreach collection="list" item="item" index="index" separator="," > 
			(
<#list mainObject.properties as property>
	<#if property_has_next>
				${r'#'}{item.${property.name},jdbcType=${property.colType}},
	</#if>
	<#if ! property_has_next>
				${r'#'}{item.${property.name},jdbcType=${property.colType}}
	</#if>
</#list>			
			)
		</foreach>
	</insert>	
	
	<!--修改所有-->
	<update id="update" parameterType="${mainObject.domainPackageName}.${mainObject.className}">
		update ${mainObject.tableName}  set 
<#list mainObject.properties as property>
	<#if ! property.isPk>
		<#if property.name !='createUserid'&& property.name !='createDeptid'&& property.name !='createDate'>
			<#if property_has_next>
			${property.colName}=${r'#'}{${property.name},jdbcType=${property.colType}},
			</#if>
			<#if ! property_has_next>
			${property.colName}=${r'#'}{${property.name},jdbcType=${property.colType}}
			</#if>
		</#if>
	</#if>
</#list>
		where 1=1
<#list mainObject.properties as property>
	<#if  property.isPk>
		and ${property.colName}=${r'#'}{${property.name},jdbcType=${property.colType}}
	</#if>
</#list>
    </update>
    
    <!--部分修改-->
    <update id="updateSelective" parameterType="${mainObject.domainPackageName}.${mainObject.className}">
		update ${mainObject.tableName}  
		<set>
<#list mainObject.properties as property>
	<#if ! property.isPk && property.name !='createUserid'&& property.name !='createDeptid'&& property.name !='createDate'>
		  	<if test="@Ognl@IsNotEmpty(${property.name})">
				${property.colName}=${r'#'}{${property.name},jdbcType=${property.colType}},
			</if>
	</#if>
</#list>
		 </set>
		where 1=1
<#list mainObject.properties as property>
	<#if  property.isPk>
		<#if property_has_next>
		and FIND_IN_SET(${property.colName},${r'#'}{${property.name},jdbcType=${property.colType}})
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
	<select id="getInfo" resultMap="BaseResultMap" parameterType="${mainObject.domainPackageName}.${mainObject.className}" >
    	select 
    	<include refid="${mainObject.tableName}_column_query" />
    	from 
    	${mainObject.tableName} ${mainObject.objectName}
		where 1=1 
		<include refid="where" /> 
  </select>
  
  <!--查询列表-->
	<select id="getAll" resultMap="BaseResultMap" parameterType="${mainObject.domainPackageName}.${mainObject.className}" >
    	select 
    	<include refid="${mainObject.tableName}_column_query" />
    	from 
    	${mainObject.tableName} ${mainObject.objectName}
		where 1=1 
		<include refid="where" /> 
  	</select>
  	
<#if (mainObject.objectRels?size>0) >  
  	<!--查询列表(内连接)-->
	<select id="getAllInnerJoin" resultMap="BaseResultMap" parameterType="${mainObject.domainPackageName}.${mainObject.className}" >
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
	<select id="getAllLeftJoin" resultMap="BaseResultMap" parameterType="${mainObject.domainPackageName}.${mainObject.className}" >
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

