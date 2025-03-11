<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요게 이제 굉장히 중요함</title>

<style>
	ul,li {list-decoration : none;}

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

</body>

</html>