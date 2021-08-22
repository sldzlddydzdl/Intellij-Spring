<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardlist.jsp</title>
</head>
<body>
	<h1>댓글 list</h1>
	<a href="/board/input">등록화면</a>
	<section>
		<c:forEach var="board" items="${boardList}">
		<fieldset>
			id : ${board.id}<br>
			제목 : ${board.title}<br>
			글 : ${board.content}<br>
			글쓴이 : ${board.content}<br>
			작성일 : ${board.writeDate}<br>
			수정일 : ${board.updateDate}
			<a href="/board/modify?id=${board.id}">수정</a>
			<a href="/board/delete?id=${board.id}">삭제</a>
		</fieldset>
		</c:forEach>
	</section>
</body>
</html>