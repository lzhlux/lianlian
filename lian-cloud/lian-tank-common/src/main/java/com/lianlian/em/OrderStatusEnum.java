package com.lianlian.em;

/**
 * 收益类型
 * @author thc
 * 2019年7月29日
 */
public enum OrderStatusEnum {
	
	    WAIT("待开团", -1),
	    WAIT_PAY("待付款", 0),
	    WAIT_SEND("待发货",1),
	    WAIT_RECIVE("待收货",2),
	    WAIT_EVALUATE("待评价",3),
	    COMPLETE("交易成功",4),
	    CANCLE("已取消",5),
	    DEL("已删除",6),
	    REFUND("售后", 7),
	    GROUP_ING("拼团中", 8),
	    HAD_REFUND("已退款", 9);
	
		/** 角色名 */
		private String name;
		/** 值 */
		private int value;
		
		private OrderStatusEnum(String name, int value){
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
			for (OrderStatusEnum c : OrderStatusEnum.values()) {
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
		public static OrderStatusEnum getInstance(int value) {
			for (OrderStatusEnum c : OrderStatusEnum.values()) {
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
