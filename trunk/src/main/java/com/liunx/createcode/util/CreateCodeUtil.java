package com.liunx.createcode.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liunx.common.util.StringUtil;

public class CreateCodeUtil {

	public static Map<String,Object> createCodeObjectsToMap(CreateCodeObjects createCodeObjects) {
	    Map<String, Object> rootMap = new HashMap<String, Object>();
	    rootMap.put("nowDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // 当前日期
        rootMap.put("author", createCodeObjects.getAuthor());
        rootMap.put("version", createCodeObjects.getVersion());
        rootMap.put("copyright", createCodeObjects.getCopyright());
        rootMap.put("applicationName", CreateCodeUtil.getModuleNameByTableName(createCodeObjects.getMainObject().getTableName()));
        //主对象
        Map mainObject=createCodeObjecToMap(createCodeObjects.getMainObject());
        //关联对象
        List relObjects=getObjectsMapList(createCodeObjects.getRelObjects());
        //所有对象
        List<Map> objects=getObjectsMapList(createCodeObjects.getObjects());
        //关联关系
        List<Map> objectRels=(List) mainObject.get("objectRels");
        //将对象放入关联关系里
        for(Map objectRelMap:objectRels){
             for(Map object:objects){
                 //如果relClassName 跟 对象名称一样,放入relObject
                 if(object.get("className").equals(objectRelMap.get("relClassName"))){
                     objectRelMap.put("relObject", object);
                 }
                 //如果relToClassName 跟 对象名称一样,放入relToObject
                 if(object.get("className").equals(objectRelMap.get("relToClassName"))){
                     objectRelMap.put("relToObject", object);
                 }
             }
        }
        rootMap.put("mainObject",mainObject);
        rootMap.put("relObjects", relObjects);
        rootMap.put("objects", objects);
        return rootMap;
	}

	public static Map createCodeObjecToMap(CreateCodeObject createCodeObject) {
		Map objectMap = objectToMap(createCodeObject);
		// objectRelMaps
		List<ObjectRel> objectRels = createCodeObject.getObjectRels();
		List objectRelMaps = new ArrayList();
		for (int i = 0; i < objectRels.size(); i++) {
			ObjectRel objectRel = (ObjectRel) objectRels.get(i);
			Map objectRelMap = objectToMap(objectRel);
			objectRelMaps.add(objectRelMap);
		}
		objectMap.put("objectRels", objectRelMaps);
		// propertyMaps
		List<Property> properties = createCodeObject.getProperties();
		List<Map> propertyMaps = new ArrayList();
		for (int i = 0; i < properties.size(); i++) {
			// propertyMap
			Property property = properties.get(i);
			Map propertyMap = objectToMap(property);
			propertyMap.put("propertyEdit", objectToMap(property.getPropertyEdit()));
			propertyMap.put("propertyNew", objectToMap(property.getPropertyNew()));
			propertyMap.put("propertyQuery", objectToMap(property.getPropertyQuery()));
			propertyMap.put("propertyList", objectToMap(property.getPropertyList()));
			propertyMap.put("propertyView", objectToMap(property.getPropertyView()));
			// type是通过 colType转换来的，在objectToMap的时候，由于执行顺序的的问题，可能转换错误，所以手动put
			propertyMap.put("type", property.getType());
			// propertyMaps
			propertyMaps.add(propertyMap);
		}
		objectMap.put("properties", properties);
		// objectName是通过className转换来的，在objectToMap的时候，由于执行顺序的的问题，可能转换错误，所以手动put
		objectMap.put("objectName", createCodeObject.getObjectName());
		return objectMap;
	}

	public static List getObjectsMapList(List<CreateCodeObject> createCodeObjectList) {
		Map<String, Object> objectsMap = new HashMap();
		List<Map> createCodeObjectMaps = new ArrayList();
		for (int i = 0; i < createCodeObjectList.size(); i++) {
			CreateCodeObject createCodeObject = createCodeObjectList.get(i);
			createCodeObjectMaps.add(createCodeObjecToMap(createCodeObject));
		}

		return createCodeObjectMaps;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (obj == null) {
				return null;
			}
			Field[] declaredFields = obj.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (IllegalArgumentException e) {
			System.out.println("转MAP出错！");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("转MAP出错！！");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将table的名字转换为class的名字
	 * 
	 * @param str
	 * @return
	 */
	public static String tableNameToObjectClassName(String tableName) {
		String objectClassName = "";
		String tableNameSp[] = tableName.split("_");
		//
		for (int i = 1; i < tableNameSp.length; i++) {
			objectClassName += StringUtil.firstCharUpper(tableNameSp[i].toLowerCase());
		}
		return objectClassName;
	}

	/**
	 * 将列名转换为属性名
	 * 
	 * @param str
	 * @return
	 */
	public static String colNameToPropertyName(String colName) {
		String propertyName = "";
		String propertyNameSp[] = colName.split("_");
		for (int i = 0; i < propertyNameSp.length; i++) {
			propertyName += StringUtil.firstCharUpper(propertyNameSp[i].toLowerCase());
		}
		return StringUtil.firstCharLower(propertyName);
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public static String getModuleNameByTableName(String tableName) {
		String moduleName = "";
		String tableNameSp[] = tableName.split("_");
		if (tableNameSp.length > 1) {
			moduleName = tableNameSp[1].toLowerCase();
		}
		return moduleName;
	}
}
