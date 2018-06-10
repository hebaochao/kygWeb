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
import com.it.domain.Product;
import com.it.service.ProductService;
import com.mysql.jdbc.StringUtils;
/***
 * 根据商品ID
 * 查找商品的详细资料
 * 
 * @author Alex
 *
 */
public class ProductServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		String pid = request.getParameter("pid");
		
		String result=ProductService.getProductService().getProductById(pid);
		
		response.getWriter().write(result);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		
		String pids = request.getParameter("prids");
		int type=Integer.parseInt(request.getParameter("type"));
		
		String [] strs=pids.split("-");
	
		if(type==200){
			String result=ProductService.getProductService().GetProductList(strs);
		
			
			response.getWriter().write(result);
		 }
		
		
		
		
		
		
		
		
//		request.setCharacterEncoding("utf-8");// 设置解码方式
//		response.setContentType("text/html;charset=utf-8"); // 设置编码方式
//		String pids = request.getParameter("prids");
//		int type=Integer.parseInt(request.getParameter("type"));
//		
//		String [] strs=pids.split("-");
//		System.out.println(strs[0]);
//		if(type==200){
//			String result=ProductService.getProductService().GetProductList(strs);
//			System.out.println("--result>>"+result);
//			if (result != null) {
//				response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
//			} else {
//				response.setHeader("response", ConfigValues.RESPONSE_ERROR);
//			}
//			response.getWriter().write(result);
//		 }
		
		
		
		
		
	}

}
