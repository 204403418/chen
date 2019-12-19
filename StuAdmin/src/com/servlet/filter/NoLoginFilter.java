package com.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NoLoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) arg0;
		if(request.getRequestURI().indexOf("login.jsp")!=-1||
				request.getRequestURI().indexOf("/LoginServlet")!=-1||
				request.getRequestURI().indexOf("RegServlet")!=-1||
				request.getRequestURI().indexOf("register.jsp")!=-1||
				request.getRequestURI().indexOf("/css/")!=-1||
				request.getRequestURI().indexOf("/js/")!=-1){
			arg2.doFilter(arg0, arg1);
			return;
		}
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null){
			arg2.doFilter(arg0, arg1);
		}else{
			//request.getRequestDispatcher("login.jsp").forward(arg0, arg1);
			HttpServletResponse response=(HttpServletResponse) arg1;
			System.out.println(request.getHeader("Host"));
			response.sendRedirect("http://"+request.getHeader("Host")+"/"+request.getContextPath()+"/login.jsp?page="+request.getRequestURL());
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
