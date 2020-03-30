package com.lianlian.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML与JavaBean相互转换工具类
 */
public class XMLBeanUtils {
	private static final Logger logger = LoggerFactory.getLogger(XMLBeanUtils.class);

	public static String map2XmlString(Map<String, String> map) {
		String xmlResult = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (String key : map.keySet()) {
			String value = "<![CDATA[" + map.get(key) + "]]>";
			sb.append("<" + key + ">" + value + "</" + key + ">");
		}
		sb.append("</xml>");
		xmlResult = sb.toString();

		return xmlResult;
	}

	/**
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> readStringXmlOut(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			List<Element> list = rootElt.elements();// 获取根节点下所有节点
			for (Element element : list) { // 遍历节点
				map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
			}
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return map;
	}

	/**
	 * 将 XML 字符串转换为 Java 对象
	 * 
	 * @param clazz
	 *            要转换对象的 class
	 * @param xml
	 *            待转换的 xml
	 * @return 转换后的对象
	 */
	public static Object xmlToBean(Class<?> clazz, String xml) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller us = jc.createUnmarshaller();
			return us.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
