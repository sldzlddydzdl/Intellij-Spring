<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardmodify.jsp</title>
</head>
<body>
	<h1>수정 화면</h1>
	<form action="/board/modify" method="post">
		<fieldset>
			<legend>수정</legend>
			<input type="hidden" name="id" value="${board.id}"><br>
			<input type="text" name="title" value="${board.title}"><br>
			<input type="text" name="content" value="${board.content}"><br>
			<input type="text" name="writer" value="${board.writer}"><br>
			<input type="hidden" name="writeDate" value="${board.writeDate}"><br>
			<input type="hidden" name="updateDate" value="${board.updateDate}"><br>
			<input type="submit" value="수정">
		</fieldset>
	</form>
</body>
</html>