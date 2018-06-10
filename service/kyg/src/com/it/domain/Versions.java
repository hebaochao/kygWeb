package com.it.domain;
/**
 * 版本实体类
 * @author Alex
 *
 */
public class Versions implements java.io.Serializable {

	public Versions() {
		// TODO Auto-generated constructor stub
	}

	
	
	private long id;
	private int versionsid;
	private String datatime;
	private int isused;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getVersionsid() {
		return versionsid;
	}
	public void setVersionsid(int versionsid) {
		this.versionsid = versionsid;
	}
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	public int getIsused() {
		return isused;
	}
	public void setIsused(int isused) {
		this.isused = isused;
	}

}
