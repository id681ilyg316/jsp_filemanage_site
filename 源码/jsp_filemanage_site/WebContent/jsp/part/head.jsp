<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.xatu.file.servlet.*"%>
<html>
<head>
<script src="<%=request.getContextPath() %>/jquery/jquery.js"></script>
<script
	src="/bootstrap3.3.7/js/bootstrap.js"></script>
<link
	href="/bootstrap3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航栏</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid" style="bgcolor: #2BD5D5">
			<!-- <a class="navbar-brand" href="Welcome.jsp">网站主页</a>
	 -->
			<ul class="nav navbar-nav">
				<li><a href="/filemanage_site/jsp/page/Welcome.jsp">网站主页</a></li>
				<li><a href="/filemanage_site/jsp/page/All_document.jsp">全部</a></li>
				<li><a href="/filemanage_site/jsp/page/Image_page.jsp">图片</a></li>
				<!--  class="active" -->
				<li><a href="/filemanage_site/jsp/page/Document_page.jsp">文档</a></li>
				<li><a href="/filemanage_site/jsp/page/vidio_page.jsp">视频</a></li>
				<li><a href="/filemanage_site/jsp/page/BT_page.jsp">BT种子</a></li>
				<li><a href="/filemanage_site/jsp/page/Exe_page.jsp">应用</a></li>
				<li><a href="/filemanage_site/jsp/page/Other_page.jsp">其他</a></li>
				<li><a href="/filemanage_site/jsp/page/uploadFile.jsp">上传</a></li>
			</ul>
			<ul class="nav navbar-nav" style="float: right">
				<li id="tname"></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"> 注册 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/filemanage_site/jsp/page/StudentRegist.jsp">学生注册</a></li>
						<li><a href="/filemanage_site/jsp/page/TeacherRegist.jsp">老师注册</a></li>
						<li><a href="/filemanage_site/LoginOutServlet">注销登录</a></li>

					</ul></li>
			</ul>
		</div>
	</nav>
</body>
<script type="text/javascript">
$(document).ready(function(){
	check();
})
function check(){
	
			var username ="<%=session.getAttribute("username")%>";
			//alert(username);
			if(username=="null"){
				url1="/filemanage_site/jsp/page/Login.jsp";
				$("#tname").html("<a href="+url1+">请你登陆!</a>");
			}else{
				$("#tname").html("<a href='/filemanage_site/jsp/user/Personal.jsp'>"+username+"</a>");
			}
 
	
}

</script>
</html>
