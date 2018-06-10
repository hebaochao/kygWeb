package com.it.dao;

import java.sql.SQLException;

import com.it.domain.User;

/***
 * 管理数据库用户表的增删改查
 * 
 * @author Alex
 * 
 */
public class UserManager extends BaseDAO {

	public UserManager() {
		// TODO Auto-generated constructor stub
	}

	/*********************** 单例模式 *****************************/
	public static UserManager manager = new UserManager();

	public static UserManager getManager() {
		return manager;
	}

	/*********************** 单例模式 *****************************/

	/***
	 * 注册时用 检查用户名是否已经存在
	 * 
	 * @return
	 * @throws SQLException
	 */
	public boolean CheckUsername(String username) {
		String sql = "select username from user WHERE(username='" + username
				+ "')";
		User user = (User) super.get(sql, User.class);
		if (user == null) {
			return true;
		} else {
			return false;
		}

	}

	/****
	 * 添加新用户
	 * 
	 * @param newuser
	 * @return
	 */
	public boolean AddUser(User newuser) {
		String sql = "insert into user(userid,username,passwords,phone)value"
				+ "('"
				+ newuser.getUserid()
				+ "','"
				+ newuser.getUsername()
				+ "','"
				+ newuser.getPasswords()
				+ "','"
				+ newuser.getPhone()
                 + "')";

		boolean flag = super.update(sql);

		return flag;

	}

	/**
	 * 
	 * 根据用户名以及密码 查找用户
	 * 
	 * @throws SQLException
	 */

	public User getUserPasswordByUsername(String username, String passwords) {

		String sql = "select * from user WHERE(username='" + username
				+ "' and passwords='" + passwords + "')";
		User user = (User) super.get(sql, User.class);

		return user;

	}

	/**
	 * 
	 * 根据用户名id 查找用户
	 * 
	 * @throws SQLException
	 */

	public User findUserdByUserID(long userid) {

		String sql = "select * from user WHERE(userid='" + userid + "')";
		User user = (User) super.get(sql, User.class);

		return user;

	}

	/***
	 * 更新用户登陆时间
	 * 
	 * @param userid
	 * @param time
	 * @return
	 */
	public boolean updateUserLoginTime(long userid, String time) {
		String sql = "UPDATE user set lastvisit='" + time + "' where userid='"
				+ userid + "'";

		return super.update(sql);
	}

	/***
	 * 更新用户密码
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	public boolean updateUserPasswords(long userid, String password) {
		String sql = "UPDATE user set passwords='" + password
				+ "' where userid='" + userid + "'";

		return super.update(sql);
	}

	/***
	 * 更新用户余额
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	public boolean updateUserbalance(long userid, double balance) {
		String sql = "UPDATE user set balance= " + balance + " where userid='"
				+ userid + "'";

		return super.update(sql);
	}

	/***
	 * 更新用户手机和邮箱 更新用户真实姓名和身份证号码
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	public boolean updateUserphoneAndEmail(long userid, String Email,
			String phone) {
		String sql = "UPDATE user set Email= '" + Email + "', phone = '"
				+ phone + "' where userid= " + userid;
		return super.update(sql);
	}

}
