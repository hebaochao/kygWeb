package com.it.dao;

import java.util.ArrayList;
import java.util.List;

import com.it.domain.Delivery;
import com.it.domain.Paytype;
import com.it.domain.ProductOrder;

/****
 * 操作订单管理类
 * 
 * @author Administrator
 * 
 */
public class OrderManager extends BaseDAO {

	/********************** 单例模式 ****************************/
	private static OrderManager manager = new OrderManager();

	public static OrderManager getManager() {
		return manager;
	}

	/********************** 单例模式 ****************************/
	public OrderManager() {
		// TODO Auto-generated constructor stub
	}

	private String sql;

	/****
	 * 添加订单
	 */
	public boolean addOrder(ProductOrder order) {
		sql = "insert into productorder value('" + order.getOrder_id() + "','"
				+ order.getReceiver() + "','" + order.getAddress() + "','"
				+ order.getPhone() + "','" + order.getOrdertime() + "','"
				+ order.getUserid() + "'," + order.getDelivery() + ","
				+ order.getState() + "," + order.getPay() + ","
				+ order.getNumber() + "," + order.getPrice() + ",'"
				+ order.getRemitcode() + "')";
		return super.update(sql);
	}

	/****
	 * 根据用户名和订单单号删除指定的订单
	 * 
	 * @param order_id
	 * @param userid
	 * @return
	 */
	public boolean deleteOrder(long order_id, String userid) {
		String sql = "delete from productorder WHERE(order_id='" + order_id
				+ "'and userid='" + userid + "')"; // 删除order的sql语句
		return super.update(sql);
	}

	/***
	 * 用于在线支付 根据订单ID 更新订单表的支付状态字段
	 * 
	 * @param order_id
	 *            订单ID
	 * @param state
	 *            支付状态
	 * @return
	 */
	public boolean updateOrderStatic(String order_id, int state) {
		sql = "UPDATE productorder set state=" + state + " WHERE(order_id='"
				+ order_id + "')";
		return super.update(sql);

	}

	/***
	 * 用于货到付款 根据订单ID 更新支付方式 支付状态
	 * 
	 * @param order_id
	 *            订单ID
	 * @param state
	 *            支付状态
	 * @param pay
	 *            支付方式
	 * @param remitcode
	 *            支付号
	 * @return
	 */
	public boolean updateOrderStatic(String order_id, int state, int pay,
			String remitcode) {
		String sql = "UPDATE productorder set state=" + state + ",pay=" + pay
				+ ",remitcode='" + remitcode + "'  WHERE(order_id='" + order_id
				+ "')";
		return super.update(sql);

	}

	/**
	 * 根据订单ID 查询订单
	 * 
	 * @param id
	 * @return
	 */
	public ProductOrder getOrder(long id) {
		sql = "select * from productorder where order_id = " + id ;

		return (ProductOrder) super.get(sql, ProductOrder.class);

	}

	/**
	 * 根据用户ID查找所有订单
	 */
	public List<ProductOrder> getOrderByUserid(String userid) {
		List<ProductOrder> orderlist = new ArrayList<ProductOrder>();
		sql = "select * from productorder where userid='" + userid + "'";

		return super.query(sql, ProductOrder.class);
	}
	
	

	/**
	 * 分页查询
	 * 根据用户ID查找所有订单
	 */
	public List<ProductOrder> getOrderByUseridlimit(String userid, int start,
			int count) {
		List<ProductOrder> orderlist = new ArrayList<ProductOrder>();
		sql = "select * from productorder where userid= ' " + userid + "' limit " + start + "," + count;

		return super.query(sql, ProductOrder.class);
	}

	/**
	 * 分页查询
	 * 根据用户ID查找所有订单 未支付的订单
	 */
	public List<ProductOrder> getOrderByUseridlimit(String userid, int state, int start,
			int count) {
		List<ProductOrder> orderlist = new ArrayList<ProductOrder>();
		sql = "select * from productorder where (userid='" + userid
				+ "'and state=" + state + ")  limit " + start + "," + count;
		return super.query(sql, ProductOrder.class);
	}
	
	
	

//	/**
//	 * 根据用户ID查找所有订单 分页查询订单   
//	 * @param reg_page 请求页数
//	 * @param userid  用户ID
//	 * @return
//	 */
//	public List<ProductOrder> getOrderByUseridAndPage(int reg_page,String userid) {
//		//List<ProductOrder> orderlist = new ArrayList<ProductOrder>();
//		
//		sql = "select * from productorder where userid='" + userid + "'";
//		List<ProductOrder> orderlist=super.query(sql, ProductOrder.class);
//		//分页处理
//		//计算总页数
//		
//		
//		//判断当前页面页数是否超出总页数范围
//		
//		//分页查询订单  每次只查询10条数据
//		
//	    
//		
//		
//		
//		
//		
//		return null;
//	}
//	
	
	
	
	/**
	 * 根据用户ID查找所有订单 未支付的订单
	 */
	public List<ProductOrder> getOrderByUserid(String userid, int state) {
		List<ProductOrder> orderlist = new ArrayList<ProductOrder>();
		String sql = "select * from productorder where (userid='" + userid
				+ "'and state=" + state + ");";
		return super.query(sql, ProductOrder.class);
	}

	
	/****
	 * 获取所有支付方式
	 */
	public List<Paytype> getAllPayType(){
		sql="select * from paytype";
		return super.query(sql, Paytype.class);
	}
	
	/***
	 * 根据支付类型ID 查找支付类型
	 * @param id
	 * @return
	 */
	public Paytype  findPaytypeById(int id){
		sql="select * from paytype where id ="+id;
		return (Paytype) super.get(sql, Paytype.class);
	}
	
	
	
	
	/****
	 * 获取所有配送方式
	 * @return
	 */
	public List<Delivery> getAllDeliveries(){
		sql="select * from delivery";
		return super.query(sql, Delivery.class);
	}
	
	
	
	
	
	/***
	 * 根据配送方式ID 查找配送方式
	 * @param id
	 * @return
	 */
	public Delivery  findDeliveryById(int id){
		sql="select * from delivery where id ="+id;
		return (Delivery) super.get(sql, Delivery.class);
	}
	
	
}
