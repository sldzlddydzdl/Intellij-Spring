<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm.jsp</title>
</head>
<body>
	<h1>삭제 페이지</h1>
	삭제 할 유저 아이디 : ${user.userId}<br>
	<form action="/user/delete" method="post">
		<input type="hidden" name="id" value="${user.id}">
		비밀번호를 입력하세요<br>
		<input type="password" name="password">
		<input type="submit" value="삭제">
	</form>
</body>
</html>