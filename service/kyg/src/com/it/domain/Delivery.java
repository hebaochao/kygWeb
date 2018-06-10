package com.it.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 配送方式
 */

public class Delivery implements java.io.Serializable {

	// Fields

	private int id;
	private String name;
	private int cost;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	

}