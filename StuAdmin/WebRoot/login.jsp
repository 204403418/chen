<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(request.getSession().getAttribute("user")!=null){
	response.sendRedirect("index.jsp");
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登陆</title>
		<style>
			html,body{
				width: 100%;
				height: 100%;
				margin: 0;
				padding-top: 0.1px;
			}
			form{
				width: 400px;
				height: 230px;
				border: 1px solid #dbdbdb;
				border-radius: 5px;
				overflow: hidden;
				margin: 100px auto 0;
			}
			form .title{
				width: 100%;
				height: 40px;
				line-height: 40px;
				font-size: 15px;
				font-weight: bold;
				text-align: center;
				background: #ECF8FF;
				margin-bottom: 20px
			}
			form input[type=text],form input[type=password]{
				outline: none;
				box-sizing: border-box;
				padding: 10px;
				border-radius: 5px;
				width: 80%;
				border: 1px solid #DCDFE6;
				display: block;
				margin: 10px auto 0;
			}
			form input[type=submit]{
				width: 80%;
				padding: 0;
				margin: 0;
				display: block;	
				border: none;
				outline: none;
				line-height: 35px;
				min-height: 35px;
				font-size: 13px;
				border-radius: 5px;
				margin: 10px auto 0;
				background: #409EFF;
				color:#FFFFFF;
			}
			form input[type=submit]:active{
				opacity: 0.7;
			}
			form .ps{
				display: block;
				width: 100%;
				line-height:30px;
				text-align: center;
				color: red;
				font-size: 13px;
				text-decoration: none;
			}
		</style>
	</head>
	<body>
		<form action="<%=path %>/servlet/LoginServlet" method="post">
			<div class="title">登陆</div>
			<input type="text" name="user" placeholder="用户名"/>
			<input type="password" name="pass" placeholder="密码" />
			<input type="submit" value="登     陆" />
			<a class="ps" href="<%=path %>/register.jsp">
			<%
			Object msg=application.getAttribute("errorMsg");
			application.removeAttribute("errorMsg");
			if(msg!=null){
				out.print(msg.toString());
			}else{
				out.print("账号注册");
			}
			%>
			</a>
		</form>
	</body>
</html>
