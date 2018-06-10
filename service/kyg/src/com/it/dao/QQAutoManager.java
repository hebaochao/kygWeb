package com.it.dao;

import com.it.domain.qqauthor;

/***
 * 腾讯QQ授权登陆表管理类 单例模式
 * 
 * @author Alex
 * 
 */
public class QQAutoManager extends BaseDAO {

	public QQAutoManager() {
		// TODO Auto-generated constructor stub
	}

	/********************** 单例模式 ***************************/
	private static QQAutoManager autoManager = new QQAutoManager();

	public static QQAutoManager getAutoManager() {
		return autoManager;
	}

	/********************** 单例模式 ***************************/

	private String sql;

	/***
	 * 添加一条QQ授权登陆记录
	 * 
	 * @param qq_openid
	 *            QQ开放ID
	 * @param userid
	 *            用户ID
	 * @return
	 */
	public boolean addrecode(String qq_openid, long userid) {
		sql = "insert into qqauthor value('" + qq_openid + "' ," + userid + ")";
		return super.update(sql);
	}

	/***
	 * 根据QQ开放ID查找是否已授权过 有user l
	 * 
	 * @param qq_openid
	 * @return  若有则返回id 号 若没 则返回nul
	 */
	public Long getuseridByopenid(String qq_openid) {

		sql = "select * from qqauthor where qqid = '" + qq_openid+"'";
		qqauthor  qqauthor1=(qqauthor) super.get(sql, qqauthor.class);
		if(qqauthor1==null){
			return null;
		}
		
		return qqauthor1.getUserid();

	}

}
