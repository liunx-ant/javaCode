package com.qhgrain.createword;

import com.qhgrain.createword.service.WordService;

/**
 * 生成word
 * 
 * @author dwl
 * @version 1.0
 * @date 2016年8月5日 Copyright 青海粮食云项目组
 */
public class CreateWord {
	/**
	 * 生成word的主方法
	 * 
	 * @author dwl
	 * @param args
	 * @throws Exception
	 * @date 2016年8月5日
	 */
	public static void main(String[] args) throws Exception {
		WordService wordService = new WordService();
		wordService.creatWords();
	}
}