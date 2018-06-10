package com.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.domain.Product;
import com.it.service.ProductService;
/****
 * 根据分类、类型、品牌
 * 返回相应的商品集合
 * @author Alex
 *
 */
public class ProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		String key = request.getParameter("type");
		String id = request.getParameter("pid");
		String req_page=request.getParameter("req_page");
	
		String result=null;
		
	if(req_page!=null){//有请求页  分页查询
	
		result=ProductService.getProductService().GetProductList(id, Integer.parseInt(key), Integer.parseInt(req_page), null);
	}else{//无请求页  不分页查询
		result=ProductService.getProductService().GetProductList(id, Integer.parseInt(key));
	}
		
		
	
		if (result != null) {
			response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
		} else {
			response.setHeader("response", ConfigValues.RESPONSE_ERROR);
			result="error";
		}
		
		response.getWriter().write(result);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
