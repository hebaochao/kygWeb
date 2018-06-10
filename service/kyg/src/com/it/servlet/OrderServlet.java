package com.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.domain.User;
import com.it.service.ProductOrderService;
import com.mysql.jdbc.StringUtils;

/***
 * 订单网络接口层
 * 
 * @author Alex
 * 
 */
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//服务端调试
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		
		int key = Integer.parseInt(request.getParameter("type"));
		String userid = request.getParameter("userid");
		String result = null;
       String order_id;
       String req_page;
		switch (key) {
//		case 1530:// 创建新订单
//			String carts = request.getParameter("carts");
//			String[] cart_ids = carts.split("-");
//			String addressId = request.getParameter("addressid");
//		
//			int delivery = Integer.parseInt(request.getParameter("delivery"));
//			int pay = Integer.parseInt(request.getParameter("pay"));
//	
//
//			result = ProductOrderService.getOrderService().AddProductOrder(
//					cart_ids, addressId, userid, delivery, pay);
//
//			break;
		case 1528:// 删除订单 取消订单
			order_id = request.getParameter("order_id");
			result = ProductOrderService.getOrderService().DeleteProductOrder(
					order_id, userid);

			break;
		case 1533:// 支付订单
			order_id = request.getParameter("order_id");
			result = ProductOrderService.getOrderService().PayOrderPayState(
					order_id, userid);

			break;
		case 1545:// 为用户查询所有订单
			result = ProductOrderService.getOrderService().getOrderByUserId(
					userid);

			break;
		case 1590:// 为用户查询未支付账单 0为未支付状态吗 1为已支付 待发货 2 已发货
			result = ProductOrderService.getOrderService()
					.getOrderByUserIdAndState(userid, 0);
			break;
		case 1591:// 为用户查询未发货账单
			result = ProductOrderService.getOrderService()
					.getOrderByUserIdAndState(userid, 1);
			break;
		case 1592:// 为用户查询已发货账单
			result = ProductOrderService.getOrderService()
					.getOrderByUserIdAndState(userid, 2);
			break;
			
			
			
			
			
			
		case 1281:// 根据指定订单ID查询所有商品
			order_id = request.getParameter("order_id");
			result = ProductOrderService.getOrderService()
					.getOrderItemByOrderId(order_id);
			break;

			
			//*******扩展*****方法****/
			
			
		case 1881:// 为用户查询未完成订单
			
			 req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page,0, userid);

			break;
		case 1882:// 为用户查询未发货账单
			
			 req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page,1, userid);

			break;
		case 1883:// 为用户查询已发货账单
			
			 req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page,2, userid);

			break;
		case 1885:// 为用户查询所有订单
		
			req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page, 5, userid);

			break;
	
		}
	
		response.getWriter().write(result);
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		
		int key = Integer.parseInt(request.getParameter("type"));
		String userid = request.getParameter("userid");
		String result = null;
       String order_id;
       String req_page;
		switch (key) {
		case 1530:// 创建新订单
			String carts = request.getParameter("carts");
			
			String[] cart_ids = carts.split("-");
			String addressId = request.getParameter("addressid");
		
			int delivery = Integer.parseInt(request.getParameter("delivery"));
			int pay = Integer.parseInt(request.getParameter("pay"));
	

			result = ProductOrderService.getOrderService().AddProductOrder(
					cart_ids, addressId, userid, delivery, pay);

			break;
		case 1528:// 删除订单 取消订单
			order_id = request.getParameter("order_id");
			result = ProductOrderService.getOrderService().DeleteProductOrder(
					order_id, userid);

			break;
		case 1533:// 支付订单
			order_id = request.getParameter("order_id");
			result = ProductOrderService.getOrderService().PayOrderPayState(
					order_id, userid);

			break;
		case 1545:// 为用户查询所有订单
			result = ProductOrderService.getOrderService().getOrderByUserId(
					userid);

			break;
		case 1590:// 为用户查询未支付账单 0为未支付状态吗 1为已支付 待发货 2 已发货
			result = ProductOrderService.getOrderService()
					.getOrderByUserIdAndState(userid, 0);
			break;
		case 1591:// 为用户查询未发货账单
			result = ProductOrderService.getOrderService()
					.getOrderByUserIdAndState(userid, 1);
			break;
		case 1592:// 为用户查询已发货账单
			result = ProductOrderService.getOrderService()
					.getOrderByUserIdAndState(userid, 2);
			break;
			
			
			
			
			
			
		case 1281:// 根据指定订单ID查询所有商品
			order_id = request.getParameter("order_id");
			result = ProductOrderService.getOrderService()
					.getOrderItemByOrderId(order_id);
			break;

			
			//*******扩展*****方法****/
			
			
		case 1881:// 为用户查询未完成订单
			
			 req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page,0, userid);

			break;
		case 1882:// 为用户查询未发货账单
			
			 req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page,1, userid);

			break;
		case 1883:// 为用户查询已发货账单
			
			 req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page,2, userid);

			break;
		case 1885:// 为用户查询所有订单
		
			req_page =request.getParameter("req_page");
			result = ProductOrderService.getOrderService().getOrderListByUser(req_page, 5, userid);

			break;
	
		}
		
		response.getWriter().write(result);
		
		
		
