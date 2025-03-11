<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>실과 바늘</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	h1 {
	background-color: yellow;
}

</style>

</head>
<body class="p-3">

	<h1> JSTL 이란...</h1>
	
	<p>
		JSP Standard Tag Library 의 약어
		JSP에서 사용할 수 있는 커스텀 액션 태그 <br>
		공통적으로 사용하는 코드들을 보다 쉽게 사용할 수 있도록 태그화해서 표준으로 제공하는 라이브러리
	</p>

	<hr>
	
	<p>
		JSTL을 사용하기 위해선 먼저 라이브러리르 추가해준 후 <br>
		JSTL을 사용하고자 하는 JSP페이지 상단에 선언을 해 주어야함! <br>
	</p>
	
	
	<h2>Core 라이브러리 실전압축핵심요약</h2>
	
	<p> 
		if 라는 태그를 작성하여 조건문을 만들어 낼 수 있음 <br>
		- java에서의 단일 if문과 똑같은 역할을 할 수 있는 태그 <br>
		- 조건식은 test 속성에 작성을 한다. <br>
		(조건식을 만들때 EL구문으로 작성해주세요)
		XML태그를 이용했다.. <br> 
	</p>
	
	<c:if test="${ 1 lt 2 }">
		<strong>조건식이 참이다!</strong>
	</c:if>

	<c:if test="${ 1 gt 2 }">
		<strong>조건식이 거짓이다!</strong>
	</c:if>
	
	<hr>
	
	<h3>2) choose, when, otherwise</h3>
	
	<p>
	
	</p>
	<!-- point라는 키값으로 Value가 넘어옴  -->
	
	<!-- 
		포인트 사용량이 100 이하라면 일반회원이라고 출력해주고 싶음
		포인트 사용량이 300 이하라면 우수회원이라고 출력해주고 싶음
		요 앞에 두 케이스에 걸리지 않을 시 최우수 회원이라고 출력해주고 싶음
	 -->
		
	<c:choose>
		<c:when test="${ point le 100 }">일반회원 <br></c:when>
		<c:when test="${ point le 300 }">
			<label style="color:gold; font-weight:bold;"> 우수회원 </label>
		</c:when>
		<c:otherwise><mark style="color:black">최우수 회원</mark></c:otherwise>
	</c:choose>	
		
	<br>
	
	<hr>
	
	<h3> 빤복문 - forEach </h3>	
	
	<pre>
		[ 표현법 ]
		
		for loop문
		&lt;c:forEach var="속성명" begin="초기값" end="끝값" step="증가시킬값(생략가능)" &gt;
			반복적으로 실행할 내용
		&lt;c:/forEach&gt;
		
		&lt;c:forEach var="속성명" items="순차적으로 접근할 배열 또는 컬렉션" &gt;
			반복적으로 실행할 내용
		&lt;c:/forEach&gt;
		
	</pre>
	
	<c:forEach var="i" begin="0" end="9">
		${ i }
	</c:forEach>
	
	<br>
	왜 별찍기 제대로 안댐? 뛰발
<%-- 	<c:forEach var="i" begin="1" end="5">
		 <% int i = 1; %>
		<c:forEach var="j" begin="0" end="i">
			*
		</c:forEach>
		<% i++; %>
		<br>
	</c:forEach> --%>
	
	<hr>
	
	<c:forEach var="c" items="${ colors }">
	</c:forEach>
	
	<hr>
	<div class="m-2">
		<h3>회원목록</h3>
		<button type="button" class="btn btn-primary">조회하기</button>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose >
					<c:when test="${ empty requestScope.users }">
						<tr>
							<td colspan ="4"> 조회된 결과가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="user" items="${ users }">
							<tr>
								<td>${ user.userNo }</td>
								<td>${ user.userName }</td>
								<td>${ user.userId }</td>
								<td>${ user.enrollDate }</td>
							</tr>					
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>