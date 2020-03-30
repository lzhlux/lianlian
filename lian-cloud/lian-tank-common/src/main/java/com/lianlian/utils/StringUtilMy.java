package com.lianlian.utils;

import java.text.NumberFormat;

public class StringUtilMy {

	public static String shouWei(String str) {
		if (str.length() == 1) {
			str = "*" + str;
		}
		if (str.length() == 2) {
			str = str.replace(str.substring(1), "*");
		}
		if (str.length() > 2) {
			str = str.replace(str.substring(1, str.length() - 1), "**");
		}
		return str;
	}

	public static String baifenbi(int i, int j) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) i / (float) j * 100);
		return result;
	}
}
