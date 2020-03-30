package com.lianlian.common.redis;

public class RedisConstant {

    public static final String SYS_DICT_KEYS = "sys_dict_keys:%s";//系统字典表字典值缓存

    public static final String SYS_CONFIG_KEYS = "sys_config_keys:%s";//SYS_CONFIG_KEYS 系统参数表缓存
    
    public static final String SYS_CONFIG_GROUP_KEYS = "sys_config_group_keys:%s";//SYS_CONFIG_GROUP_KEYS 系统参数组缓存
    
	public static final Long REDIS_DAY_SECONDS =86400L;	//一天
	
	public static final Long REDIS_HOUR_SECONDS =3600L;	//一个小时
	
	public static final Long REDIS_MINUTE_SECONDS =60L;	//1分钟
	
	public static final Integer REDIS_SEVEN_SECONDS = 2592000;	//30天
	
    public static final String USER_TOKEN_KEY = "user_token:%s";//登录token

    public static final String USER_TOKEN_LIST_KEY = "user_token_list:%s";//用户ID关联的信息
    
    public static final String VERIFY_REQUEST_KEY ="verify_request:%s"; // redis验证请求的key
    
    public static final String REGISTER_CODE_KEY = "register_code:%s";//注册验证码
    
    public static final String LOGIN_CODE_KEY = "login_code:%s";//登录验证码
}
