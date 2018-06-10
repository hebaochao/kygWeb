package com.it.dao;

/****
 * 投诉建议表管理类
 * 
 * @author Alex
 * 
 */
public class ContactManager extends BaseDAO {

	private String sql;

	public ContactManager() {
		// TODO Auto-generated constructor stub
	}

	/****************** 单例模式 *************************/
	public static ContactManager contactManager = new ContactManager();

	public static ContactManager getContactManager() {
		return contactManager;
	}

	/****************** 单例模式 *************************/

	
	
	
	
	
	/**
	 * 添加一条留言记录
	 */
	public boolean Addcontact(String name, String email, String phone,
			String title, String contact, String con_time) {
		sql = "insert into contact value(null,'" + name + "','" + email + "','"
				+ phone + "','" + title + "','" + contact + "','" + con_time
				+ "',0)";
		return super.update(sql);
	}
	
	
	
	
	
	
	
	
	

}
