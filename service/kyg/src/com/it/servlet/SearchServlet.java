package com.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

/****
 * 搜索商品 模糊查询数据库相关商品 返回List集合
 * 
 * @author Alex
 * 
 */
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式

		String keyword = request.getParameter("keyword");
		int type = Integer.parseInt(request.getParameter("type")) ;
		String req_page=request.getParameter("req_page");
		
		String result="";
		
	if(req_page!=null){//有请求页  分页查询
		
		result=ProductService.getProductService().GetProductList(null, type ,Integer.parseInt(req_page), keyword);
	}else{
		result = ProductService.getProductService().SearchLikeProduct(
				keyword,type);
	}
		
	response.getWriter().write(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
