package com.it.domain;

import java.util.List;

public class ProOrder_page {
	/** 
	 *当前页码
	*/
	public int  cur_page;

	/**
	 * 总页数
	 */
	public int count_page;
	/**
	 * 订单列表
	 */
	public List<ProOrder_Item> orders;
}
