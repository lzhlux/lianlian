package com.lianlian.em;
/**
 * 组团状态
 * @author thc
 * 2019年8月2日
 */
public enum GroupStatusEnum {
	

    ING("进行中", 0),
    OK("拼团成功",1),
    FAIL("拼团失败",2),
    WAIT("待开团",-1);

	/** 角色名 */
	private String name;
	/** 值 */
	private int value;
	
	private GroupStatusEnum(String name, int value){
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
		for (GroupStatusEnum c : GroupStatusEnum.values()) {
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
	public static GroupStatusEnum getInstance(int value) {
		for (GroupStatusEnum c : GroupStatusEnum.values()) {
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
