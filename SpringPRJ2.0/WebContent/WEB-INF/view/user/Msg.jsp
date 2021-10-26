<%@page import="poly.dto.UserInfoDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입을 축하드립니다.</title>
<%
//Controller 로 부터 전달받은 데이터
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
//Controller로부터 전달받은 웹으로부터 입력받은 데이터(회원아이디, 이름, 이메일, 주소 등)
UserInfoDTO pDTO = (UserInfoDTO)request.getAttribute("pDTO");
if(pDTO==null){
	pDTO = new UserInfoDTO();
}
%>
<script type= "text/javascript">
	alert("<%=msg %>");
</script>
</head>
<body>
<%=CmmUtil.nvl(pDTO.getUser_name()) %> 님의 회원가입을 축하드립니다.
</body>
</html>