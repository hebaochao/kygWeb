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
import com.it.service.MessageService;
import com.it.service.UserService;

/***
 * 处理 用户信息网络层
 * 
 * @author Alex
 * 
 */
public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
	
		String userid = request.getParameter("userid");
		int type = Integer.parseInt(request.getParameter("type"));
		String result = "";
		switch (type) {

		case 311:// 根据用户ID查找用户 并把新的User实体类的json数据
			result = UserService.getService().FindUserByUserid(userid);
			break;
		
		
		case 381://获取用户消息集合
			result = MessageService.getMessageService().getMessageByUserID(
					userid);
			break;

		}

	
		
		response.getWriter().write(result);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
	
		
		String userid = request.getParameter("userid");
		int type = Integer.parseInt(request.getParameter("type"));
		String result = "";
		switch (type) {

		case 311:// 根据用户ID查找用户 并把新的User实体类的json数据
			result = UserService.getService().FindUserByUserid(userid);
			break;
		case 382:// 修改密码
			String password =request.getParameter("password");
			result = UserService.getService().alterUserPassword(userid,
					password);
			break;
		case 352:// 更新用户信息
			
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");

			result = UserService.getService().updateUserConnection(userid,
					email, phone);
			break;
		case 381://获取用户消息集合
			result = MessageService.getMessageService().getMessageByUserID(
					userid);
			break;

		}

		
		response.getWriter().write(result);
		
	}

}
