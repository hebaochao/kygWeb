package com.it.domain;

import java.sql.Timestamp;
import java.util.Date;

/***
 * 消息实体类
 * 
 * @author Alex
 * 
 */
public class Message implements java.io.Serializable {

	// Fields

	private long mes_id;
    private String title;
	private String context;
	private String con_time;
	private String sendername;
	private long receiverid;
	
	public long getMes_id() {
		return mes_id;
	}
	public void setMes_id(long mes_id) {
		this.mes_id = mes_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCon_time() {
		return con_time;
	}
	public void setCon_time(String con_time) {
		this.con_time = con_time;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public long getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(long receiverid) {
		this.receiverid = receiverid;
	}



}