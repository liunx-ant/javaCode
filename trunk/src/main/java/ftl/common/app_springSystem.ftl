<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
 		http://www.springframework.org/schema/context   
  		http://www.springframework.org/schema/context/spring-context-3.2.xsd   
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd   
  		http://www.springframework.org/schema/aop   
  		http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">
	<context:component-scan base-package="${projStructurePath}">
		<context:include-filter type="annotation"
			expression="org.springframework.stereotype.Controller" />
		<context:exclude-filter type="annotation"
			expression="org.springframework.stereotype.Service" />
		<context:exclude-filter type="annotation"
			expression="org.springframework.stereotype.Repository" />
	</context:component-scan>
	<context:annotation-config />
	<context:component-scan base-package="${projStructurePath}">
		<context:exclude-filter type="annotation"
			expression="org.springframework.stereotype.Controller" />
	</context:component-scan>	
	<mvc:annotation-driven/>
	<mvc:default-servlet-handler />	
	<bean id="ky.requestFactory" class="org.springframework.http.client.SimpleClientHttpRequestFactory">
        <!-- 
        <property name="readTimeout" value="60000"/>
        <property name="connectTimeout" value="60000"/>
         -->
    </bean>	
    
	<bean id="restTemplate" class="org.springframework.web.client.RestTemplate">
		<constructor-arg ref="ky.requestFactory"/>		
	</bean>
  
	<bean id="exceptionResolver"
		class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
		<property name="defaultErrorView">
			<value>error</value>
		</property>
		<property name="order" value="0"></property>
	</bean>

	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass"
			value="org.springframework.web.servlet.view.JstlView"></property>
		<property name="prefix" value="/WEB-INF/view/"/>
		<property name="suffix" value=".jsp"></property>
	</bean>
	
	<!-- 读取配置文件 -->
	<bean id="propertiesUtil" class="${projStructurePath}.util.common.PropertiesUtil">
	 	<property name="locations">
			<list>
				<value>WEB-INF/config/env.properties</value>
			</list>
		</property>
        <property name="ignoreUnresolvablePlaceholders" value="true"></property>
	</bean>
	
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="104857600" />
		<property name="maxInMemorySize" value="4096" />
	</bean>
</beans>  
