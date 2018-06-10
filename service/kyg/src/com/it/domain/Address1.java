package com.it.domain;

import java.util.Date;

public class Address1 implements java.io.Serializable {

	public Address1() {
		// TODO Auto-generated constructor stub
	}

	private long address_id;
	private long userid;
	private String re_name;
	private String phone;
	private String address_msg;



	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getRe_name() {
		return re_name;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress_msg() {
		return address_msg;
	}

	public void setAddress_msg(String address_msg) {
		this.address_msg = address_msg;
	}

	public Address1(long id, long userid, String re_name, String phone,
			String address_msg) {

		this.address_id = new Date().getTime();
		this.userid = userid;
		this.re_name = re_name;
		this.phone = phone;
		this.address_msg = address_msg;
	}

}
