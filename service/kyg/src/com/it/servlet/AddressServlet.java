package com.it.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.service.AddressService;

/***
 * 地址访问的网络接口层
 * 中文乱码问题尚未解决
 * @author Alex
 * 
 */
public class AddressServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式

	
		int key = Integer.parseInt( request.getParameter("type"));
		String userid =  request.getParameter("userid");
	
		String result = "";
		String id;
		
		switch (key) {
		case 666:// 根据用户ID获取所有地址列表
			result = AddressService.getAddressService().getAddressByuserid(
					Long.valueOf(userid));

			break;
		
		case 888:// 删除一条地址记录
			id = request.getParameter("address_id");
			result = AddressService.getAddressService().deleteAddress(
					Long.parseLong(id), Long.parseLong(userid));

			break;

		}
		
		
		response.getWriter().write(result);
		
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式

	
		int key = Integer.parseInt( request.getParameter("type"));
		String userid =  request.getParameter("userid");
		
		String result = "";
		String re_name;
		String phone;
		String address;
		String id;
		
		switch (key) {
		case 666:// 根据用户ID获取所有地址列表
			result = AddressService.getAddressService().getAddressByuserid(
					Long.valueOf(userid));

			break;
		case 562:// 为用户添加一条新的地址记录
			re_name = request.getParameter("re_name");
			phone = request.getParameter("phone");
			address = request.getParameter("address");
			result = AddressService.getAddressService().AddAddress(
					Long.valueOf(userid), re_name, phone, address);

			break;
		case 359:// 更新一条地址记录

			id = request.getParameter("address_id");
			re_name = request.getParameter("re_name");
			phone = request.getParameter("phone");
			address = request.getParameter("address");
			result = AddressService.getAddressService().UpdataAddress(
					Long.parseLong(id), Long.parseLong(userid), re_name, phone,
					address);
			break;
		case 888:// 删除一条地址记录
			id = request.getParameter("address_id");
			result = AddressService.getAddressService().deleteAddress(
					Long.parseLong(id), Long.parseLong(userid));

			break;

		}
		
		
		response.getWriter().write(result);
		
		
		
		
		
		
		
		
		
		
		
		
		
//		request.setCharacterEncoding("utf-8");// 设置解码方式
//		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
//
//		Map<String, String> params = FastjsonTools.getParams(request
//				.getInputStream());
//
//		int key = Integer.parseInt( params.get("type"));
//		String userid =  params.get("userid");
//		System.out.println("userid" + userid);
//		String result = "";
//		String re_name;
//		String phone;
//		String address;
//		String id;
//		switch (key) {
//		case 666:// 根据用户ID获取所有地址列表
//			result = AddressService.getAddressService().getAddressByuserid(
//					Long.valueOf(userid));
//
//			break;
//		case 562:// 为用户添加一条新的地址记录
//			re_name = params.get("re_name");
//			phone = params.get("phone");
//			address = params.get("address");
//			result = AddressService.getAddressService().AddAddress(
//					Long.valueOf(userid), re_name, phone, address);
//
//			break;
//		case 359:// 更新一条地址记录
//
//			id = params.get("id");
//			re_name = params.get("re_name");
//			phone = params.get("phone");
//			address = params.get("address");
//			result = AddressService.getAddressService().UpdataAddress(
//					Long.parseLong(id), Long.parseLong(userid), re_name, phone,
//					address);
//			break;
//		case 888:// 删除一条地址记录
//			id = params.get("id");
//			result = AddressService.getAddressService().deleteAddress(
//					Long.parseLong(id), Long.parseLong(userid));
//
//			break;
//
//		}
//		if (result != null && !result.equals("")) {
//			response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
//		} else {
//			response.setHeader("response", ConfigValues.RESPONSE_ERROR);
//			result="error";
//		}
//		System.out.println("address" + result);
//		response.getWriter().write(result);

		// int key = Integer.parseInt(request.getParameter("type"));
		// String userid = request.getParameter("userid");
		// System.out.println("userid" + userid);
		// String result = "";
		// String re_name;
		// String phone;
		// String address;
		// String id;
		// switch (key) {
		// case 666:// 根据用户ID获取所有地址列表
		// result = AddressService.getAddressService().getAddressByuserid(
		// Long.valueOf(userid));
		//
		// break;
		// case 562:// 为用户添加一条新的地址记录
		// re_name = request.getParameter("re_name");
		// phone = request.getParameter("phone");
		// address = request.getParameter("address");
		// result = AddressService.getAddressService().AddAddress(
		// Long.valueOf(userid), re_name, phone, address);
		//
		// break;
		// case 359:// 更新一条地址记录
		//
		// id = request.getParameter("id");
		// re_name = request.getParameter("re_name");
		// phone = request.getParameter("phone");
		// address = request.getParameter("address");
		// result = AddressService.getAddressService().UpdataAddress(
		// Long.parseLong(id), Long.parseLong(userid), re_name, phone,
		// address);
		// break;
		// case 888:// 删除一条地址记录
		// id = request.getParameter("id");
		// result = AddressService.getAddressService().deleteAddress(
		// Long.parseLong(id), Long.parseLong(userid));
		//
		// break;
		//
		// }
		// if (result != null && !result.equals("")) {
		// response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
		// } else {
		// response.setHeader("response", ConfigValues.RESPONSE_ERROR);
		// }
		// System.out.println("address" + result);
		// response.getWriter().write(result);
	}
}
