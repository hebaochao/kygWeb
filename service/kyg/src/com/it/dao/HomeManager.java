package com.it.dao;

import java.util.List;

import com.it.domain.Home_Banner;

/***
 * 关于主页对数据库操作的DAO层 提供主页所需要的数据
 * 
 * @author Alex
 * 
 */
public class HomeManager extends BaseDAO {

	/***********************单例模式****************************/
	private static HomeManager homeManager=new HomeManager();
	
	
	public static HomeManager getHomeManager() {
		return homeManager;
	}


	public HomeManager() {
		// TODO Auto-generated constructor stub
	}
	/***********************单例模式****************************/
	private String sqlString;


	/***
	 * 查询所有广告条的实体
	 * 
	 * @return 广告条的list集合
	 */

	public List<Home_Banner> getHome_Banners() {
		sqlString = "SELECT * FROM banners";

		return this.query(sqlString, Home_Banner.class);
	}

	
	
	
	
	
}
