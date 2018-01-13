package com.APITest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;

public class IPAPITest {

	public static void main(String[] args) {
		String host = "https://dm-81.data.aliyun.com";
		String path = "/rest/160601/ip/getIpInfo.json";
		String method = "GET";
		String appcode = "26de6d54ecf3473e94d8da391a10de5f";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		// headers.put("Host", "dm-81.data.aliyun.com");
		// headers.put("Content-Type", "application/json; charset=utf-8");
		headers.put("Authorization", "APPCODE " + appcode);

		Map<String, String> querys = new HashMap<String, String>();
		querys.put("ip", "123.123.123.123");
		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 * 
			 * 调用的方法是自己重写的方法
			 */
			JSONObject jsonObject = HttpUtils.doGet2(host, path, method, headers, querys);
			Iterator<String> iterator = jsonObject.keys();
			while (iterator.hasNext()) {
				// 获得key
				String key = iterator.next();
				// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
				String value = jsonObject.getString(key);
				if(key.equals("data"))
				{
					System.out.println("以下输出IP--API返回的Json：");
					//用来接收json中data的value，并且封装成json对象
					JSONObject jsonObject2 = (JSONObject) jsonObject.fromObject(jsonObject.getString(key));
					Iterator<String> iterator2 = jsonObject2.keys();
					while (iterator2.hasNext()) {
						// 获得key
						String key1 = iterator2.next();
						// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
						String value1 = jsonObject2.getString(key1);
						//只打印data中json的值
						System.out.println(key1 + ":" + value1);
						//System.out.println("key: " + key1 + ",value:" + value1);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}
