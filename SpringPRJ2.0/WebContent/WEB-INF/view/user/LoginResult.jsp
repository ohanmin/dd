<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
//Controller에 저장된 세션으로 로그인할 때 생성됨
String SS_USER_ID = CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));
String res = CmmUtil.nvl((String)request.getAttribute("res"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 완료</title>
</head>
<body>
<%
String msg = "";
if(res.equals("1")){
	msg = SS_USER_ID + "님이 로그인";
}else if(res.equals("0")){
	msg = "아이디, 비밀번호 일치 x";
}else{
	msg="시스템 이상, 다시 시도";
}
%>
</body>
<%=msg %>
</html>