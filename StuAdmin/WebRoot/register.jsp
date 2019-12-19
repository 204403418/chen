<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>账号注册</title>
<link href="css/style.css" rel="stylesheet" />
<style type="text/css">
			html,body{
				width: 100%;
				height: 100%;
				margin: 0;
				padding-top: 0.1px;
			}
			form{
				width: 420px;
				height: 280px;
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
			form input[type=submit]{
				width: 400px;
				padding: 0;
				margin: 0;
				display: block;	
				border: none;
				outline: none;
				line-height: 35px;
				min-height: 35px;
				font-size: 13px;
				border-radius: 5px;
				margin: 20px auto 0;
				background: #409EFF;
				color:#FFFFFF;
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
	<form action="<%=path %>/servlet/RegServlet" method="post">
			<div class="title">账号注册</div>
			<div class="item-box">
				<span>用户名：</span>
				<input type="text" name="user" placeholder="输入用户名"/>
			</div>
			<div class="item-box">
				<span>密码：</span>
				<input type="password" name="pass" placeholder="输入密码"/>
			</div>
			<div class="item-box">
				<span>确认密码：</span>
				<input type="password" name="pass_bak" placeholder="确认密码"/>
			</div>
			<input type="submit" value="注   册" />
			<a class="ps" href="#">
			<%
			Object msg=application.getAttribute("msg");
			application.removeAttribute("msg");
			if(msg!=null){
				out.print(msg.toString());
			}
			%>
			</a>
		</form>
</body>
</html>