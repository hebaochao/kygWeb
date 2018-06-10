package com.it.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;

import com.it.Util.EncodeUtil;
import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.QQAutoManager;
import com.it.dao.UserManager;
import com.it.dao.WeiboAutoManager;
import com.it.domain.User;

/***
 * 关于用户的相关服务 注册 登陆 查询信息
 * 
 * @author Alex
 * 
 */
public class UserService {

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	/************************ 单例模式 ******************************/
	public static UserService service = new UserService();

	public static UserService getService() {
		return service;
	}

	/************************ 单例模式 ******************************/

	
	/*****
	 * 创建返回json

	 */
	private Map<String, Object> createResultBoolean(boolean issuccess){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  if (issuccess) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				 
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  	return map;
				
	}
	
	private String key_user="user";
	/*****
	 * 创建返回json
	 * @param user  商品对象
	 * @return
	 */
	private Map<String, Object> creatResultMes(User user){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  
		  if (user!= null) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				 map.put(this.key_user,user);
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  
		  		return map;
				
	}
	
	
	
	
	/***********
	 * 登陆服务
	 */
	public String login(String username, String passwords) {
		
		User user = UserManager.getManager().getUserPasswordByUsername(
				username, passwords);
 
		if (user == null) {
			
			return FastjsonTools.CreategsonString(this.createResultBoolean(false));
		} else {
			// 更新用户登陆时间
			String time = new Timestamp(System.currentTimeMillis()).toString();
			UserManager.getManager()
					.updateUserLoginTime(user.getUserid(), time);
			
		
			
			user = UserManager.getManager().getUserPasswordByUsername(
					username, passwords);
			
			return FastjsonTools.CreategsonString(this.creatResultMes(user));
		}
	}

	/***
	 * 注册
	 * 
	 * @param username
	 *            用户名
	 * @param passwords
	 *            密码
	 * @param phone
	 * @return
	 */
	public String register(String username, String passwords, String phone) {
		User newuser = new User();
		boolean flag1 = UserManager.getManager().CheckUsername(username);
		
		if (!flag1) {
			return FastjsonTools.CreategsonString( this.createResultBoolean(false));
		}

		newuser.setUserid(new Date().getTime());
		newuser.setUsername(username);
		newuser.setPasswords(passwords);
		newuser.setPhone(phone);
		
		boolean flag = UserManager.getManager().AddUser(newuser);
		if (!flag) {// 添加用户失败
			return FastjsonTools.CreategsonString( this.createResultBoolean(false));
		}
		//发送系统消息
		MessageService.getMessageService().sendMessageToUser("欢迎", "欢迎你来到快易购商城，祝您购物愉快！", newuser.getUserid());
		

		// 跳转登陆
		return login(newuser.getUsername(), newuser.getPasswords());
	}
	/***
	 * 注册
	 * 
	 * @param username
	 *            用户名
	 * @param passwords
	 *            密码
	 * @param phone
	 * @return
	 */
	public String register(String username, String passwords, String phone,
			String sex) {
		User newuser = new User();
		boolean flag1 = UserManager.getManager().CheckUsername(username);
		if (!flag1) {
			return null;
		}

		newuser.setUserid(new Date().getTime());
		newuser.setUsername(username);
		newuser.setPasswords(passwords);
		newuser.setPhone(phone);
		newuser.setSex(sex);
		boolean flag = UserManager.getManager().AddUser(newuser);
		if (!flag) {// 添加用户失败
			return "";
		}
		//发送系统消息
		MessageService.getMessageService().sendMessageToUser("欢迎", "欢迎你来到快易购商城，祝您购物愉快！", newuser.getUserid());
		

		// 跳转登陆
		return login(newuser.getUsername(), newuser.getPasswords());
	}

	/***
	 * 检查用户名是否可用
	 * 
	 * @return
	 */
	public String CheckUserdname(String name) {
		
		boolean b= UserManager.getManager().CheckUsername(name);
		
		//封装结果
		Map<String, String> map = new HashMap<String, String>();
		map.put("checkusername",String.valueOf(b));
		
		if (b) {
			map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
		
		}else{
			map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
		}
	return	FastjsonTools.CreategsonString(map);
		
	}

	/***
	 * 根据用户ID查找用户信息
	 * 
	 * @param userid
	 * @return
	 */
	public String FindUserByUserid(String userid) {
		User user = UserManager.getManager().findUserdByUserID(
				Long.valueOf(userid));
		if (user!=null) {
			user.setPasswords("*************");	
		}
	
		return FastjsonTools.CreategsonString(this.creatResultMes(user));
	}

