
{
	"method": {
		"servicePath": "/${applicationName}Service/1.0.0",
		"path": "/${mainObject.objectName}",
		"methods": [
			{
				"type": "post",
				"path": "/add",
				"name": "add",
				"businessName": "新增数据",
				"encode": "UTF-8",
				"desc": "新增数据",
				"businessDesc": "",
				"consumes": "json",
				"returnDesc": "result：\"success\" ; msg: \"操作成功: \" +id.\nresult：\"ex\"; msg: \"操作失败: \"+异常信息 .",
				"produces": "json",
				"params": [
					{
						"name": "modelStr",
						"desc": "<#list mainObject.properties as property><#if property.name !='id'>${property.name}: ${property.title} \n </#if></#list> <#list relObjects as object>${object.objectName} :{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List: [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
						"source": "requestbody",
						"type": "String"
					}
				]
			},
			{
				"type": "post",
				"path": "/update/
				{
					id
				}
				",
				"name": "update",
				"businessName": "修改数据",
				"encode": "UTF-8",
				"desc": "修改数据",
				"businessDesc": "",
				"consumes": "json",
				"returnDesc": "result：\"success\" ; msg: \"操作成功: \" +id.\nresult：\"ex\"; msg: \"操作失败: \"+异常信息 .",
				"produces": "json",
				"params": [
					{
						"name": "id",
						"desc": "参数id",
						"source": "path",
						"type": "String"
					},
					{
						"name": "modelStr",
						"desc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List : [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
						"source": "requestbody",
						"type": "String"
					}
				]
			},
			{
				"type": "get",
				"path": "/query/
				{
					id
				}
				",
				"name": "queryById",
				"businessName": "通过id查询数据",
				"encode": "UTF-8",
				"desc": "根据ID查询数据",
				"businessDesc": "根据ID查询数据",
				"consumes": "json",
				"returnDesc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List : [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
				"produces": "json",
				"params": [
					{
						"name": "id",
						"desc": "路径参数",
						"source": "path",
						"type": "String"
					}
				]
			},
			{
				"type": "post",
				"path": "/queryByInfo",
				"name": "queryByInfo",
				"businessName": "通过对象查询数据",
				"encode": "UTF-8",
				"desc": "根据对象查询数据",
				"businessDesc": "根据对象查询数据",
				"consumes": "json",
				"returnDesc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List : [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
				"produces": "json",
				"params": [
					{
						"name": "modelStr",
						"desc": "<#list mainObject.properties as property>${property.name}: ${property.title}  \n </#list> <#list relObjects as object><#list object.properties as property>${object.objectName}.${property.name}: ${property.title}  \n </#list></#list>",
						"source": "requestbody",
						"type": "String"
					}
				]
			},
			{
				"type": "post",
				"path": "/page/
				{
					start
				}
				/
				{
					size
				}
				",
				"name": "queryAll",
				"businessName": "单表分页查询",
				"encode": "UTF-8",
				"desc": "根据起始页、每页大小、查询条件分页显示",
				"businessDesc": "根据起始页、每页大小、查询条件分页显示",
				"consumes": "json",
				"returnDesc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List : [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
				"produces": "json",
				"params": [
					{
						"name": "start",
						"desc": "起始页",
						"source": "path",
						"type": "Integer"
					},
					{
						"name": "size",
						"desc": "每页数量大小",
						"source": "path",
						"type": "Integer"
					},
					{
						"name": "modelStr",
						"desc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName}{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list>",
						"source": "requestbody",
						"type": "String"
					}
				]
			}
<#if (mainObject.objectRels?size>0) >  ,
			{
				"type": "post",
				"path": "/pageInnerJoin/
				{
					start
				}
				/
				{
					size
				}
				",
				"name": "queryAllInnerJoin",
				"businessName": "分页查询(内连接)",
				"encode": "UTF-8",
				"desc": "根据起始页、每页大小、查询条件分页显示",
				"businessDesc": "根据起始页、每页大小、查询条件分页显示",
				"consumes": "json",
				"returnDesc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List : [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
				"produces": "json",
				"params": [
					{
						"name": "start",
						"desc": "起始页",
						"source": "path",
						"type": "Integer"
					},
					{
						"name": "size",
						"desc": "每页数量大小",
						"source": "path",
						"type": "Integer"
					},
					{
						"name": "modelStr",
						"desc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list>",
						"source": "requestbody",
						"type": "String"
					}
				]
			},
			{
				"type": "post",
				"path": "/pageLeftJoin/
				{
					start
				}
				/
				{
					size
				}
				",
				"name": "queryAllLeftJoin",
				"businessName": "分页查询(左连接)",
				"encode": "UTF-8",
				"desc": "根据起始页、每页大小、查询条件分页显示",
				"businessDesc": "根据起始页、每页大小、查询条件分页显示",
				"consumes": "json",
				"returnDesc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list><#list relObjects as object>${object.objectName}List : [{<#list object.properties as property>${property.name}: ${property.title} \n </#list>}]</#list>",
				"produces": "json",
				"params": [
					{
						"name": "start",
						"desc": "起始页",
						"source": "path",
						"type": "Integer"
					},
					{
						"name": "size",
						"desc": "每页数量大小",
						"source": "path",
						"type": "Integer"
					},
					{
						"name": "modelStr",
						"desc": "<#list mainObject.properties as property>${property.name}: ${property.title} \n </#list>  <#list relObjects as object>${object.objectName} : {<#list object.properties as property>${property.name}: ${property.title} \n </#list>}</#list>",
						"source": "requestbody",
						"type": "String"
					}
				]
			}
</#if>
		]
	},
	"testCase": [
		{
			"output": {
				"result": "success",
				"msg": "操作成功"
			},
			"params": [
				{
					"name": "modelStr",
					"value": ""
				}
			],
			"methodName": "add"
		},
		{
			"output": {
				"result": "success",
				"msg": "操作成功"
			},
			"params": [
				{
					"name": "id",
					"value": ""
				},
				{
					"name": "modelStr",
					"value": ""
				}
			],
			"methodName": "update"
		},
		{
			"output": {
				"result": "success",
			    "data": {
<#list mainObject.properties as property>
					"${property.name}": "${property.title}"  <#if property_has_next>,</#if>
</#list>
<#if (relObjects?size>0) > ,</#if>
<#list relObjects as object>
					"${object.objectName}":{
	<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
	</#list>
				},
</#list>
<#list relObjects as object>
					"${object.objectName}List":[{
	<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
	</#list>
					}]<#if object_has_next>,</#if>
</#list>
				}
			},
			"params": [
				{
					"name": "id",
					"value": ""
				}
			],
			"methodName": "queryById"
		},
		{
			"output": {
				"result": "success",
			    "data": {
<#list mainObject.properties as property>
					"${property.name}": "${property.title}"  <#if property_has_next>,</#if>
</#list>
<#if (relObjects?size>0) > ,</#if>
<#list relObjects as object>
					"${object.objectName}":{
	<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
	</#list>
					},
</#list>
<#list relObjects as object>
					"${object.objectName}List":[{
	<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
	</#list>
					}]<#if object_has_next>,</#if>
</#list>
				}	
			},
			"params": [
				{
					"name": "modelStr",
					"value": ""
				}
			],
			"methodName": "queryByInfo"
		},
		{
			"output": {
				"result": "success",
			    "data": {
					"curPage": 1,
					"data": [
						{
<#list mainObject.properties as property>
					"${property.name}": "${property.title}"  <#if property_has_next>,</#if>
</#list>
<#if (relObjects?size>0) > ,</#if>
<#list relObjects as object>
					"${object.objectName}":{
	<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
	</#list>
					}<#if object_has_next>,</#if>
</#list>
						}
					],
					"first": 1,
					"last": 2,
					"next": 2,
					"pageLine": 10,
					"previous": 1,
					"startIndex": 0,
					"totalPage": 2,
					"totalRow": 20
				}	
			},
			"params": [
				{
					"name": "start",
					"value": ""
				},
				{
					"name": "size",
					"value": ""
				},
				{
					"name": "modelStr",
					"value": ""
				}
			],
			"methodName": "queryAll"
		}
<#if (mainObject.objectRels?size>0) >  ,
		{
			"output": {
				"result": "success",
			    "data": {
					"curPage": 1,
					"data": [
						{
	<#list mainObject.properties as property>
					"${property.name}": "${property.title}"  <#if property_has_next>,</#if>
</#list>
<#if (relObjects?size>0) > ,</#if>
	<#list relObjects as object>
					"${object.objectName}":{
		<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
		</#list>
					}<#if object_has_next>,</#if>
	</#list>
						}
					],
					"first": 1,
					"last": 2,
					"next": 2,
					"pageLine": 10,
					"previous": 1,
					"startIndex": 0,
					"totalPage": 2,
					"totalRow": 20
				}
			},
			"params": [
				{
					"name": "start",
					"value": ""
				},
				{
					"name": "size",
					"value": ""
				},
				{
					"name": "modelStr",
					"value": ""
				}
			],
			"methodName": "queryAllInnerJoin"
		},
		{
			"output": {
			    "result": "success",
			    "data": {
					"curPage": 1,
					"data": [
						{
	<#list mainObject.properties as property>
					"${property.name}": "${property.title}" <#if property_has_next>,</#if>
</#list>
<#if (relObjects?size>0) > ,</#if>
	<#list relObjects as object>
					"${object.objectName}":{
		<#list object.properties as property>
						"${property.name}": "${property.title}" <#if property_has_next>,</#if>
		</#list>
					}<#if object_has_next>,</#if>
	</#list>
						}
					],
					"first": 1,
					"last": 2,
					"next": 2,
					"pageLine": 10,
					"previous": 1,
					"startIndex": 0,
					"totalPage": 2,
					"totalRow": 20
					}
				},
			"params": [
				{
					"name": "start",
					"value": ""
				},
				{
					"name": "size",
					"value": ""
				},
				{
					"name": "modelStr",
					"value": ""
				}
			],
			"methodName": "queryAllLeftJoin"
		}
</#if>
	]
}

