package com.it.configs;

/****
 * 全局常量配置接口 本地测试
 * 
 * @author Alex
 * 
 */
public interface ConfigValues {
	/***
	 * 图片文件夹的URL
	 */
	final static String PIC_URL = "http://192.168.1.101:8080/kyg/images/";
	/***
	 * APK安装包存放位置URL
	 */
	final static String APK_URL = "http://192.168.1.101:8080/kyg/kyg_client.apk";

	/***
	 * MySQL数据库驱动名称
	 */
	final static String DABASE_DRIVRE = "com.mysql.jdbc.Driver";
	/***
	 * 数据库的URL
	 */
	final static String DABASE_URL = "jdbc:mysql://localhost:3307/mystore?useUnicode=true&characterEncoding=utf8"; // mystore为mysql里面的数据库名称
	/***
	 * 数据库用户名
	 */
	final static String DABASE_USER = "root";
	/**
	 * 数据库密码
	 */
	final static String DABASE_PASSWORD = "abc123";
	/***
	 * 返回成功标志
	 */
	final static String RESPONSE_SUCCESS = "18325";
	/***
	 * 返回失败标志
	 */
	final static String RESPONSE_ERROR = "135480";
	
	
	//***********返回标志******************//
	/***
	 * 返回标志名称
	 */
	final static String RESULT_KEY = "result";
}
