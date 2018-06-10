package com.it.service;

import com.it.configs.ConfigValues;

import com.it.dao.VersionsManager;

/***
 * 版本服务类
 * @author Alex
 *
 */
public class VersionsService {

	public VersionsService() {
		// TODO Auto-generated constructor stub
	}
	
	
	/******************单例模式**********************/
	private static VersionsService versionsService=new VersionsService();


	public static VersionsService getVersionsService() {
		return versionsService;
	}
	
	/******************单例模式**********************/
	
	
	
	
	
	/***
	 * 检查版本  
	 * 版本旧的 则返回新版本APK下载地址
	 * @param versionid
	 * @return
	 */
	public  String CheckVersion(int  versionid){
		int new_verid=VersionsManager.getVersionsManager().getVersions();
		if(versionid<new_verid){
			return ConfigValues.APK_URL;
		}else{
		    	return  "";
		}
		
		
	}
	
	
	
	
	
	

}
