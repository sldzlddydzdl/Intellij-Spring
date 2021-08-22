<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardinput.jsp</title>
</head>
<body>
	<h1> Board 데이터 주입 </h1>
	<form action="/board/input" method="post">
		<input type="text" name ="title"><br>
		<input type="text" name="content" ><br>
<!-- 		<textarea name="content" rows="10" cols="25"></textarea><br> -->
		<input type="text" name="writer"><br>
		<input type="hidden" name="writeDate">
		<input type="hidden" name="updateDate">
		<input type="submit" value="글쓰기">
	</form>
</body>
</html>