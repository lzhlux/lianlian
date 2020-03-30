package com.lianlian.util;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * 导出Execl数据工具类
 */
public class ExcelUtil {
	/**
	 * 导出数据公共处理
	 * @param request 请求
	 * @param response 响应
	 * @param data 导出的数据
	 * @param templateName 模板文件名
	 * @param fileName 导出文件名
	 */
	public static void exportExcel(HttpServletResponse response, Map<String, Object> data, String templateName, String fileName){
		XLSTransformer transformer = new XLSTransformer();
		//准备输出流
        OutputStream os=null;
        FileInputStream in = null;
        try {
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName+ ".xls").getBytes("GBK"), "ISO-8859-1"));
            os = response.getOutputStream();
            
            Resource resource = new ClassPathResource("template/" + templateName);
    		File file = resource.getFile();
            //获得模板的输入流
            in = new FileInputStream(file);
            //将beans通过模板输入流写到workbook中
            Workbook workbook = transformer.transformXLS(in, data);
            //将workbook中的内容用输出流写出去
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try {
                	in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
