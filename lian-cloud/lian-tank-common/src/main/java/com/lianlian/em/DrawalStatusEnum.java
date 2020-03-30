package com.lianlian.em;

/**
 * 提现
 * @author thc
 * 2019年7月29日
 */
public enum DrawalStatusEnum {
	

	    WAIT("待审核", 0),
	    PASS("处理中",1),
	    NO("已处理", 2);
	
		/** 角色名 */
		private String name;
		/** 值 */
		private int value;
		
		private DrawalStatusEnum(String name, int value){
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
			for (DrawalStatusEnum c : DrawalStatusEnum.values()) {
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
		public static DrawalStatusEnum getInstance(int value) {
			for (DrawalStatusEnum c : DrawalStatusEnum.values()) {
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
		
	

	

}
