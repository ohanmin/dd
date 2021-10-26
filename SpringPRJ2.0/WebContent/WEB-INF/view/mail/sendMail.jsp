<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="sendMail.do" method="post">
<table border="1">
	<tr>
	<td>받는사람</td>
	<td><input name="toMail"></td>
	</tr>	
	<tr>
	<td>메일제목</td>
	<td><input name="title"></td>
	</tr>	
	<tr>
	<td>메일내용</td>
	<td><input name="contents"></td>
	</tr>	
</table>
<input type="submit"><input type="reset">
</form>
</body>
</html>