<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 문법</title>

<style>

	a{
		color : black;
		text-decoration : none;
		font-weight : bold;
		
	}
</style>

</head>

<body>

	<h1>* EL(Expression Language)표현 언어</h1>
	
	<p>
		기존 JSP 상에 사용햇던 &lt;%= %> 같은 경우 사용이 복잡하고, <br>
		컴파일시 문제가 발생할 수 있기 때문에 <br>
		이 문제를 대체 할 수 있는 문법
		
	</p>
	
	<h3>1. EL구문 기본학습</h3>
	
	<!--
		절대경로 : /mcd(context root)/sc(servlet 매핑값)
		상대경로 : sc
	 -->
	<a href="/mcd/sc">a 태그지롱</a>
	
	<br><br><br>
	<a href="include">포함포함~</a>
	
	<br><br><br>
	<a href="forward">포워드 JSP로 이동</a>
	
	<br><br><br>
	<a href="jstl">실과 바늘 배우기</a>
	

</body>

</html>