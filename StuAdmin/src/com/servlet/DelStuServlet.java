package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StuDao;

/**
 * Servlet implementation class DelStuServlet
 */
public class DelStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		StuDao stuDao=new StuDao();
		String msg;
		if(stuDao.delStu(id)) {
			//删除成功
			msg="成功删除学号为"+id+"的学生数据";
		}else {
			//删除失败
			msg="删除成功";
		}
		request.getServletContext().setAttribute("msg", msg);
		response.sendRedirect("../index.jsp");
	}

}
