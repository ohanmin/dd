<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
//Controller�� ����� �������� �α����� �� ������
String SS_USER_ID = CmmUtil.nvl((String)session.getAttribute("SS_USER_ID"));
String res = CmmUtil.nvl((String)request.getAttribute("res"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� �Ϸ�</title>
</head>
<body>
<%
String msg = "";
if(res.equals("1")){
	msg = SS_USER_ID + "���� �α���";
}else if(res.equals("0")){
	msg = "���̵�, ��й�ȣ ��ġ x";
}else{
	msg="�ý��� �̻�, �ٽ� �õ�";
}
%>
</body>
<%=msg %>
</html>