package com.lianlian.common.utils;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;

public class IkanalyzerUtils {

	public static StringBuffer sendGet(String text) throws Exception {

		// 独立Lucene实现
		StringReader re = new StringReader(text);
		IKSegmenter ik = new IKSegmenter(re, true);
		Lexeme lex = null;
		StringBuffer str = new StringBuffer();
		try {
			while ((lex = ik.next()) != null) {
				String s = lex.getLexemeText() + ",";
				str.append(s);
			}
			
		} catch (Exception e) {
		}

		return str;
	}


	public static  String[]   strToArray(String text){
		String[] names=null;
		try {
			StringBuffer stringBuffer = sendGet(text);
			String s = stringBuffer.toString();

			if(s!=null&&s.equals("")==false){
				names = s.split(",");
			}
			return names;
		} catch (Exception e) {
		}
		return null;

	}

}
