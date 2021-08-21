<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inputForm.jsp</title>
<script>
	window.addEventListener("DOMContentLoaded", function(){
		// console.log("모두 로드됨");
		
		var form = document.querySelector("form");
		console.dir(form);
		form.onsubmit = function(){
			console.log("서브밋 누름");
			
			var pwd = document.querySelector("input[name=password]");
			var pwdConfirm = document.querySelector("input[name=passwordConfirm]");
			// console.log(pwd.value);
			// console.log(pwdConfirm.value); 	
			
			if(pwd.value != pwdConfirm.value){
				
				pwd.focus(); // 커서가 깜빡깜빡 
				return false;
			}
			
			// 서브밋이 진행되지 않도록 함.
			// return false;
		}
	 	
	});
</script>
</head>
<body>
	<h1>사용자 정보를 입력하세요</h1>
	
	<form action="/user/input" method="post">
	<!-- input 의 name 이 UserDto 변수 이름과 똑같아야 맵핑이된다. -->
		<input type="text" name="userId" placeholder="유저 아이디"><br>
		<input type="password" name="password" placeholder="비밀번호"><br>
		<input type="password" name="passwordConfirm" placeholder="비밀번호 확인"><br>
		생년월일 : <input type="date" name="birthDate"><br>
		<input type="submit" value="등록하기">
	</form>
</body>
</html>