<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요게 이제 굉장히 중요함</title>

<style>
	ul,li{
		list-decoration : none;
	}

</style>

</head>

<body>

	<br>
	${ brand }
	<br>
	<h1>베스트 셀러!</h1> 
	${ bestSeller }
	
	<ul>
		<li> 브랜드 : ${ bestSeller.brand } </li>
		<li> 상품명 : ${ bestSeller.name } </li>
		<li> 가격	 : ${ bestSeller.price }원 </li>
	</ul>

	두 개 이상의 Scope 에 같은 키값으로 값을 담은 경우
	
	<!--  
		page => request => session => application 순으로 키값을 검색
	-->
	
	Scope에 직접 접근하는 방법 <br>
	
	requestScope : ${ requestScope.brand } <br>
	sessionScope : ${ sessionScope.brand } <br>
	
	만약에 없는 키값 el구문을 출력하려고 하면 어떻게 될까 ? <br>
	
	없는 값 : ${ sessionScope.in } <br>
	
	
	<hr>
	
	연산은 어디서 하는게 제일 좋은가? <br>
	1. SQL문 DB단
	2. Java => Service 단
	3. VIEW 
	
	<hr>
	<h3>1. 산술연산</h3>
	
	big + small = ${ big + small }<br>
	big - small = ${ big - small }<br>
	big x small = ${ big * small }<br>
	big / small = ${ big div small } <br>
	big % small = ${ big mod small }<br>
	
	<hr>
	<h3>2. 논리연산 </h3>
	AND : ${true && true } OR ${ true and true } <br>
	OR : ${true || true } OR ${true or true } <br>
	
	<hr>
	<h3>3. 비교연산 </h3>

	<h4>대소비교</h4> 
	big이 small보다 작니? : ${ big < small } 또는 ${ big lt small } <br>
	big이 small보다 크니? : ${ big gt small } <br>
	big이 small보다 작거나 같니? : ${ big le small } <br>
	big이 small보다 크거나 같니? : ${ big ge small } <br>
	
	<h4> 동등비교 </h4> 
	
	big이랑 small이랑 같니? : ${ big == samll } 또는 ${ big eq small }<br>
	big이 10이랑 같나? : ${ big eq 10 } <br>
	str과 yap going가 일치합니까? : ${ str == "yap going" } 또는  ${ str eq "yap going" } <br>
	big이 10과 일치하지 않냐? : ${ big ne 10 }			
	
	
	<hr>
	
	<h3>비어있는지 체크</h3>
	
	<p>
		bestSeller가 null과 일치합니까? <br>
		${ bestSeller == null } or ${ bestSeller eq null } or ${ empty bestSeller } <br>
		
		list가 비어잇지 않나요? <br>
		${ !empty list }
	
	</p>
	
</body>

</html>