<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="<%=request.getContextPath() %>/jquery/jquery.js"></script>
<script  src="/bootstrap3.3.7/js/bootstrap.js"></script>
<link href="/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<title>添加文件</title>
</head>
<body>
	<div>
		<form action="<%=request.getContextPath() %>/uploadServlet"	enctype="multipart/form-data" method="POST">
			<table class="table table-hover">
			<thead><h2>文件上传</h2></thead>
			<tbody>
				<tr><td>选择文件:</td><td><input type="file" name="file" id="file"></td></tr>
				<tr><td>文件分类:</td><td>
				   <input type = 'checkbox' name = "class" value = 'JAVA'/>JAVA
				   <input type = 'checkbox' name = "class" value = 'C/C++'/>C/C++
				   <input type = 'checkbox' name = "class" value = 'PHP'/>PHP
				   <input type = 'checkbox' name = "class" value = 'HTML/CSS'/>HTML/CSS<br/>
				   <input type = 'checkbox' name = "class" value = 'JavaScript'/>JavaScript
				   <input type = 'checkbox' name = "class" value = 'Python'/>Python
				   <input type = 'checkbox' name = "class" value= '算法'/>算法
		    </td></tr>
		    		<tr><td>文件描述:</td><td><textarea rows="6" cols="50" name="description"></textarea></td></tr>
		    		<tr><td colspan="2"><input type="submit" class="btn btn_lg btn-primary" value="上传"></td></tr>
			</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){

})
</script>
</html>