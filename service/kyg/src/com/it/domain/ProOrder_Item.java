package com.it.domain;

import java.util.List;

public class ProOrder_Item {

	
	
	/****
	 * 订单
	 */
	public ProductOrder  order;
	/***
	 * 订单对应的订单子项列表
	 */
	public  List<Item_Product> item_products;
}
