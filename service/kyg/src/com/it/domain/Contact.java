package com.it.domain;

import java.sql.Timestamp;

/**
 * 消息记录类
 * 
 * @author Alex
 * 
 */
public class Contact implements java.io.Serializable {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String title;
	private String message;
	private String con_time;
	private int tip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCon_time() {
		return con_time;
	}

	public void setCon_time(String con_time) {
		this.con_time = con_time;
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

}
