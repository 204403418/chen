<%@page import="com.dao.StuDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
StuDao stuDao=new StuDao();
List<Map<String,Object>> list=stuDao.getStuList();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>学生管理</title>
		<script src="js/jquery-2.0.js"></script>
		<link href="css/style.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript">
		<%
		//消息反馈
		if(application.getAttribute("msg")!=null){
			out.print("alert(\""+application.getAttribute("msg")+"\")");
			application.removeAttribute("msg");
		}
		%>
		</script>
	</head>
	<body>
		<div class="content">
			<div class="letf-box">
				<div class="title">
					学生管理系统
				</div>
				<a href="?page=1">
					<div class="item" id="it1">
						学生信息列表
					</div>
				</a>
				<a href="?page=2">
					<div class="item" id="it2" onclick="tab(2)">
						添加学生信息
					</div>
				</a>
				<a href="<%=path %>/out.jsp">
					<div class="item" id="it3">
						退出登录
					</div>
				</a>
			</div>
			<div class="right-box">
				<div class="hreder-box" id='title'>
					
				</div>
				<div class="main">
					<!-- 添加学生 -->
					<div class="view" id="addStu">
						<form name="addform" action="<%=path %>/servlet/AddStuServlet" method="post">
							<div class="item-box">
								<span>学号：</span>
								<input type="text" name="id" placeholder="输入学号"/>
							</div>
							<div class="item-box">
								<span>姓名：</span>
								<input type="text" name="name" placeholder="输入姓名"/>
							</div>
							<div class="item-box radio-box">
								<span>性别：</span>
								<input type="radio" id="r" name="sex" value="1" checked="checked"/>
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
								<input type="number" name="age" placeholder="输入年龄"/>
							</div>
							<div class="item-box">
								<span>电话：</span>
								<input type="number" name="phone" placeholder="输入"电话/>
							</div>
							<div class="item-box">
								<span>QQ：</span>
								<input type="number" name="qq" placeholder="输入QQ"/>
							</div>
							<div class="item-box">
								<span>专业ID：</span>
								<input type="text" name="cls_id" placeholder="输入专业id"/>
							</div>
							<input class="addbtn" type="submit" value="添     加" />

						</form>
					</div>


					<!-- 学生列表 -->
					<div class="view" id="stuList">
						<%
						if(list.size()==0){
							out.print("<h3>暂无学生数据，请先添加学生数据！</h3>");
						}else{
						%>
						
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年龄</th>
								<th>电话</th>
								<th>QQ</th>
								<th>专业ID</th>
								
								<th>
									<form class="so" action="#" method="get">
										<div class="so-box">
											<input type="text" name="id" placeholder="搜索关键字"/>
											<input type="submit" class="so-btn" value=""/>
										</div>
									</form>
								</th>
							</th>
							<%
							for(int i=0;i<list.size();i++){
							%>
							<tr>
								<td><%=list.get(i).get("id") %></td>
								<td><%=list.get(i).get("name") %></td>
								<td><%
								if( Integer.parseInt(list.get(i).get("sex").toString())==1 ){
									out.print("男");
								}else{
									out.print("女");
								} %></td>
								<td><%=list.get(i).get("age") %></td>
								<td><%=list.get(i).get("phone") %></td>
								
								<td><%=list.get(i).get("qq") %></td>
								<td><%=list.get(i).get("cls_id") %></td>
								<td>
									<a href="<%=path %>/edit.jsp?id=<%=list.get(i).get("id")%>"><div class="btn edit">编辑</div></a>
									<a href="<%=path %>/servlet/DelStuServlet?id=<%=list.get(i).get("id") %>"><div class="btn del">删除</div></a>
								</td>
							</tr>
							<%} %>
						</table>
						
						<%} %>
					</div>
				</div>
			</div>
		</div>

		<script>
			<% 
			String item=request.getParameter("page");
			int i=1;
			if(item!=null&&item.equals("2")){
				i=2;
			}
			%>
			var item=<%=i%>;
			if(item==1){
				$('#stuList').show();
				$('#it1').css('background','#2C3138');
				$('#title').text('学生信息列表')
			}else if(item==2){
				$('#addStu').show();
				$('#it2').css('background','#2C3138');
				$('#title').text('添加学生信息')
			}
			
			//选择性别
			function select(i){
				console.log(i)
				if(i==1){
					$("#s1").attr("class","i");
					$("#s2").attr("class","i-no");
					$("#r").attr('value', '1');
				}
				if(i==0){
					$("#s2").attr("class","i");
					$("#s1").attr("class","i-no");
					$("#r").attr('value', '0');
				}
			}
		</script>
	</body>
</html>
