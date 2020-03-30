package com.lianlian.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * 发给微信服务消息的json
 */
public class JsonUtils {
	/**
	 * 
	 * @param touser 用户openid
	 * @param templateId 模板id
	 * @param fromId 前端穿的form_id
	 * @param v1     抽奖提醒
	 * @param v2     奖品名称
	 * @param v3     抽奖日期
	 * @return
	 */
	public static String getJsonString(String touser,String templateId,String formId,String v1,String v2, String v3) {
		WxTemplate t = new WxTemplate();
		t.setTouser(touser);
		t.setTemplate_id(templateId);
		t.setForm_id(formId);

		Map<String,TemplateData> m = new HashMap<String,TemplateData>();

		m.put("keyword1", new TemplateData(v1));
		m.put("keyword2", new TemplateData(v2));
		m.put("keyword3", new TemplateData(v3));
		t.setData(m);
		
		return JSONObject.fromObject(t).toString();
	}
}
