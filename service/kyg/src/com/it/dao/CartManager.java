package com.it.dao;

import java.util.ArrayList;
import java.util.List;

import com.it.domain.Cart;
import com.it.domain.Product;

/***
 * 管理数据库购物车表
 * 購物車的增刪改查
 * @author Alex
 *
 */
public class CartManager extends BaseDAO {

	public CartManager() {
		// TODO Auto-generated constructor stub
	}
	/************************单例模式*********************************/
	public static CartManager cartManager=new CartManager();
	

	public static CartManager getCartManager() {
		return cartManager;
	}
	/************************单例模式*********************************/
	String sqlString ;
	
	/****
	 * 根据用户ID	查找购物车表
	 * 获取购物车表中符合条件的商品ID
	 * 查询获取该商品列表
	 */
	public List<Cart> getCartProducts(String userid){
		sqlString="select * from cart where userid='"+userid+"'";
		return super.query(sqlString, Cart.class);
 }
	/****
	 * 根据购物车ID	查找购物车
	 * 获取购物车表中符合条件的商品ID
	 * 查询获取该商品列表
	 */
	public Cart getCartByCartid(String cart_id){
		sqlString="select * from cart where cartid='"+cart_id+"'";
		return (Cart) super.get(sqlString, Cart.class);
 }
	
	/*****
	 * 添加商品到购物车中
	 * @param userid  用户ID
	 * @param pro_id  商品ID
	 * @param number  商品数量
	 * @return  是否添加成功
	 */
	public boolean addCarProducts(String userid,String pro_id,int number){
		// 查询是否存在该信息
		List<Cart> carts=	getCartProducts(userid);
		for (Cart cart : carts) {
			if(cart.getPro_id()==Integer.parseInt(pro_id)){
				number=cart.getPro_number()+number;
			   return	UpdateCarProducts(userid,pro_id,String.valueOf(cart.getCartid()),number);
				  
			}
		}
		
		//新信息插入
		  Product product=	ProductManager.getProductManager().getProductByPro_id(pro_id);
		  
		  double price=number*product.getPrice();
		
	
	sqlString="insert into cart value(null,'" +userid+"',"+pro_id+",'"+product.getName()+"',"+number+","+price+")";
	return  super.update(sqlString);
	}
	/*****
	 * 更新商品到购物车中
	 * @param userid  用户ID
	 * @param pro_id  商品ID
	 * @param number  商品数量
	 * @return  是否更新成功
	 */
	public boolean UpdateCarProducts(String userid,String pro_id,String cart_id,int number){
		  Product product=	ProductManager.getProductManager().getProductByPro_id(pro_id);
		  double price=number*product.getPrice();

		sqlString="UPDATE cart set pro_number="+number+",pro_price="+price+" WHERE(cartid="+cart_id+" and userid="+userid+")";
		return  super.update(sqlString);
		}
		
	
	
	
	
	
	/****
	 * 从购物车中删除该商品
	 * @param userid  用户ID
	 * @param pro_id  商品ID
	 * @return  返回是否删除成功
	 */
	public boolean deleteCarProducts(String userid,String cart_id){
	
		 
		  sqlString="delete from cart where (userid="+userid+" and cartid="+cart_id+")";
	
		return  super.update(sqlString);
		}
		
	
}
