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
import com.it.service.UserService;
import com.mysql.jdbc.StringUtils;

/********
 * 注册
 * 
 * @author Alex
 * 
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		
		//获取参数
		String username = request.getParameter("username");
		String passwords = request.getParameter("passwords");
		String phone = request.getParameter("phone");

		//处理并返回结果
		String result = UserService.getService().register(username, passwords,
				phone);
	
        //回送结果到客户端
		response.getWriter().write(result);
		

	}

}
