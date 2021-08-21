<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
	<h1>유저 정보 목록화면</h1>
	<a href="/user/input">유저 정보 등록</a>
	<section>
		<c:forEach var="user" items="${userList}">
		<fieldset>
			id : ${user.id}<br>
			유저아이디 : ${user.userId}<br>
			비밀번호 : ${user.password}<br>
			생년월일 : ${user.birthDate}<br>
			등록일 : ${user.joinDate }
		</fieldset>
		</c:forEach>
	</section>
</body>
</html>