package com.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.service.HomeService;
import com.it.service.ProductService;

/****
 * 主页资源获取访问 获取广告条 获取专题活动相关商品
 * 
 * @author Alex
 * 
 */
public class HomeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		int type = Integer.parseInt(request.getParameter("type"));
		String gsonString = "";
		switch (type) {
		case 100:

			gsonString = HomeService.getHomeService().GetHome_Banner();
//			typename = "home_banner";

			break;
		case 101:

			gsonString = ProductService.getProductService().GetHotProduct();

//			typename = "home_hotproduct";
			break;
		case 102:
			gsonString = ProductService.getProductService().GetNewsProduct();

//			typename = "home_newproduct";
			break;
		case 103:
			gsonString = ProductService.getProductService().GetScalProduct();

//			typename = "home_scalproduct";
			break;
		case 104:
			gsonString = ProductService.getProductService()
					.GetEveryOneProduct();
//			typename = "home_everyoneproduct";
			break;
		}

//		response.setHeader("type", typename);
//		if (gsonString != null) {
//			response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
//		} else {
//			response.setHeader("response", ConfigValues.RESPONSE_ERROR);
//		}
//		System.out.println(typename+"---"+gsonString);
		
	//	System.out.println("aaaaaaa"+gsonString);
		response.getWriter().write(gsonString);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
