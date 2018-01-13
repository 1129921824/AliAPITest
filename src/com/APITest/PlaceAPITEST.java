package com.APITest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PlaceAPITEST {
	public static void main(String[] args) {
		String host = "http://txcjsb.market.alicloudapi.com";
		String path = "/image/scene";
		String method = "POST";
		String appcode = "26de6d54ecf3473e94d8da391a10de5f";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		String bodys = "{\"type\":0,\"image_url\":\"http://pic36.photophoto.cn/20150803/0035035709130903_b.jpg\",\"content\":\"\"}";
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
			JSONObject jsonObject = HttpUtils.doPost2(host, path, method, headers, querys, bodys);

			Iterator<String> iterator = jsonObject.keys();
			while (iterator.hasNext()) {
				// 获得key
				String key = iterator.next();
				// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
				String value = jsonObject.getString(key);
				if (key.equals("tags")) {
					System.out.println("格式化tags");
					JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString(key));
					// 遍历json数组
					for (Object js : jsonArray) {
						JSONObject jsonObject2 = JSONObject.fromObject(js);
						Iterator<String> iterator1 = jsonObject2.keys();
						while (iterator1.hasNext()) {
							// 获得key
							String key1 = iterator1.next();
							// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
							String value1 = jsonObject2.getString(key1);
							System.out.println(key1 + ":" + value1);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}
