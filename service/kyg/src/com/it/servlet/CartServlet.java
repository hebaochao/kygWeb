package com.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.service.CartService;

/****
 * 提供购物车相关擦服务 添加商品到购物车 删除商品 修改商品 查看购物车商品列表
 * 
 * @author Alex
 * 
 */
public class CartServlet extends HttpServlet {

	private String cart_id;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//测试代码块
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		//response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式

		int key = Integer.parseInt(request.getParameter("type"));
		String userid = request.getParameter("userid");

		String result = "";

		String pro_id;
		int number;
		switch (key) {
		case 300:// 根据用户ID查看购物车中的商品列表

			result = CartService.getCartService().getCarProductsByUserIdTojson(
					userid);
			break;
		case 400:// 添加商品到购物车中

			pro_id = request.getParameter("pro_id");
			number = Integer.parseInt(request.getParameter("number"));

			result = CartService.getCartService().addCarProductsTojson(userid,
					pro_id, number);
			break;
		case 500:// 修改购物车某个商品的数量
			pro_id = request.getParameter("pro_id");
			cart_id = request.getParameter("cart_id");
			number = Integer.parseInt(request.getParameter("number"));
			result = CartService.getCartService().updateCarProductsTojson(
					userid, pro_id, cart_id, number);
			break;
		case 600:// 从购物车删除指定商品

			cart_id = request.getParameter("cart_id");
		
			result = CartService.getCartService().deleteCarProductsTojson(
					userid, cart_id);
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
	
		String result = "";

		String pro_id;
		int number;
		switch (key) {
		case 300:// 根据用户ID查看购物车中的商品列表

			result = CartService.getCartService().getCarProductsByUserIdTojson(
					userid);
			break;
		case 400:// 添加商品到购物车中

			pro_id = request.getParameter("pro_id");
			number = Integer.parseInt(request.getParameter("number"));

			result = CartService.getCartService().addCarProductsTojson(userid,
					pro_id, number);
			break;
		case 500:// 修改购物车某个商品的数量
			pro_id = request.getParameter("pro_id");
			cart_id = request.getParameter("cart_id");
			number = Integer.parseInt(request.getParameter("number"));
			result = CartService.getCartService().updateCarProductsTojson(
					userid, pro_id, cart_id, number);
			break;
		case 600:// 从购物车删除指定商品

			cart_id =request.getParameter("cart_id");
			result = CartService.getCartService().deleteCarProductsTojson(
					userid, cart_id);
			break;

		}
	
	
		response.getWriter().write(result);

		
	}

}
