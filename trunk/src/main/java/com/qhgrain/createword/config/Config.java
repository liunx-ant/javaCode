package com.qhgrain.createword.config;

import java.util.List;

/**
 * 生成文档的配置信息
 * 
 * @author dwl
 *
 */
public class Config extends BaseConfig {

	// 所要生成的文档是app还是api 阈值[app,api]
	public static String WORD_TYPE = "";

	// app 或api manager中的项目编码
	public static String PROJECT_CODE = "";

	// 生成app或api文档时，所需SERVICE类的文件目录
	public static List<String> SERVICE_FILE_PATHS;

	// 生成app或api文档时，所需DAO类的文件目录
	public static List<String> DAO_FILE_PATHS;

	// 生成app或api文档时，所需COTROLLOR类的文件目录
	public static List<String> CONTROLLER_FILE_PATHS;

	// 生成api文档时，所需实体类的文件目录
	public static List<String> ENTITY_FILE_PATHS;

	// 生成app文档时，所需的js文件目录
	public static List<String> JS_FILE_PATHS;
}
