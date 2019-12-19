package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StuDao;

public class UpStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", request.getParameter("id"));
		map.put("y_id", request.getParameter("y_id"));
		map.put("name", request.getParameter("name"));
		map.put("sex", request.getParameter("sex"));
		map.put("age", request.getParameter("age"));
		map.put("phone", request.getParameter("phone"));
		map.put("qq", request.getParameter("qq"));
		map.put("cls_id", request.getParameter("cls_id"));
		System.out.println("性别：" + request.getParameter("sex"));
		StuDao stuDao = new StuDao();
		String msg;
		if (stuDao.upStu(map)) {
			msg = "修改成功";
			request.getServletContext().setAttribute("msg", msg);
			response.sendRedirect("../edit.jsp?id=" + request.getParameter("id"));
		} else {
			msg = "修改失败";
			request.getServletContext().setAttribute("msg", msg);
			response.sendRedirect("../edit.jsp?id=" + request.getParameter("y_id"));
		}

	}
}
