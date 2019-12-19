package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dao.UserDao;
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		String pass_bak=request.getParameter("pass_bak");
		UserDao userDao=new UserDao();
		//账号验证
		if(pass.equals(pass_bak)) {
			if(user.matches("^[0-9a-zA-Z]{6,15}$")) {
				//账号格式通过
				if(pass.matches("^[0-9a-zA-Z]{6,20}$")) {
					//密码格式通过
					if(!userDao.isUser(user)) {
						if(userDao.addUser(user, pass)) {
							request.getServletContext().setAttribute("errorMsg", "恭喜您！注册成功");
							response.sendRedirect("../login.jsp");
						}else {
							request.getServletContext().setAttribute("msg", "注册失败");
							response.sendRedirect("../register.jsp");
						}
					}else {
						request.getServletContext().setAttribute("msg", "用户名已被使用");
						response.sendRedirect("../register.jsp");
					}
				}else {
					request.getServletContext().setAttribute("msg", "密码只能是6-20位字母或数字");
					response.sendRedirect("../register.jsp");
				}
			}else {
				request.getServletContext().setAttribute("msg", "账号只能是6-15位字母或数字");
				response.sendRedirect("../register.jsp");
			}
		}else {
			request.getServletContext().setAttribute("msg", "两次输入的密码不一致");
			response.sendRedirect("../register.jsp");
		}
	}

}
