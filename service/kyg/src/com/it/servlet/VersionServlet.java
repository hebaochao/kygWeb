package com.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.configs.ConfigValues;
import com.it.service.VersionsService;

public class VersionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");// 设置解码方式
		response.setContentType("application/json;charset=utf-8"); // 设置编码方式
		int versionid = Integer.parseInt(request.getParameter("ver_id"));

		String result = VersionsService.getVersionsService().CheckVersion(
				versionid);
		if (result != null) {
			response.setHeader("response", ConfigValues.RESPONSE_SUCCESS);
		} else {
			response.setHeader("response", ConfigValues.RESPONSE_ERROR);
		}
		
		response.getWriter().write(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
