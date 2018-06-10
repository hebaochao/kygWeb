package com.it.dao;

import com.it.domain.Versions;

public class VersionsManager extends BaseDAO {

	public VersionsManager() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	/************单例模式******************/
	private static VersionsManager versionsManager=new VersionsManager();




	public static VersionsManager getVersionsManager() {
		return versionsManager;
	}
	
	/************单例模式******************/
	
	
	
	public int getVersions(){
		String sql="select *  from versions1 where isused = "+1;
		Versions  versions=(Versions) super.get(sql, Versions.class);
		return  versions.getVersionsid();
	}
	
	
	
	
	
	
	
	
	
	
	
}
