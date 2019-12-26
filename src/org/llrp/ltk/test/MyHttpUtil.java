package org.llrp.ltk.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by stone on 2017/3/28.
 */
public class MyHttpUtil {

	public static JSONObject httpPost12(String url, JSONObject jsonParam) {
		// post请求返回结果
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				.setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时�?
		/*
		 * RequestConfig requestConfig = RequestConfig.custom()
		 * .setSocketTimeout(2000).setConnectTimeout(2000).build();
		 * httpPost.setConfig(requestConfig);
		 */
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发�?成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数�?
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					System.out.println("=================str:" + str);
					jsonResult = JSON.parseObject(str);
				} catch (Exception e) {
					System.out.println("post请求提交失败:" + url);
					// logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			System.out.println("post请求提交失败:" + url);
			// logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		String strResult = "";
		try {
			// CloseableHttpClient client = HttpClients.createDefault();
			RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(
					5000).setStaleConnectionCheckEnabled(true).build();
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

			// 发�?get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);

			/** 请求发�?成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数�?**/
				strResult = EntityUtils.toString(response.getEntity());
				jsonResult = JSON.parseObject(strResult);
				System.out.println("-------------strResult:" + strResult);
				/** 把json字符串转换成json对象 **/
				// jsonResult = JSONObject.parseObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				System.out.println("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			System.out.println("get请求提交失败:" + url);
			System.out.println(e);
		}
		return jsonResult;
	}

	public static void main(String[] args) {
		System.out.print("==================");
		JSONObject result = new JSONObject();
		String url = "http://10.2.43.234:8080/rmweb/be/login.rfid?yhdh=admin&mm=passw0rd&aqmkxh=HA0100200548";
		JSONObject jo = new JSONObject();
		jo.put("yhdh", "admin");
		jo.put("mm", "passw0rd");
		jo.put("aqmkxh", "HA0100200548");

		result = MyHttpUtil.httpPost12(url, jo);
		System.out.println("result===========" + result);
	}
}
