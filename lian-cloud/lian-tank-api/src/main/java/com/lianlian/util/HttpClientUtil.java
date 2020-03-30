package com.lianlian.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;

@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String doGet(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {
                HttpEntity entity = response.getEntity();    
                if (entity != null) {  
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return null;
	} 
	
	public static void doGetRes(String url, String fileName){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {
                HttpEntity entity = response.getEntity();    
                if (entity != null) {  
    				byte[] data = EntityUtils.toByteArray(entity);
    				//图片存入磁盘
    				FileOutputStream fos = new FileOutputStream(fileName);
    				fos.write(data);
    				fos.close();
                }
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
	}
	
	public static String postHttplient(String url, String xmlInfo) {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		try {
			StringEntity stringEntity = new StringEntity(xmlInfo, "UTF-8");
			httppost.setEntity(stringEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 需要加密执行的
	 * 
	 * @param url
	 * @param xmlInfo
	 * @return
	 * @throws Exception
	 */
	public static String postHttplientNeedSSL(String url, String xmlInfo, String cretPath, String mrchId)
			throws Exception {
		// 选择初始化密钥文件格式
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// 得到密钥文件流
		FileInputStream instream = new FileInputStream(new ClassPathResource(cretPath).getFile());
		try {
			// 用商户的ID 来解读文件
			keyStore.load(instream, mrchId.toCharArray());
		} finally {
			instream.close();
		}
		// 用商户的ID 来加载
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mrchId.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		// 用最新的httpclient 加载密钥
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		StringBuffer ret = new StringBuffer();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(xmlInfo, "utf-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			BufferedReader bufferedReader = null;
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						ret.append(text);
					}
				}
				EntityUtils.consume(entity);
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return ret.toString();
	}
}
