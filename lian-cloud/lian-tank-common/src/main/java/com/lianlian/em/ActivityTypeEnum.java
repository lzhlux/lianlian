package com.lianlian.em;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动类型
 * @author thc
 * 2019年8月13日
 */
public enum ActivityTypeEnum {
	

	    KILL("秒杀", 0),
	    GROUP("拼团",1),
	    BARGAIN("砍价",2),
	    WAN("万人团",3),
	    XMQZ("厦门亲子",4);
	
		/** 角色名 */
		private String name;
		/** 值 */
		private int value;
		
		private ActivityTypeEnum(String name, int value){
			this.setName(name);
			this.setValue(value);
		}

		
		/**
		 * 根据值获取名称
		 *@param value
		 *@return
		 * @author lmh 2016年8月18日
		 */
		public static String getName(int value) {
			for (ActivityTypeEnum c : ActivityTypeEnum.values()) {
				if (c.getValue() == value) {
					return c.name;
				}
			}
			return null;
		}

		/**
		 * 根据值获取枚举对象
		 *@param value
		 *@return
		 * @author lmh 2016年8月18日
		 */
		public static ActivityTypeEnum getInstance(int value) {
			for (ActivityTypeEnum c : ActivityTypeEnum.values()) {
				if (c.getValue() == value) {
					return c;
				}
			}
			return null;
		}
		
		
		/**
		 * get 角色名
		 *@return
		 * @author lmh 2016年8月18日
		 */
		public String getName() {
			return name;
		}

		/**
		 * set 角色名
		 *@param name
		 * @author lmh 2016年8月18日
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * get 值
		 *@return
		 * @author lmh 2016年8月18日
		 */
		public int getValue() {
			return value;
		}

		/**
		 * set 值
		 *@param value
		 * @author lmh 2016年8月18日
		 */
		public void setValue(int value) {
			this.value = value;
		}
		
		public static   Map<Integer, String> getMap(){
			Map<Integer, String> map = new HashMap<Integer, String>();
			for(ActivityTypeEnum o  :ActivityTypeEnum.values()){
				map.put(o.getValue(), o.getName(o.getValue()));
			}
			return   map;
		}
		
	

	

}
