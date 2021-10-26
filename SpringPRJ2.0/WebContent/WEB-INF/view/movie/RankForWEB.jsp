<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="poly.util.CmmUtil" %>
<%
	String res = CmmUtil.nvl((String)request.getAttribute("res"),"0");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
CGV 영화 홈페이지에서 <%=res %>개의 영화 순위 정보가 수집됨.
</body>
</html>