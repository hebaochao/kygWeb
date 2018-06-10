package com.it.configs;

/****
 * 全局常量配置接口
 * 外网使用
 * 新服务器配置
 * @author Alex
 * 
 */
public interface ConfigValues40 {
	/***
	 * 图片文件夹的URL
	 */
	final static String PIC_URL = "http://119.247.47.84/kyg/images/";
	/***
	 * APK安装包存放位置URL
	 */
	final static String APK_URL = "http://119.247.47.84/kyg_client.apk";
	
	/***
	 * MySQL数据库驱动名称
	 */
		final static  String DABASE_DRIVRE="com.mysql.jdbc.Driver";
	/***
	 * 数据库的URL
	 */
	 // mystore为mysql里面的数据库名称   
	final static String DABASE_URL = "jdbc:mysql://localhost:3306/mystore?useUnicode=true&amp;characterEncoding=utf8"; 
	/***
	 * 数据库用户名
	 */
	final static  String DABASE_USER="root";
	/**
	 * 数据库密码
	 */
	final static  String DABASE_PASSWORD="admin";
	
	/***
	 * 返回成功标志
	 */
	final static String RESPONSE_SUCCESS="18325";
	/***
	 * 返回失败标志
	 */
	final static String RESPONSE_ERROR="135480";
	
	//***********返回标志******************//
	/***
	 * 返回标志名称
	 */
	final static String RESULT_KEY = "result";
}
