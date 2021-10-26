<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 화면</title>
<script type="text/javascript">
	function doLoginUserCheck(f){
		if(f.user_id.value==""){
			alert("아이디를 입력하세요");
			f.user_id.focus();
			return false;
		}
		if(f.password.value==""){
			alert("비밀번호를 입력하세요");
			f.password.focus();
			return false;
		}
	}
</script>
</head>
<body>
<h1>로그인 화면</h1>
<br>
<form name="f" method="post" action="/user/getUserLoginCheck.do"onsubmit="return doLoginUserCheck(this);">
<table border="1">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="user_id"></td>
		<td>비밀번호</td>
		<td><input type="password" name="password"></td>
	</tr>

</table>
<input type="submit" value="로그인">
</form>
</body>
</html>