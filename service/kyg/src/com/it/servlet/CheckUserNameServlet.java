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
import com.it.service.UserService;

/***
 * 检查用户名是否存在
 * 
 * @author Alex
 * 
 */
public class CheckUserNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		String username = request.getParameter("username");
		
       String	result =	UserService.getService().CheckUserdname(username);
		
		response.getWriter().write(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
