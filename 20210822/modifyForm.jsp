<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyForm.jsp</title>
</head>
<body>
	<h1>유저 수정 화면</h1>
	<form action="/user/modify" method="post">
		<fieldset>
			<legend>수정</legend>
			<!-- name 이 key 입력한값이 value 로 되서 key value 형식으로 쿼리스트링으로 url 에 들어가게된다. -->
			<input type="text" value="${user.id}" name="id" readonly="readonly" ><br>
			<input type="text" value="${user.userId}" name="userId" placeholder="유저 아이디" ><br>
			<input type="password" value="${user.password}" name="password" placeholder="비밀번호" ><br>
			생년월일 : <input type="date" name="birthDate" value="${user.birthDate}" ><br>
			<input type="datetime-local" value="${user.joinDate}" name="joinDate" readonly="readonly" ><br>
			<input type="submit" value="수정하기">
		</fieldset>
	</form>
</body>
</html>