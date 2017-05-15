package com.qhgrain.createword.config;

import com.qhgrain.common.util.PathUtil;

/**
 * 生成文档的基础配置信息
 * 
 * @author dwl
 *
 */
public class BaseConfig {

	// 忽略备注解析的文件列表
	public static String[] IGNORE_FILE_LIST = { "*VO.java" };

	// ServiceImpl备注解析的文件列表
	public static String[] SERVICE_FILE_LIST = { "InstorageServiceImpl.java" ,"LoadingOperationServiceImpl.java"};

	// Mapper备注解析的文件列表
	public static String[] DAO_FILE_LIST = { "InstorageVerifyMapper.java" ,"LoadingOperationMapper.java"};

	// Controller 备注解析的文件列表
	public static String[] CONTROLLER_FILE_LIST = { "LoadingOperationController.java","InStorageController.java" };

	// 实体类备注解析的文件列表
	public static String[] ENTITY_FILE_LIST = { "InstorageVerify.java","LoadingOperation.java" };

	// JS备注解析的文件列表
	public static String[] JS_FILE_LIST = { "*.js" };

	// FTL模板的文件地址
	public static String FTL_FOLDER_PATH = PathUtil.getClasspath() + "ftl";

	// 文件生成地址
	public static String WORD_FOLDER_ROOT_PATH = "code/word/";

	// 详细文档中的项目名称
	public static String WORD_INFO_PROJECT_NAME = "青海省粮食信息智能化建设项目";

	// 详细文档中的版本
	public static String WORD_INFO_APP_VERSION = "1.0";

	// 详细文档中的版本
	public static String WORD_INFO_API_VERSION = null;

	// 详细文档中的作者
	public static String WORD_INFO_AUTHOR = null;//

	// 详细文档中的标题
	public static String WORD_INFO_TITLE = null;// 例：货位管理服务

	// 详细文档中的服务名称 要以服务为结尾
	public static String WORD_INFO_SERVICE_NAME = null;// 例：货位管理服务

	// 详细文档中的文件名
	public static String WORD_INFO_FILE_NAME = null;// 例：货位管理服务
	
	// ----XML跟目录配置
	public static String XML_FOLDER_PATH = PathUtil.getClasspath() + "xml/word";

}
