package com.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.HomeManager;
import com.it.domain.Home_Banner;


public class HomeService  {

	/**********************单例模式****************************/
	private static HomeService homeService=new HomeService();
	
	public static HomeService getHomeService() {
		return homeService;
	}


	public HomeService() {
		// TODO Auto-generated constructor stub
		
	}

	

	public String GetHome_Banner() {
		
		Map<String, Object> map=new HashMap<String, Object>();
		List<Home_Banner> list=HomeManager.getHomeManager().getHome_Banners();
		if(list.size()>0){
			map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
			
			map.put("banners", list);
		}else{
			map.put("result", ConfigValues.RESPONSE_ERROR);
		}
		
		return    FastjsonTools.CreategsonString(map);
	}



	
	
	

}
