package com.qhgrain.createword.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qhgrain.common.util.Freemarker;
import com.qhgrain.createword.config.Config;
import com.qhgrain.createword.util.CreateWordConfig;
import com.qhgrain.createword.util.note.CodeNoteUtil;
import com.qhgrain.createword.util.word.WordApi;
import com.qhgrain.createword.util.word.WordApp;
import com.qhgrain.createword.util.word.WordInfo;

/**
 * 类注释
 * 
 * @author dwl
 * @version 1.0
 * @date 2016年8月5日 Copyright 青海粮食云项目组
 */

public class WordService {
	private WordDbService wordDbService = new WordDbService();
	private WordInfoService wordInfoService = new WordInfoService();
	private WordApiProtocolService wordApiMethodService = new WordApiProtocolService();
	Map<String, Object> rootMap = new HashMap<String, Object>();

	public void creatWords() throws Exception {
		List<CreateWordConfig> createWordConfigs = getCreateWordConfigs(getXmlFile());
		for (CreateWordConfig createWordConfig : createWordConfigs) {
			initConfig(createWordConfig);
			creatWord();
		}
	}

	public void creatWord() throws Exception {
		new WordDbService();
		if (Config.WORD_TYPE.equals("api")) {
			setRootMaoByWordApi();
		} else if (Config.WORD_TYPE.equals("app")) {
			setRootMaoByWordApp();
		}
		createFileByFtl(rootMap);
	}

	/**
	 * 初始化config
	 * 
	 * @param createWordConfig
	 */
	private void initConfig(CreateWordConfig createWordConfig) {
		Config.WORD_TYPE = createWordConfig.getWrodType();
		Config.PROJECT_CODE = projectCodeDeal(createWordConfig.getProjectCode());
		Config.SERVICE_FILE_PATHS = createWordConfig.getServiceFilePaths();
		Config.DAO_FILE_PATHS = createWordConfig.getDaoFilePaths();
		Config.CONTROLLER_FILE_PATHS = createWordConfig.getControllerFilePaths();
		Config.ENTITY_FILE_PATHS = createWordConfig.getEntityFilePaths();
		Config.JS_FILE_PATHS = createWordConfig.getJsFilePaths();
	}

	/**
	 * 因为数据库中存储的projectCode没有app和api开头的标示，所以要清除
	 * 
	 * @param projectCode
	 * @return
	 */
	private String projectCodeDeal(String projectCode) {
		if (projectCode.length() > 3) {
			if (projectCode.startsWith("app")) {
				projectCode = projectCode.replace("app", "");
			}
			if (projectCode.startsWith("api")) {
				projectCode = projectCode.replace("api", "");
			}
		}
		return projectCode;
	}

	/**
	 * 通过xml获得批量配置
	 * 
	 * @param xmlFile
	 * @return
	 * @throws Exception
	 * @throws DocumentException
	 */

