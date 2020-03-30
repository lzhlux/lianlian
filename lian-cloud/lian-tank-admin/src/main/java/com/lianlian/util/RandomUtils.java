package com.lianlian.util;

import java.util.UUID;

/**
 * 随机生成十六位订单号
 * 
 * @author Administrator
 *
 */
public class RandomUtils {
	public static String getOrderIdByUUId() {
		int machineId = 1;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		// 有可能是负数
		if (hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		//          0 代表前面补充0
		//          4 代表长度为4
		//          d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}
}
