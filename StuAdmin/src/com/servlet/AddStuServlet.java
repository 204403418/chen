package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StuDao;

public class AddStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", request.getParameter("id"));
		map.put("name", request.getParameter("name"));
		map.put("sex", request.getParameter("sex"));
		map.put("age", request.getParameter("age"));
		map.put("phone", request.getParameter("phone"));
		map.put("qq", request.getParameter("qq"));
		map.put("cls_id", request.getParameter("cls_id"));
		StuDao stuDao=new StuDao();
		String msg;
		if(stuDao.addStu(map)) {
			msg="添加成功";
		}else {
			msg="添加失败 !";
		}
		request.getServletContext().setAttribute("msg", msg);
		response.sendRedirect("../index.jsp?page=2");
	}

}
