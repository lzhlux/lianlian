package com.lianlian.common.utils;

import java.text.DecimalFormat;

public class BrowseUtils {
	private static DecimalFormat df = new DecimalFormat("0.00");

	// 浏览次数格式为万 /亿
	public static String browseFormat(String browse) {
		long l = Long.parseLong(browse);
		if (l > 100000000) {
			browse = df.format((float) l / 100000000) + "亿";
		} else if (l > 10000) {
			browse = df.format((float) l / 10000) + "万";
		}
		return browse;
	}

}