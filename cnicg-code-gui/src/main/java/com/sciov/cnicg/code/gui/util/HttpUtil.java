package com.sciov.cnicg.code.gui.util;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * @author Rio
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static HttpEntity doPost(String url, List<NameValuePair> params, String jsonParams) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();

		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json"); //
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String transfer = EntityUtils.toString(entity, "UTF-8");
			logger.info(String.format("post %s params %s data %s ,return %s", url, 
					EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"), "UTF-8"),jsonParams,transfer));
			// 关闭连接，释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static HttpEntity doGet(String url, List<NameValuePair> params) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = null;
		// 转换为键值对
		if (params != null && params.size() > 0) {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
			httpGet = new HttpGet(url + "?" + str);
		} else {
			httpGet = new HttpGet(url);
		}

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
				.setConnectionRequestTimeout(5000) // 设置请求超时时间
				.setSocketTimeout(5000).setRedirectsEnabled(true)// 默认允许自动重定向
				.build();
		httpGet.setConfig(requestConfig);

		try {
			// 执行Get请求，
			CloseableHttpResponse response = httpClient.execute(httpGet);
			// 得到响应体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String transfer = EntityUtils.toString(entity, "UTF-8");
				System.out.println(transfer);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static HttpEntity doPost(CloseableHttpClient httpClient,String url, List<NameValuePair> params, String jsonParams) {
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();

		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json"); //
		try {
			if(params != null && params.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			if(jsonParams != null && jsonParams.trim().length() > 0) {
				httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String transfer = EntityUtils.toString(entity, "UTF-8");
			logger.info(String.format("post %s params %s data %s ,return %s", url, params
					,jsonParams,transfer));
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	public static HttpEntity doPostForm(CloseableHttpClient httpClient,String url, List<NameValuePair> params) {
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000).setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();

		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded"); //
		try {
			if(params != null && params.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String transfer = EntityUtils.toString(entity, "UTF-8");
			logger.info(String.format("post %s params %s ,return %s", url, params
					,transfer));
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public static HttpEntity doGet(CloseableHttpClient httpClient,String url, List<NameValuePair> params) throws ClientProtocolException, IOException {
		HttpGet httpGet = null;
		// 转换为键值对
		if (params != null && params.size() > 0) {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
			httpGet = new HttpGet(url + "?" + str);
		} else {
			httpGet = new HttpGet(url);
		}

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
				.setConnectionRequestTimeout(5000) // 设置请求超时时间
				.setSocketTimeout(5000).setRedirectsEnabled(true)// 默认允许自动重定向
				.build();
		httpGet.setConfig(requestConfig);

		try {
			// 执行Get请求，
			CloseableHttpResponse response = httpClient.execute(httpGet);
			// 得到响应体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String transfer = EntityUtils.toString(entity, "UTF-8");
				System.out.println(transfer);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	/**
	 * 获取ip地址
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}

		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
}
