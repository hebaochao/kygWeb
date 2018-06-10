package com.it.domain;

/****
 * 订单商品明细表
 * 
 * @author Administrator
 * 
 */
public class OrderItem implements java.io.Serializable {
	private long items_id;
	private int pro_id;
	private long order_id;
	private int number;
	private double price;


	
	public long getItems_id() {
		return items_id;
	}


	public void setItems_id(long items_id) {
		this.items_id = items_id;
	}




	public int getPro_id() {
		return pro_id;
	}


	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}


	public long getOrder_id() {
		return order_id;
	}


	public void setOrder_id(long order_id) {
		this.order_id = order_id;
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


	

}
