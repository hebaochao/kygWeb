package com.it.dao;

import java.util.List;

import com.it.domain.OrderItem;

/****
 * 订单明细表管理类
 * 
 * @author Administrator
 * 
 */
public class OrderItemManager extends BaseDAO {

	/******************* 单例模式 *************************/
	private static OrderItemManager itemManager = new OrderItemManager();

	public static OrderItemManager getItemManager() {
		return itemManager;
	}

	private String sql;

	/******************* 单例模式 *************************/
/****
 * 添加一条订单明细记录
 * @param item  订单明细实体
 * @return   是否操作成功
 */
	public boolean addOrderItem(OrderItem item) {
		sql = "insert into item value(" + item.getItems_id() + ","+ item.getPro_id()+","
				+ item.getOrder_id() + "," + item.getNumber() + ","
				+ item.getPrice() + ")";
		return super.update(sql);
	}
	/***
	 * 根据订单ID删除该订单下的所有商品明细
	 * @param order_id  订单ID
	 * @return
	 */
	public Boolean deleteOrderItem(String order_id){
		  sql="delete from item WHERE(order_id='"+order_id+"')"; //删除item的sql语句
		  return super.update(sql);
	}
	  
	/***
	 * 根据订单ID查询该订单下的所有明细商品列表
	 * @param order_id  订单ID
	 * @return  List<OrderItem>    订单明细表的商品集合
	 */
	public List<OrderItem>  getOrderItemByOrderID(long order_id){
		sql="select * from item where order_id = "+order_id;
		return super.query(sql, OrderItem.class);
	}
	

}
