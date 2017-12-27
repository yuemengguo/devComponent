<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-1.8.3-min.js"></script>
</head>
<body>
	<script type="text/javascript">
		$(function(){
			$("#btn").click(function(){
				alert("您点击了按钮");
			});
		})
	</script>
	<input type="button" id="btn" value="点击">
	<img src="./images/loading.gif">
</body>
</html>