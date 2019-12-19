package com.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String errorMsg;
		if (user != null && pass != null && user != "" && pass != "") {
			UserDao userDao = new UserDao();
			Map<String, Object> userInfo = userDao.getUserInfo(user);
			if (userInfo != null) {
				if (userInfo.get("pass").equals(pass)) {
					// 登录成功
					request.setAttribute("user", user);
					HttpSession session=request.getSession();
					session.setAttribute("user", user);
					System.out.println(request.getAttribute("user"));
					String url=request.getParameter("url");
					if(url==null) {
						response.sendRedirect("../index.jsp");
					}else {
						response.sendRedirect(url);
					}
					return ;
				} else {
					errorMsg = "密码错误！点击可注册账号"; //密码错误
				}
			} else {
				errorMsg = "账号不存在！点击可注册账号"; //user不存在
			}
		} else {
			errorMsg="输入有误！点击可注册账号"; //user不能为空
		}
		this.getServletContext().setAttribute("errorMsg", errorMsg);
		response.sendRedirect("../login.jsp");
	}
	
}
