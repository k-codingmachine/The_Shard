<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The shard</title>
<link rel="stylesheet" href="/resources/css/common.css" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var result = "${result}";
		if(result == "logout"){
			alert("성공적으로 로그아웃되었습니다.");
		}
	</script>

	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>