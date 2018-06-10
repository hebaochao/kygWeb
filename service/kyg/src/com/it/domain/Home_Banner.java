package com.it.domain;

/****
 * 广告条实体类
 * 
 * @author Alex
 * 
 */
public class Home_Banner implements java.io.Serializable{
	/***
	 * 广告条ID
	 */
	private int  _id;
	/***
	 * 广告条标题摘要
	 */
	private String title;
	/***
	 * 广告条图片资源URL
	 */
	private String pic_url;
	/***
	 * 广告对应目标活动页面URL
	 */
	private String tag_url;
	
	
	
	


	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getTag_url() {
		return tag_url;
	}

	public void setTag_url(String tag_url) {
		this.tag_url = tag_url;
	}

	public Home_Banner() {
		super();
	}

}
