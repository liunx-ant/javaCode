package com.qhgrain.createword.util;

import com.qhgrain.createword.service.WordService;

public class CharDealUtil {
	/**
	 * 对xml中的涉及的特殊字符进行处理
	 * 
	 * @param s
	 * @return
	 */
	public static String xmlCharDeal(String s) {
		return s = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replace("\\n", "<w:br/>");
	}

}
