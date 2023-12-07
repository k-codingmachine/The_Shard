<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="/resources/css/common.css" />
</head>
<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	
	
	<h2>관리자 페이지</h2>
	
	<ul>
		<li><a href="/admin/user">회원관리</a></li>
		<li><a href="/admin/item">제품 관리</a></li>
		<li><a href="/admin/noReply">리뷰 & 문의 관리</a></li>
	</ul>	
	
	<jsp:include page="../footer.jsp" />
</body>
</html>