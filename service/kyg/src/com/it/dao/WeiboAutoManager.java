package com.it.dao;

import com.it.domain.weiboauthor;



public class WeiboAutoManager extends BaseDAO{
	public WeiboAutoManager() {
		// TODO Auto-generated constructor stub
	}

	/********************** 单例模式 ***************************/
	private static WeiboAutoManager autoManager = new WeiboAutoManager();

	public static WeiboAutoManager getAutoManager() {
		return autoManager;
	}

	/********************** 单例模式 ***************************/

	private String sql;

	/****
	 * 添加一条微博授权登陆
	 * @param weibo_openid 微博授权码
	 * @param userid  用户ID
	 * @return
	 */
	public boolean addrecode(String weibo_openid, long userid) {
		sql = "insert into weiboauthor value('" + weibo_openid + "' ," + userid + ")";
		return super.update(sql);
	}

	/****
	 * 根据微博授权码 查询userid
	 * @param weibo_openid
	 * @return
	 */
	public Long getuseridByopenid(String weibo_openid) {

		sql = "select * from weiboauthor where weiboid = '" + weibo_openid+"'";
		weiboauthor  weiboauthor1=(weiboauthor) super.get(sql, weiboauthor.class);
		if(weiboauthor1==null){
			return null;
		}
		
		return weiboauthor1.getUserid();

	}

}
