<%@page import="poly.dto.UserInfoDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�������� ���ϵ帳�ϴ�.</title>
<%
//Controller �� ���� ���޹��� ������
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
//Controller�κ��� ���޹��� �����κ��� �Է¹��� ������(ȸ�����̵�, �̸�, �̸���, �ּ� ��)
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
<%=CmmUtil.nvl(pDTO.getUser_name()) %> ���� ȸ�������� ���ϵ帳�ϴ�.
</body>
</html>