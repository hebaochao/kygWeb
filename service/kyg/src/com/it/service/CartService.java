package com.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.CartManager;
import com.it.dao.ProductManager;
import com.it.domain.Cart;
import com.it.domain.Product;


/****
 * 购物车相关服务
 * 
 * @author Alex
 * 
 */
public class CartService {

	public CartService() {
		// TODO Auto-generated constructor stub
	}

	/************************ 单例模式 *********************************/
	public static CartService cartService = new CartService();

	public static CartService getCartService() {
		return cartService;
	}

	/************************ 单例模式 *********************************/

	
	
	private String key_carts="carts";
	private String key_cart="cart";
	/*****
	 * 创建返回json
	 * @param produts  list集合
	 * @return
	 */
	private Map<String, Object> creatResultMes(List<Cart> carts){
		  Map<String, Object> map=new HashMap<String, Object>();
				if (carts.size()>0) {
					map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
					map.put(this.key_carts,carts);
				}else{
					map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
				}
				return map;
				
	}
	/*****
	 * 创建返回json
	 * @param produt  商品对象
	 * @return
	 */
	private Map<String, Object> creatResultMes(Object produt){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  
		  if (produt!= null) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				 map.put(this.key_cart,produt);
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  
		  		return map;
				
	}
	
	/*****
	 * 创建返回json
	 * @param produt  商品对象
	 * @return
	 */
	private Map<String, Object> createResultBoolean(boolean issuccess){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  if (issuccess) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				 
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  	return map;
				
	}
	
	
	
	
	
	
	
	
	/****
	 * 根据用户ID查找用户购物车的商品列表 并返回json数据
	 * 
	 * @param tip
	 *            返回数据的提示信息 可为空
	 * @param userid
	 *            用户ID
	 * @return json 数据
	 */
	public String getCarProductsByUserIdTojson(String userid) {
		List<Cart> list = CartManager.getCartManager().getCartProducts(userid);
		
		//遍历数组 追加商品图片
		for (Cart cart : list) {
			Product p=ProductManager.getProductManager().getProductByPro_id(String.valueOf(cart.getPro_id()));
			cart.setPro_image(p.getImage());
		}
	
		return FastjsonTools.CreategsonString(this.creatResultMes(list));
		
	
	}

	/***
	 * 往购物车中添加商品
	 * 
	 * @param userid
	 * @return json 数据
	 */
	public String addCarProductsTojson(String userid, String pro_id, int number) {
			
		boolean issuccess=CartManager.getCartManager()
				.addCarProducts(userid, pro_id, number);
		 
		
		return FastjsonTools.CreategsonString(this.createResultBoolean(issuccess));
       }

	/***
	 * 根据商品ID和用户ID删除购物车中的某个商品
	 * 
	 * @param userid
	 *            用户ID
	 * @param pro_id
	 *            商品ID
	 * @return 返回是否正确删除该商品
	 */

	public String deleteCarProductsTojson(String userid, String cart_id) {
		
		
		boolean issuccess=CartManager.getCartManager()
				.deleteCarProducts(userid, cart_id);
		;
		
		return FastjsonTools.CreategsonString(this.createResultBoolean(issuccess));

	}

	/***
	 * 用户修改商品的数量
	 * 
	 * @param userid
	 *            用户ID
	 * @param pro_id
	 *            商品ID
	 * @param number
	 *            商品数量
	 * @return 返回更新后的商品
	 */

	public String updateCarProductsTojson(String userid, String pro_id,
			String cart_id, int number) {

		boolean flag = CartManager.getCartManager().UpdateCarProducts(userid,
				pro_id, cart_id, number);
       
		if (flag) {
			Cart cart=CartManager.getCartManager()
			.getCartByCartid(cart_id);
			
			//追加商品图片
			Product p=ProductManager.getProductManager().getProductByPro_id(String.valueOf(cart.getPro_id()));
			
			cart.setPro_image(p.getImage());
			
			
		return FastjsonTools.CreategsonString(this.creatResultMes(cart));
		}
		return FastjsonTools.CreategsonString(this.createResultBoolean(flag));
		
	

	}

}
