package com.it.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.AddressManager;
import com.it.dao.CartManager;
import com.it.dao.OrderItemManager;
import com.it.dao.OrderManager;
import com.it.dao.ProductManager;
import com.it.dao.UserManager;
import com.it.domain.Address1;
import com.it.domain.Cart;
import com.it.domain.Delivery;
import com.it.domain.Item_Product;
import com.it.domain.OrderItem;
import com.it.domain.ProOrder_Item;
import com.it.domain.ProOrder_page;
import com.it.domain.Product;
import com.it.domain.ProductOrder;
import com.it.domain.Product_page;
import com.it.domain.User;


/***
 * 商品订单服务类
 * 
 * @author Alex
 * 
 */
public class ProductOrderService {

	public ProductOrderService() {
		// TODO Auto-generated constructor stub
	}

	/*********************** 单例模式 ***************************/
	public static ProductOrderService orderService = new ProductOrderService();

	public static ProductOrderService getOrderService() {
		return orderService;
	}

	/***********************
	 * 单例模式
	 * 
	 ***************************/

	public String Orders_keyName="orders";
	public String Order_keyName="order";
	public String Orders_page_keyName="orders_page";
	public String OrdersItems_keyName="orderitems";
	
	/*****
	 * 创建返回json
	 * @param produts  list集合
	 * @return
	 */
	private Map<String, Object> creatResultMes(List<ProductOrder> orders){
		  Map<String, Object> map=new HashMap<String, Object>();
		  if(orders!=null){
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				map.put(this.Orders_keyName,orders);
		  }else{
			  map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
		  }
		 
		return map;
				
	}
	/*
	 * 订单子项列表
	 * **/
	private Map<String, Object> creatResultMesItems(List<OrderItem> ordersitems){
		  Map<String, Object> map=new HashMap<String, Object>();
		  if(ordersitems!=null){
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				map.put(this.OrdersItems_keyName,ordersitems);
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
	private Map<String, Object> creatResultObject(Object order){
		  Map<String, Object> map=new HashMap<String, Object>();
		
		  
		  if (order!= null) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				
				 if (order instanceof ProductOrder) {
					  map.put(this.Order_keyName,order);
				    }else if(order instanceof ProOrder_page){
				    	map.put(this.Orders_page_keyName, order);
				    }
				 
				
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
	private Map<String, Object> creatResultBoolean(boolean issuccess){
		  Map<String, Object> map=new HashMap<String, Object>();
		 if (issuccess) {
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
				
			}else{
				map.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
			}
		  
		  	return map;
				
	}
	

/****
 * 创建一个新订单 更新购物车和订单清单表
 * @param cart_ids
 * @param addressId
 * @param userid
 * @param delivery
 * @param pay
 * @return
 */
	public String AddProductOrder(String[] cart_ids,String addressId, String userid, int delivery, int pay
			) {
		// 待结算的商品集合
		List<Cart> carts = new ArrayList<Cart>();
		// 查询购物车 获取要创建订单的商品有哪些
		for (String cartid : cart_ids) {
			Cart cart = CartManager.getCartManager().getCartByCartid(cartid);
			if (cart != null) {
				carts.add(cart);
			}
		}
		//查询地址
		Address1 address=AddressManager.getAddressManager().getUserAddressById(addressId);

		if (!carts.isEmpty()) {// 要结算的购物车商品集合不为空
			// 1、创建订单
			
			ProductOrder order = new ProductOrder();
		
			order.setReceiver(address.getRe_name());
			order.setAddress(address.getAddress_msg());
			order.setPhone(address.getPhone());
			order.setUserid(Long.parseLong(userid));
			order.setDelivery(delivery);
			order.setPay(pay);
			//计算订单价格和数量
			int number1=0;
			double price1=0.0;
			for (Cart cart : carts) {
				number1=number1+cart.getPro_number();
				price1=price1+cart.getPro_price();
						
			}
			//加上运费
			Delivery del=OrderManager.getManager().findDeliveryById(delivery);
			price1=price1+del.getCost();
			//设置订单价格和数量
			order.setNumber(number1);
			order.setPrice(price1);

			// 设置订单的订单ID以及订单时间

			order.setOrder_id(new Date().getTime() + RandomUtils.nextInt(10000));
			order.setOrdertime(new Timestamp(System.currentTimeMillis())
					.toString());

			// 设置订单默认初始状态是未付款的 状态吗为0
			order.setState(0);
			switch (pay) {
			 case 2:// 如果是转账汇款 则设置转账交易码
				order.setRemitcode(new Timestamp(System
						.currentTimeMillis()).getTime());
				
				break;
			case 3:// 如果是货到付款 则设置订单状态为已支付 待发货状态
				order.setState(1);
				break;
			}

			// 把订单添加到数据库中
			boolean flag = OrderManager.getManager().addOrder(order);
			if (!flag) {
				return  null;
			}
			// 2、添加订单详细表
			for (Cart cart : carts) {

				OrderItem item=new OrderItem();
				item.setPro_id(cart.getPro_id());
				item.setOrder_id(order.getOrder_id());
				item.setNumber(cart.getPro_number());
				item.setPrice(cart.getPro_price());
				item.setItems_id(new Date().getTime());
				OrderItemManager.getItemManager().addOrderItem(item);
			}
			
			// 3、从购物车中移除集合中的商品信息
			for (Cart cart : carts) {
				CartManager.getCartManager().deleteCarProducts(userid,
						String.valueOf(cart.getCartid()));
			}
		
			
			// 4、返回新订单的json数据
			order = OrderManager.getManager().getOrder(order.getOrder_id());
			
			
			return FastjsonTools.CreategsonString(this.creatResultObject(order));

		}

		return FastjsonTools.CreategsonString(this.creatResultObject(null));

	}

	/****
	 * 删除指定订单 从订单子项中移除订单清单商品
	 * 
	 * @param order_id
	 *            订单ID
	 * @param userid
	 *            用户ID
	 * @return
	 */
	public String DeleteProductOrder(String order_id, String userid) {
		// 1、从订单子项表中移除该订单下的所有商品记录
		boolean flag = OrderItemManager.getItemManager().deleteOrderItem(
				order_id);
		if (flag) {
			// 2、从订单表中移除指定的订单
			return FastjsonTools.CreategsonString(this.creatResultBoolean(OrderManager.getManager()
					.deleteOrder(Long.parseLong(order_id), userid)));
		}

		return FastjsonTools.CreategsonString(this.creatResultBoolean(false));

	}

	/***
	 * 订单支付
	 * 
	 * @param order_id
	 * @param userid
	 * @return
	 */
	public String PayOrderPayState(String order_id, String userid) {
		// 1、根据订单ID查询订单
		ProductOrder order = OrderManager.getManager().getOrder(
				Long.parseLong(order_id));
		// 2、根据用户ID查询用户资料
		User user = UserManager.getManager().findUserdByUserID(
				Long.parseLong(userid));

		// 3、比对用户资源是否足够支付订单的金额
		if (user.getBalance() < order.getPrice()) {
			
			return FastjsonTools.CreategsonString(this.creatResultBoolean(false));
		}

		// 4、足够则更改用户资金余额
		double balance = user.getBalance() - order.getPrice();
		UserManager.getManager().updateUserbalance(Long.parseLong(userid),
				balance);

		// 5、更改订单状态码为1 已支付与状态
		if (order.getPay() == 2) {
			// 原来是转账支付 更改为在线支付
			OrderManager.getManager().updateOrderStatic(order_id, 1, 1, "0");
		} else {
			OrderManager.getManager().updateOrderStatic(order_id, 1);
		}
		
		//发送系统消息

		//发送消息到用户消息中  提示订单支付成功
	MessageService.getMessageService().sendMessageToUser("支付消息","订单："+order_id+"商品准备好就尽快为您配送，请耐心等候！", Long.parseLong(userid));

		// 6、返回成功标志
		return FastjsonTools.CreategsonString(this.creatResultBoolean(true));

	}

	/***
	 * 根据用户ID查找全部订单
	 * 
	 * @param userid
	 * @return
	 */
	public String getOrderByUserId(String userid) {
		List<ProductOrder> orders = OrderManager.getManager().getOrderByUserid(
				userid);
		
		
		
		return FastjsonTools.CreategsonString(this.creatResultMes(orders));

	}
	


	/***
	 * 查找用户的相关订单 按交易状态查找订单
	 * 
	 * @return
	 */
	public String getOrderByUserIdAndState(String userid, int state) {

		List<ProductOrder> orders = OrderManager.getManager().getOrderByUserid(
				userid, state);

		return FastjsonTools.CreategsonString(this.creatResultMes(orders));

	}

	/****
	 * 根据订单ID获取订单子项的集合
	 * 
	 * @param order_id
	 * @return
	 */
	public String getOrderItemByOrderId(String order_id) {
		List<OrderItem> orderItems = OrderItemManager.getItemManager()
				.getOrderItemByOrderID(Long.valueOf(order_id));
		
		
		
		return FastjsonTools.CreategsonString(this.creatResultMesItems(orderItems));

	}
	
	
	
	
	/******************扩展方法  分页查找用户订单***************/
	
///***
// * 根据用户ID查找全部订单 分页查询 每次查询10条记录
// * @param reg_page  请求的页数
// * @param userid   用户ID
// * @return
// */
//	public String getOrderByUserIdAndPage(int reg_page,String userid) {
//		List<ProductOrder> orders = OrderManager.getManager().getOrderByUserid(
//				userid);
//		
//		if (orders.isEmpty()) {//没可查询订单
//			return  null;
//		}else if (reg_page>orders.size()) {//页数超出范围
//			return  null;
//		}else {  //正常情况查询到订单
//			return FastjsonTools.CreategsonString(orders);
//		}
//
//	}
	
	/***
	 * 根据user查询 user的订单
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            用户
	 * @param type
	 *            0--未支付 5--所有订单
	 * @throws IOException
	 */
	
	
	public String getOrderListByUser(String req_page,int req_type, String userid) {
		int page1 = Integer.parseInt(req_page);// 获取需要查询的ID
		int type = req_type;

		// 获取订单的总页数
	
		int totalpage = this.getOrderNumber(userid,
				type);
		//请求页数小于等于0或者大于分页范围则返回null
		if(page1<=0||page1>totalpage){
			return FastjsonTools.CreategsonString(this.creatResultObject(null));
		}
		// 获取订单列表
		List<ProductOrder> productorders =this.getOrderByPage(userid, page1, type);

		
			//创建返回模型
			ProOrder_page order_page=new ProOrder_page();
			order_page.count_page=totalpage;
			order_page.cur_page=page1;
			
			
			List<ProOrder_Item>  orders=new ArrayList<ProOrder_Item>();
			
			for (ProductOrder order : productorders) {
				List<OrderItem> orderItems = OrderItemManager.getItemManager()
						.getOrderItemByOrderID(order.getOrder_id());
				//创建订单与订单子项模型
				ProOrder_Item order_item=new ProOrder_Item();
				//设置订单
				order_item.order=order;
			
				//创建商品数组
				List<Item_Product>  item_products=new ArrayList<Item_Product>();
				//查找订单子项对应的商品
				for (OrderItem orderItem : orderItems) {
					Item_Product item_pro= new Item_Product();
					Product  product=ProductManager.getProductManager().getProductByPro_id(String.valueOf(orderItem.getPro_id()));
					//设置订单子项
					item_pro.orderitem=orderItem;
					//设置订单子项对应商品
					item_pro.product=product;
					
					item_products.add(item_pro);
				}
				
				order_item.item_products=item_products;
				
				
				orders.add(order_item);
			}
			
			//设置订单-子项-商品数组
			order_page.orders=orders;
			
			
			
			
			
			
			return FastjsonTools.CreategsonString(this.creatResultObject(order_page));
	

		
	}
	
	
	
	
	/***
	 * 获得订单总页数 每頁10條記錄
	 * 
	 * @param type
	 *            0--未支付 1---已支付 未完成 5--所有订单 
	 * @return
	 */
	public int getOrderNumber(String userid, int type) {
	
		int total = this.CountProductById(userid, type);

		int totalPage = total /10; // 总页数
		if (total % 10!= 0) { // 不是整页的情况
			totalPage += 1;
		}
		return totalPage;
	}

	/***
	 * 获取订单总记录条目
	 * 
	 * @param type
	 *            0--未支付  5--所有订单 
	 * @return
	 */
	private int CountProductById(String userid, int type) {
		switch (type) {
		case 0:// 用户未支付
			return OrderManager.getManager()
					.getOrderByUserid(userid, 0)
					.size();

		case 5: // 用户所有订单
			return OrderManager.getManager()
					.getOrderByUserid(userid).size();

	

		}
		return 0;
	}


	/***
	 * 查询指定页面的订单列表
	 * 
	 * @param page
	 * @param type
	 *            0--未支付 1---已支付 未完成 5--所有订单 
	 *            10 管理员查询 所有未支付订单 50 --管理员查询所有订单
	 * @param totalpage
	 *            总页数
	 * @return
	 */
	public List<ProductOrder> getOrderByPage(String userid, int page, int type) {
		int allnumber = this.CountProductById(userid, type);

		
		int startPoint = page * 10- 10; // 新查询的起始位置

		int count = 10;
		if (startPoint + 10 > allnumber) { // 尝试加10 若大于 则超出最大值

			count = allnumber - startPoint;
		}
		List<ProductOrder> orders = null;

		switch (type) {
		case 0:// 未支付 未完成订单
			orders = OrderManager.getManager().getOrderByUseridlimit(
					userid, 0, startPoint, count);
			break;
		case 5:// 所有订单
			
			orders = OrderManager.getManager().getOrderByUseridlimit(
					userid, startPoint, count);
			break;
	

		}

		return orders;
	}

	

}
