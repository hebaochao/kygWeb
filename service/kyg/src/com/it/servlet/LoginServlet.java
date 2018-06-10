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

/****
 * 登陆servlet
 * 
 * @author Alex
 * 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		//测试调试
//		request.setCharacterEncoding("utf-8");// 设置解码方式
//		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
//
//		int type = Integer.parseInt(request.getParameter("type"));
//
//		String result = null;
//		switch (type) {
//		case 3000:// 本网站登陆
//			String username = request.getParameter("username");
//			String passwords = request.getParameter("passwords");
//	
//			result = UserService.getService().login(username, passwords);
//			break;
//		case 3555:// QQ授权登陆
//			String openid = request.getParameter("openid");
//			result = UserService.getService().autologin(openid);
//
//			break;
//
//		}
//
//	
//		response.getWriter().write(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式

		int type = Integer.parseInt(request.getParameter("type"));

		String result = null;
		try {
			switch (type) {
			case 3000:// 本网站登陆
				String username = request.getParameter("username");
				String passwords = request.getParameter("passwords");

				result = UserService.getService().login(username, passwords);
				break;
			case 3555:// QQ授权登陆
				String openid = request.getParameter("openid");
				try {
					result = UserService.getService().autologin(openid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 3666:// weibo授权登陆
				String weibo_openid = request.getParameter("weibo_openid");
				try {
					result = UserService.getService().autologin_weibo(weibo_openid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		response.getWriter().write(result);
		
		
		
		
//		request.setCharacterEncoding("utf-8");// 设置解码方式
//		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
//
//		Map<String, String> params = FastjsonTools.getParams(request
//				.getInputStream());
//		int type = Integer.parseInt(params.get("type"));
//
//		String result = null;
//		switch (type) {
//		case 3000:// 本网站登陆
//			String username = params.get("username");
//			String passwords = params.get("passwords");
//			System.out.println("-->>username" + username);
//			System.out.print("-->>passwords" + passwords);
//			result = UserService.getService().login(username, passwords);
//			break;
//		case 3555:// QQ授权登陆
//			String openid = params.get("openid");
//			result = UserService.getService().autologin(openid);
//
//			break;
//
//		}
//
//		if (!result.equals("")) {
//			response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
//		} else {
//			response.setHeader("response", ConfigValues.RESPONSE_ERROR);
//		}
//		response.getWriter().write(result);

	}

}
