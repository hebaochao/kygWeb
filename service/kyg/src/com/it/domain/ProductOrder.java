package com.it.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 商品订单实体类
 * 
 * @author jack
 * 
 */
public class ProductOrder implements java.io.Serializable {

	private long order_id;
	private String receiver;
	private String address;
	private String phone;
	private String ordertime;
	private long userid;
	private int delivery;
	private int state;
	private int pay;
	private int number;
	private double price;
	private long remitcode;

	
	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getDelivery() {
		return delivery;
	}

	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getRemitcode() {
		return remitcode;
	}

	public void setRemitcode(long remitcode) {
		this.remitcode = remitcode;
	}



}
