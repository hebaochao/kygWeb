package com.it.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

public class FastjsonTools {

	/***
	 * 把任意 Object对象解析为FASTSON数据
	 * 
	 * @param value
	 * @return
	 */
	public static String CreategsonString(Object value) {
		return JSON.toJSONString(value);
	}

	/***
	 * 根据json数据 还原解析出一个person对象 使用fastjson
	 * 
	 * @param urlpath
	 * @return
	 */
	// 使用反射
	public static <T> T getObject(String gsonString, Class<T> cls) {
		T t = null;
		t = JSON.parseObject(gsonString, cls);
		return t;
	}

	// 使用泛型
	/***
	 * 
	 * 把json数据解析还原为list<person>
	 */
	public static <T> List<T> getObjectlist(String gsonString, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		list = JSONArray.parseArray(gsonString, cls);
		return list;

	}

	/****
	 * object 是基本数据类型 自定类型 使用通用泛型T
	 */

	/***
	 * 把List<Map<String,Object>>数据还原
	 */

	public static List<Map<String, Object>> getlistmap(String gsonString) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		listmap = JSONArray.parseObject(gsonString,
				new TypeReference<List<Map<String, Object>>>() {
				});
		return listmap;
	}

	public static Map<String, String> getParams(InputStream ins) {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(ins,
					"UTF-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			// 解析流
			String str = URLDecoder.decode(sb.toString(), "utf-8");// 解码
			return  getObject(str, Map.class);
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