//		
//		request.setCharacterEncoding("utf-8");// 设置解码方式
//		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
//		
//		
//		Map<String, String> params = FastjsonTools.getParams(request
//				.getInputStream());
//		
//		
//		int key = Integer.parseInt(params.get("type"));
//		String userid = params.get("userid");
//		String result = null;
//       String order_id;
//		switch (key) {
//		case 1530:// 创建新订单
//			String carts = params.get("carts");
//			String[] cart_ids = carts.split("-");
//			String addressId = params.get("addressid");
//			String address = params.get("address");
//			String phone = params.get("phone");
//			int delivery = Integer.parseInt(params.get("delivery"));
//			int pay = Integer.parseInt(params.get("pay"));
//			int number = Integer.parseInt(params.get("number"));
//			double price = Double.parseDouble(params.get("price"));
//
//			result = ProductOrderService.getOrderService().AddProductOrder(
//					cart_ids, addressId, userid, delivery, pay);
//
//			break;
//		case 1528:// 删除订单 取消订单
//			order_id = params.get("order_id");
//			result = ProductOrderService.getOrderService().DeleteProductOrder(
//					order_id, userid);
//
//			break;
//		case 1533:// 支付订单
//			order_id = params.get("order_id");
//			result = ProductOrderService.getOrderService().PayOrderPayState(
//					order_id, userid);
//
//			break;
//		case 1545:// 为用户查询所有订单
//			result = ProductOrderService.getOrderService().getOrderByUserId(
//					userid);
//                  break;
//                  
//		case 1546:// 为用户查询所有订单  分页查询  每次查询10条
//			//获取请求的页数
//		int	reg_pag=Integer.parseInt(request.getParameter("reg_pag")) ;
//			result = ProductOrderService.getOrderService().getOrderByUserId(
//					userid);
//                  break;
//                  
//		case 1590:// 为用户查询未支付账单 0为未支付状态吗 1为已支付 待发货 2 已发货
//			result = ProductOrderService.getOrderService()
//					.getOrderByUserIdAndState(userid, 0);
//			break;
//		case 1591:// 为用户查询未发货账单
//			result = ProductOrderService.getOrderService()
//					.getOrderByUserIdAndState(userid, 1);
//			break;
//		case 1592:// 为用户查询已发货账单
//			result = ProductOrderService.getOrderService()
//					.getOrderByUserIdAndState(userid, 2);
//			break;
//		case 1281:// 根据指定订单ID查询所有商品
//			order_id = params.get("order_id");
//			result = ProductOrderService.getOrderService()
//					.getOrderItemByOrderId(order_id);
//			break;
//
//		}
//		if(result!=null){
//			response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
//		}else{
//			response.setHeader("response", ConfigValues.RESPONSE_ERROR);
//		}
//		System.out.println("order-->>"+result);
//		response.getWriter().write(result);
//		
	
	}
	
	
}
