<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:jee="http://www.springframework.org/schema/jee"
	xsi:schemaLocation="http://www.springframework.org/schema/beans   
  http://www.springframework.org/schema/beans/spring-beans-3.2.xsd   
  http://www.springframework.org/schema/tx   
  http://www.springframework.org/schema/tx/spring-tx-3.2.xsd    
  http://www.springframework.org/schema/context   
  http://www.springframework.org/schema/context/spring-context-3.2.xsd   
  http://www.springframework.org/schema/aop   
  http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
  http://www.springframework.org/schema/jee   
  http://www.springframework.org/schema/jee/spring-jee-3.2.xsd">
  	
	<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="systemPropertiesMode" value="2"/>
		<property name="location" value="WEB-INF/config/env.properties"></property>
	</bean>
	<context:annotation-config />
	<context:component-scan base-package="${projStructurePath}">
	</context:component-scan>
		<!--数据库连接池 根据实际情况调整参数-->
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">   
	    <!-- 基本属性 url、user、password -->  
	    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
	    <property name="url" value="jdbc:mysql://${r'$'}{PAASOS_MYSQL_HOST_IP}:${r'$'}{PAASOS_MYSQL_HOST_PORT}/${r'$'}{PAASOS_MYSQL_DATABASE}?useUnicode=true&amp;characterEncoding=utf-8"/>  
	    <property name="username" value="${r'$'}{PAASOS_MYSQL_USERNAME}" />  
	    <property name="password" value="${r'$'}{PAASOS_MYSQL_PASSWORD}" />  
	        
	    <!-- 配置初始化大小、最小、最大 -->  
	    <property name="initialSize" value="10" />  
	    <property name="minIdle" value="1" />   
	    <property name="maxActive" value="20" />  
	   
	    <!-- 配置获取连接等待超时的时间 -->  
	    <property name="maxWait" value="60000" />  
	   
	    <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->  
	    <property name="timeBetweenEvictionRunsMillis" value="60000" />  
	   
	    <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->  
	    <property name="minEvictableIdleTimeMillis" value="300000" />  
	    
	    <property name="validationQuery" value="SELECT 'x'" />  
	    <property name="testWhileIdle" value="true" />  
	    <property name="testOnBorrow" value="false" />  
	    <property name="testOnReturn" value="false" />  
	   
	    <!-- 打开PSCache，并且指定每个连接上PSCache的大小   
	    <property name="poolPreparedStatements" value="true" />  
	    <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />  
	   -->
	    <!-- 配置监控统计拦截的filters，去掉后监控界面sql无法统计
	    <property name="filters" value="stat" />   
		 -->  
	</bean>  
	
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="mapperLocations" value="classpath:mapper/*.xml" />
		<property name="configLocation" value="WEB-INF/config/mybatis.xml"/>
	</bean>
	
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	  <property name="basePackage" value="${projStructurePath}" />
	  <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
	</bean> 	
	
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
		p:dataSource-ref="dataSource" />	
	<tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true"/>	
</beans>  