	private List<CreateWordConfig> getCreateWordConfigs(File xmlFile) throws Exception {
		List<CreateWordConfig> createWordConfigs = new ArrayList<CreateWordConfig>();
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(xmlFile);
		} catch (DocumentException e) {
			System.out.println("配置文件命名规则为‘计算机名.xml’，目录为src.main.java.xml.word，请在此目录下创建自己的配置文件，此计算机目前为" + getSysUserName() + "。");
			throw new Exception("获取文件失败，请检查工程目录src.main.java.xml.word目录下是否存在" + xmlFile.getName());
		}
		Element rootElement = doc.getRootElement();
		for (Iterator iteratorObject = rootElement.elementIterator("api"); iteratorObject.hasNext();) {
			CreateWordConfig apiCreateWordConfig = new CreateWordConfig();
			Element objectElement = (Element) iteratorObject.next();
			apiCreateWordConfig.setWrodType("api");
			apiCreateWordConfig.setProjectCode(objectElement.element("projectCode").getTextTrim());
			apiCreateWordConfig.setDaoFilePaths(getFilePaths(objectElement, "daoFilePath"));
			apiCreateWordConfig.setServiceFilePaths(getFilePaths(objectElement, "serviceFilePath"));
			apiCreateWordConfig.setEntityFilePaths(getFilePaths(objectElement, "entityFilePath"));
			createWordConfigs.add(apiCreateWordConfig);
		}
		for (Iterator iteratorObject = rootElement.elementIterator("app"); iteratorObject.hasNext();) {
			CreateWordConfig appCreateWordConfig = new CreateWordConfig();
			Element objectElement = (Element) iteratorObject.next();
			appCreateWordConfig.setWrodType("app");
			appCreateWordConfig.setProjectCode(objectElement.element("projectCode").getTextTrim());
			appCreateWordConfig.setControllerFilePaths(getFilePaths(objectElement, "controllerFilePath"));
			appCreateWordConfig.setJsFilePaths(getFilePaths(objectElement, "jsFilePath"));
			createWordConfigs.add(appCreateWordConfig);
		}
		return createWordConfigs;
	}

	/**
	 * 获得多个相同名称的元素
	 * 
	 * @param pElement
	 * @param elementName
	 * @return
	 */
	private List<String> getFilePaths(Element pElement, String elementName) {
		List<String> filePaths = new ArrayList<String>();
		for (Iterator filePathIteratorObject = pElement.elementIterator(elementName); filePathIteratorObject.hasNext();) {
			Element filePathElement = (Element) filePathIteratorObject.next();
			filePaths.add(pathDeal(filePathElement.getTextTrim()));
		}
		return filePaths;
	}

	/**
	 * 对配置的路径进行处理
	 * 
	 * @param path
	 * @return
	 */

	public String pathDeal(String path) {
		return path.replace("\\", "/");
	}

	/**
	 * 获得xml
	 * 
	 * @return
	 * @throws Exception
	 */
	public File getXmlFile() throws Exception {
		String xmlName = getSysUserName() + ".xml";
		File xmlFile = new File(Config.XML_FOLDER_PATH + "/" + xmlName);
		return xmlFile;
	}

	/**
	 * 获得计算机名称
	 * 
	 * @return
	 */

	public String getSysUserName() {
		Map<String, String> map = System.getenv();
		// 获取用户名;
		String sysUserName = map.get("USERNAME");
		return sysUserName;
	}

	/**
	 * 生成app文档
	 */

	private void setRootMaoByWordApp() throws Exception {
		WordApp wordApp = getWordApp();
		// 文档信息
		rootMap.put("wordInfo", wordInfoService.getWordInfoMap(wordApp.getWordInfo()));
		rootMap.put("controllerCodeNoteClasses", CodeNoteUtil.getCodeNoteClassMaps(wordApp.getControllerCodeNoteClasses()));
		rootMap.put("jsCodeNoteClasses", CodeNoteUtil.getCodeNoteClassMaps(wordApp.getJsCodeNoteClasses()));
		rootMap.put("appVersionApiWordInfos", wordInfoService.getWordInfoMaps(wordApp.getAppVersionApiWordInfos()));
		rootMap.put("mainApiWordInfo", wordInfoService.getWordInfoMap(wordApp.getMainApiWordInfo()));
		rootMap.put("appVersionApiQtWordInfos", wordInfoService.getWordInfoMaps(wordApp.getAppVersionApiQtWordInfos()));
	}

	/**
	 * 生成api文档
	 * 
	 * @throws Exception
	 */

	private void setRootMaoByWordApi() throws Exception {
		WordApi wordApi = getWordApi();
		// 文档信息
		rootMap.put("wordInfo", wordInfoService.getWordInfoMap(wordApi.getWordInfo()));
		rootMap.put("dbName", wordApi.getDbName());
		rootMap.put("wordApiProtocols", wordApiMethodService.wordApiProtocolsToMap(wordApi.getWordApiProtocols()));
		rootMap.put("wordTableObjects", wordDbService.getWordTableObjectMaps(wordApi.getWordTableObjects()));
		rootMap.put("serviceCodeNoteClasses", CodeNoteUtil.getCodeNoteClassMaps(wordApi.getServiceCodeNoteClasses()));
		rootMap.put("daoCodeNoteClasses", CodeNoteUtil.getCodeNoteClassMaps(wordApi.getDaoCodeNoteClasses()));
		rootMap.put("entityCodeNoteClasses", CodeNoteUtil.getCodeNoteClassMaps(wordApi.getEntityCodeNoteClasses()));
	}

	private WordApp getWordApp() throws Exception {
		WordApp wordApp = new WordApp();
		System.out.println("获取文档信息中。。。");
		wordApp.setWordInfo(wordInfoService.getWordInfo());
		System.out.println("获取文档信息成功！");
		// controller说明
		System.out.println("获取controller说明信息中。。。");
		wordApp.setControllerCodeNoteClasses(CodeNoteUtil.getCodeNoteClasses(Config.CONTROLLER_FILE_PATHS, Config.CONTROLLER_FILE_LIST));
		System.out.println("获取controller说明成功！");
		// JS说明
		System.out.println("获取JS说明信息中。。。");
		wordApp.setJsCodeNoteClasses(CodeNoteUtil.getJsCodeNoteClasses(Config.JS_FILE_PATHS, Config.JS_FILE_LIST));
		System.out.println("获取JS说明信息成功！");
		// 服务依赖
		System.out.println("获取服务依赖信息中。。。");
		// ...先获得所有的依赖服务
		List<WordInfo> appVersionApiWordInfos = wordInfoService.getAppVersionApiWordInfos(Config.PROJECT_CODE);
		wordApp.setAppVersionApiWordInfos(appVersionApiWordInfos);
		// ...获得主服务API
		WordInfo mainApiWordInfo = wordInfoService.getMainApiWordInfo(appVersionApiWordInfos, wordApp.getWordInfo().getServiceName());
		wordApp.setMainApiWordInfo(mainApiWordInfo);
		// ...获得非主服务API
		List<WordInfo> appVersionApiQtWordInfos = wordInfoService.getAppVersionApiQtWordInfos(appVersionApiWordInfos, mainApiWordInfo);
		wordApp.setAppVersionApiQtWordInfos(appVersionApiQtWordInfos);
		System.out.println("获取服务依赖信息成功！");
		return wordApp;
	}

	/**
	 * 生成wordApi
	 * 
	 * @return
	 * @throws Exception
	 */

	private WordApi getWordApi() throws Exception {
		WordApi wordApi = new WordApi();
		System.out.println("获取api数据库信息中。。。");
		WordDbService wordDbService = new WordDbService();
		System.out.println("获取api数据库信息成功！");
		wordApi.setDbName(wordDbService.getDbInfo().getName());
		System.out.println("获取文档信息中。。。");
		wordApi.setWordInfo(wordInfoService.getWordInfo());
		System.out.println("获取文档信息成功！");
		// 服务设计
		System.out.println("获取服务设计信息中。。。");
		wordApi.setWordApiProtocols(wordApiMethodService.getWordApiProtocols(Config.PROJECT_CODE));
		System.out.println("获取服务设计信息成功！");
		// service说明
		System.out.println("获取service说明信息中。。。");
		wordApi.setServiceCodeNoteClasses(CodeNoteUtil.getCodeNoteClasses(Config.SERVICE_FILE_PATHS, Config.SERVICE_FILE_LIST));
		System.out.println("获取service说明信息成功！");
		// dao说明
		System.out.println("获取dao说明信息中。。。");
		wordApi.setDaoCodeNoteClasses(CodeNoteUtil.getCodeNoteClasses(Config.DAO_FILE_PATHS, Config.DAO_FILE_LIST));
		System.out.println("获取dao说明信息成功！");
		// 实体说明
		System.out.println("获取实体说明信息中。。。");
		wordApi.setEntityCodeNoteClasses(CodeNoteUtil.getEntityCodeNoteClasses(Config.ENTITY_FILE_PATHS, Config.ENTITY_FILE_LIST));
		System.out.println("获取实体说明信息成功！");
		// 数据库表
		System.out.println("获取数据库表信息中。。。");
		wordApi.setWordTableObjects(wordDbService.getWordTableObjects());
		System.out.println("获取数据库表信息成功！");
		return wordApi;
	}

	/**
	 * 生成文件
	 * 
	 * @param rootMap
	 * @throws Exception
	 */
	private void createFileByFtl(Map<String, Object> rootMap) throws Exception {
		Map wordInfo = (HashMap) rootMap.get("wordInfo");
		String fileName = Config.WORD_TYPE + "_" + wordInfo.get("fileName") + "详细设计文档.doc";
		Freemarker.printFile(Config.WORD_TYPE + ".ftl", rootMap, fileName, Config.WORD_FOLDER_ROOT_PATH, Config.FTL_FOLDER_PATH + "/word");
	}
}
