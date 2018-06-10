package com.it.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.ContactManager;

/***
 * 投诉建议服务类
 * 
 * @author Alex
 * 
 */
public class ContactService {

	public ContactService() {
		// TODO Auto-generated constructor stub
	}

	/*************** 单例模式 ******************/
	public static ContactService contactService = new ContactService();

	public static ContactService getContactService() {
		return contactService;
	}

	/***************
	 * 单例模式
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @param title
	 * @param contact
	 * @param con_time
	 ******************/

	public String AddContactService(String name, String email, String phone,
			String title, String contact) {
		boolean flag = ContactManager.getContactManager().Addcontact(name,
				email, phone, title, contact,
				new Timestamp(System.currentTimeMillis()).toString());
       //封装数据
	  Map<String, Object> map=new HashMap<String, Object>();
		if (flag) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  
		return FastjsonTools.CreategsonString(map);
       }

}
