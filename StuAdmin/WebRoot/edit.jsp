<%@page import="java.util.Map"%>
<%@page import="com.dao.StuDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>信息编辑</title>
<link href="css/style.css" rel="stylesheet" />
<script src="js/jquery-2.0.js"></script>
<script type="text/javascript">
		<%
		//消息反馈
		if(application.getAttribute("msg")!=null){
			out.print("alert(\""+application.getAttribute("msg")+"\")");
			application.removeAttribute("msg");
		}
		%>
		</script>
<style>
body{
	padding-top: 0.1px;
}
h3{
	padding-top: 50px;
}
a{
	text-decoration: none;
	display:block;
	width:100%;
	text-align:center;
	color:red;
}
</style>
</head>
	<body>
	<% 
	if(id==null||id==""){
	%>
	<h3>学生id错误</h3>
	<% }else{
		StuDao stuDao=new StuDao();
		Map<String,Object> stu=stuDao.getStu(id);
		if(stu==null){
			out.print("<h3>学生id不存在</h3>");
			return ;
		}
		int sex=1;
		if(Integer.parseInt(stu.get("sex").toString())==0){
			sex=0;
		}
	%>
		<h3>修改学生信息</h3>
		<form name="addform" action="<%=path %>/servlet/UpStuServlet" method="post">
			<div class="item-box">
				<span>学号：</span>
				<input type="text" name="id" placeholder="输入学号" value="<%=stu.get("id")%>"/>
			</div>
			<div class="item-box">
				<span>姓名：</span>
				<input type="text" name="name" placeholder="输入姓名" value="<%=stu.get("name")%>"/>
			</div>
			<div class="item-box radio-box">
				<span>性别：</span>
				<input type="radio" id="r" name="sex" value="<%=sex %>"  checked/>
				<div class="box">
					<span class="i" id="s1" onclick="select(1)"></span>
					<span class="t" onclick="select(1)">男</span>
				</div>
				<div class="box">
					<span class="i-no" id="s2" onclick="select(0)"></span>
					<span class="t" onclick="select(0)">女</span>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="item-box">
				<span>年龄：</span>
				<input type="number" name="age" placeholder="输入" value="<%=stu.get("age")%>"/>
			</div>
			<div class="item-box">
				<span>电话：</span>
				<input type="number" name="phone" placeholder="输入" value="<%=stu.get("phone")%>"/>
			</div>
			<div class="item-box">
				<span>QQ：</span>
				<input type="number" name="qq" placeholder="输入" value="<%=stu.get("qq")%>"/>
			</div>
			<input type="hidden" name="y_id" value="<%=stu.get("id")%>">
			<div class="item-box">
				<span>专业ID：</span>
				<input type="text" name="cls_id" placeholder="输入" value="<%=stu.get("cls_id")%>"/>
			</div>
			<input class="addbtn" type="submit" value="修     改" />
			<a href="<%=path %>/index.jsp?page=1">返回首页</a>
		</form>
		

		<script>
			//选择性别
			select(<%=sex%>);
			function select(i) {
				console.log(i)
				if (i == 1) {
					$("#s1").attr("class", "i");
					$("#s2").attr("class", "i-no");
					$("#r").attr('value', '1');
				}
				if (i == 0) {
					$("#s2").attr("class", "i");
					$("#s1").attr("class", "i-no");
					$("#r").attr('value', '0');
				}
			}
		</script>
		<%} %>
	</body>
</html>