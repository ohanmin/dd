<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
	//Controller���� model.addAttribute("res", String.valueOf(res)); �� ���� ����� �� �ҷ�����
	//CmmUtil.nvl() �Լ��� ���� model.addAttribute("res")������ ���� NULL�� ���, 0���� ���� ����ǵ��� ���� ó����
	//0�̸� ���� �߼� �����ϴ� ������ �������� Service���� �����߱� ������ 0���� �� ����
	String jspRes = CmmUtil.nvl((String)request.getAttribute("res"),"0");
	//�� URL�κ��� ���޹��� ����(�������� �⺻������ ������ ������� ������ �̵��ϱ� ������ url�� �Է¹��� request
	//���� �����ü�����, �Ϲ����� jsp���� �Ұ�����	)
	String toMail = CmmUtil.nvl(request.getParameter("toMail"));
	String title = CmmUtil.nvl(request.getParameter("title"));
	String contents = CmmUtil.nvl(request.getParameter("contents"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
//���� �߼��� �����ߴٸ� ...
if (jspRes.equals("1")){
	out.print(toMail + "�� ���� ������ �����Ͽ����ϴ�.");
	out.print("���̷���" + title);
	out.print("���� ����"+ contents);
//���� �߼��� �����ߴٸ�...	
}else{
	out.println(toMail+"�� ���� ������ �����Ͽ����ϴ�.");
}
%>
</body>
</html>