<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common.css" />
<link rel="stylesheet" href="/resources/css/myPage.css" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<script src="https://kit.fontawesome.com/f21f7d3508.js" crossorigin="anonymous"></script>
</head>
<body>
	<script type="text/javascript">
		var result = "${result}";
		if (result == "logout") {
			alert("성공적으로 로그아웃되었습니다.");
		}
	</script>

	<jsp:include page="../header.jsp" />

	<div class="user_info">
		<div class="info_box">
			<div class="info_left">
				<ul>
					<li style="font-size: 24px;">My Page</li>
					<li class="list_name"><span>${user.userName}</span> <span>
							<c:choose>
								<c:when test="${user.memNum eq 0}">
            Loby 회원
        </c:when>
								<c:when test="${user.memNum eq 1}">
            Lounge 회원
        </c:when>
								<c:when test="${user.memNum eq 2}">
            Shard 회원
        </c:when>
							</c:choose>
					</span></li>
					<li><a href="/user/membership">등급 혜택보기</a> <span class="bar"></span>
						<a href="/user/userGet">회원정보수정</a></li>
				</ul>
			</div>
			<div class="info_right">
				<div>
					<a href="#" class="ws_icon">
						<span>
							<i class="fa-solid fa-heart" style="color:#fff;"></i>
						</span>
					</a>
				</div>
				<ul class="info_icon">
					<li>
						<a href="">
							<span class="point imgadd"></span>
							<p>적립금 ></p>
							<span>${user.point}</span>
						</a>
					</li>
					<li>
						<a href="">
							<span class="coupon imgadd"></span>
							<p>쿠폰 ></p>
							<span>쿠폰</span>
						</a>
					</li>
					<li>
						<a href="">
							<span class="cart imgadd"></span>
							<p>장바구니 ></p>
							<span>장바구니 갯수</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		
	</div>
	<div class="content">
		<div class="contentWrap">
			<div class="menu">
				<a href="">마이페이지</a>
				<a href="">적립금내역</a>
				<a href="">쿠폰내역</a>
				<a href="">관심상품</a>
				<a href="">내 게시글</a>
				<a href="">배송지 주소록 관리</a>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp" />

</body>
</html>