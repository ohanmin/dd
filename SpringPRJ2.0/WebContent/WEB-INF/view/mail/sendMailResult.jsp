<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="poly.util.CmmUtil" %>
<%
	//Controller에서 model.addAttribute("res", String.valueOf(res)); 로 부터 저장된 값 불러오기
	//CmmUtil.nvl() 함수를 통해 model.addAttribute("res")저장한 값이 NULL인 경우, 0으로 값이 저장되도록 로직 처리함
	//0이면 메일 발송 실패하는 것으로 스프링의 Service에서 정의했기 때문에 0으로 한 것임
	String jspRes = CmmUtil.nvl((String)request.getAttribute("res"),"0");
	//웹 URL로부터 전달받은 값을(스프링은 기본적으로 포워드 방식으로 펭지를 이동하기 때문에 url에 입력받은 request
	//값을 가져올수있음, 일반적인 jsp에선 불가능함	)
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
//메일 발송이 성공했다면 ...
if (jspRes.equals("1")){
	out.print(toMail + "로 메일 전송이 성공하였습니다.");
	out.print("메이레목" + title);
	out.print("메일 내용"+ contents);
//메일 발송이 실패했다면...	
}else{
	out.println(toMail+"로 메일 전소이 실패하였습니다.");
}
%>
</body>
</html>