	/****
	 * 根据userid 查找用户 并更新其密码
	 * 
	 * @param userid
	 * @param password
	 * @return 返回是否修改密码成功的标志
	 */
	public String alterUserPassword(String userid, String password) {
		boolean flag = UserManager.getManager().updateUserPasswords(
				Long.valueOf(userid), password);
		
		
		return FastjsonTools.CreategsonString(this.createResultBoolean(flag));
		

	}

	/****
	 * 根据userid 查找用户 并更新其手机和邮箱地址 
	 * 
	 * @param userid
	 * @param password
	 * @return 返回更新后的新user
	 */
	public String updateUserConnection(String userid, String email,
			String phone) {
		boolean flag = UserManager.getManager().updateUserphoneAndEmail(
				Long.valueOf(userid), email, phone);
		return FastjsonTools.CreategsonString(this.createResultBoolean(flag));
		

	}

	/************************* 腾讯QQ授权登陆 ********************************/
	/****
	 * 根据腾讯QQ开放ID 自动授权登陆
	 * 
	 * @param qq_openid
	 * @return
	 * @throws Exception 
	 */
	public String autologin(String qq_openid) throws Exception {
		// 1查找该授权Id是否已在本网站注册过
		Long userid = QQAutoManager.getAutoManager().getuseridByopenid(
				qq_openid);
		// 2若没注册则系统自动为其注册会员账号
		if (userid == null) {
			// 系统自动注册
			User newuser = new User();
			userid = new Date().getTime();
			newuser.setUserid(userid);
			newuser.setUsername(String.valueOf(new Date().getTime()));
			String password =  String.valueOf(new Date().getTime()) + "asdarwernjUUNWQUWENJNHWQJfbwur2342sd8fsfeasd13";
			//md5加密
			password = EncodeUtil.md5Encode(password);
			newuser.setPasswords(password);
			newuser.setPhone("");
			newuser.setSex("男");
			boolean flag = UserManager.getManager().AddUser(newuser);
			if (!flag) {// 添加用户失败
				return "";
			}
			// 在授权表中添加记录
			QQAutoManager.getAutoManager().addrecode(qq_openid, userid);
			//发送系统消息
			MessageService.getMessageService().sendMessageToUser("欢迎", "欢迎你来到快易购商城，祝您购物愉快！", newuser.getUserid());
		}
		// 3 若已经授权过或已注册成功 则返回会员json

		// 更新用户登陆时间
		String time = new Timestamp(System.currentTimeMillis()).toString();
		UserManager.getManager().updateUserLoginTime(userid, time);
		

		// 查询数据库 获取用户
		User user = UserManager.getManager().findUserdByUserID(userid);
		
		return FastjsonTools.CreategsonString(this.creatResultMes(user));

	}

	/************************* 腾讯QQ授权登陆 ********************************/

	/************************* 微博授权登陆 ********************************/
	/****
	 * 根据微博开放ID 自动授权登陆
	 * 
	 * @param qq_openid
	 * @return
	 * @throws Exception 
	 */
	public String autologin_weibo(String weibo_openid) throws Exception {
		// 1查找该授权Id是否已在本网站注册过
		
		Long userid = WeiboAutoManager.getAutoManager().getuseridByopenid(weibo_openid);
		// 2若没注册则系统自动为其注册会员账号
		if (userid == null) {
			// 系统自动注册
			
			User newuser = new User();
			userid = new Date().getTime();
			newuser.setUserid(userid);
			
			String name=String.valueOf(new Date().getDay())+String.valueOf(new Date().getMonth())+String.valueOf(RandomUtils.nextInt(10000));
		
			newuser.setUsername(name);
			//为密码加盐
			String password =  String.valueOf(new Date().getTime()) + "asdarwernjUUNWQUWENJNHWQJfbwur2342sd8fsfeasd13";
			//md5加密
			password = EncodeUtil.md5Encode(password);
			newuser.setPasswords(password);
			
			newuser.setPhone("");
			newuser.setSex("男");
			boolean flag = UserManager.getManager().AddUser(newuser);
			if (!flag) {
				
				return FastjsonTools.CreategsonString(this.creatResultMes(null));
			}
			// 在授权表中添加记录
			WeiboAutoManager.getAutoManager().addrecode(weibo_openid, userid);
			//发送系统消息
			MessageService.getMessageService().sendMessageToUser("欢迎", "欢迎你来到快易购商城，祝您购物愉快！", newuser.getUserid());
		}
		// 3 若已经授权过或已注册成功 则返回会员json

		// 更新用户登陆时间
		String time = new Timestamp(System.currentTimeMillis()).toString();
		UserManager.getManager().updateUserLoginTime(userid, time);
		

		// 查询数据库 获取用户
		User user = UserManager.getManager().findUserdByUserID(userid);
		
		return FastjsonTools.CreategsonString(this.creatResultMes(user));

	}

	/************************* 微博授权登陆 ********************************/
}
