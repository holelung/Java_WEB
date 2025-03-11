<%@page import="com.kh.burgerking.model.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 자바코드 쓸 수 있음	
	String message = (String)request.getAttribute("message");
	UserDTO user = (UserDTO)request.getAttribute("user");

%>   
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	
	<!--
	 	"사용자가 입력한 userName"님!!
	 	
	 	회원가입에 성공하셨습니다!
	 	
	 	가입한 아이디 : userId 
	 -->
	
	<!--${user.userId} 님 
		이런식으로 가능하다.
	-->
	<h1><%=user.getUserName() %></h1>
	<p>
		<%=message%><br>
		회원가입에 성공하셨습니다!<br><br>
	
		가입에 성공한 아이디 : <%=user.getUserId() %>
	</p>

	

</body>

</html>