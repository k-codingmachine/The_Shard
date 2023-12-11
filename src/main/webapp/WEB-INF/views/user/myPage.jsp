<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common.css" />
<link rel="stylesheet" href="/resources/css/myPage.css" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<script src="/resources/js/myPage.js"></script>
<script src="https://kit.fontawesome.com/f21f7d3508.js"
	crossorigin="anonymous"></script>
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
						<a href="/shard/userCheck">회원정보수정</a></li>
				</ul>
			</div>
			<div class="info_right">
				<div>
					<a href="#" class="ws_icon"> <span> <i
							class="fa-solid fa-heart" style="color: #fff;"></i>
					</span>
					</a>
				</div>
				<ul class="info_icon">
					<li><a href=""> <span class="point imgadd"></span>
							<p>적립금 ></p> <span>${user.point}</span>
					</a></li>
					<li><a href=""> <span class="coupon imgadd"></span>
							<p>쿠폰 ></p> <span>쿠폰</span>
					</a></li>
					<li><a href=""> <span class="cart imgadd"></span>
							<p>장바구니 ></p> <span>장바구니 갯수</span>
					</a></li>
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
				<a href="">주문내역</a>
				<a href="">내 게시글</a> 
				<a href="">배송지 주소록 관리</a>
			</div>

			<!-- 마이페이지 -->
			<div class="content_box">
				<div class="content_box_body">
					<div class="wish_box">
						<h3>관심 상품</h3>
					</div>
					
					<ul>
						<%-- <c:forEach>
							
						</c:forEach> --%>
					</ul>
				</div>
			</div>
			
			<!-- 적립금내역 -->
			<div class="content_box">
				<div class="content_box_body">
					<div>
						<h3 class="orderTitle">적립금 내역</h3>
						<p>보유 적립금과 상관없이 바로 사용하실 수 있습니다.</p>
					</div>
					
					<div class="possessionPoint">
						<p>사용가능 적립금<span class="pointPrice">0</span><span class="pointTxt">원</span></p>
					</div>
					
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="300">
								<col width="*">
								<col width="300">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">적립일</th>
									<th scope="col">내용</th>
									<th scope="col">적립금액</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="list" items="${list}">
									<tr style="position: relative">
										<td>${list.replyNum}</td>
										<td><i class="fa-solid fa-lock"></i></td>
										<td style="font-size: 13px;">${list.replyCategory}</td>
										<td style="padding-left: 30px; text-align: left;"><a
											href="${list.replyNum}" class="getReply">${list.replyTitle}</a></td>
										<td>${hiddenEmail}</td>
										<td><fmt:formatDate value="${list.replyRegDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!-- 쿠폰 내역 -->
			<div class="content_box">
				<div class="content_box_body">
					<div>
						<h3 class="orderTitle">쿠폰 내역</h3>
					</div>
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="250">
								<col width="*">
								<col width="200">
								<col width="250">
							</colgroup>

							<thead>
								<tr>
									<th scope="col">주문일</th>
									<th scope="col" style="text-align: left">상품정보</th>
									<th scope="col">결제금액</th>
									<th scope="col">주문상세</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="list" items="${list}">
									<tr style="position: relative">
										<td>${list.replyNum}</td>
										<td><i class="fa-solid fa-lock"></i></td>
										<td style="font-size: 13px;">${list.replyCategory}</td>
										<td style="padding-left: 30px; text-align: left;"><a
											href="${list.replyNum}" class="getReply">${list.replyTitle}</a></td>
										<td>${hiddenEmail}</td>
										<td><fmt:formatDate value="${list.replyRegDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!-- 주문내역 -->
			<div class="content_box">
				<div class="content_box_body">
					<div>
						<h3 class="orderTitle">최근 주문 정보</h3>
					</div>
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="70">
								<col width="120">
								<col width="*">
								<col width="120">
								<col width="110">
								<col width="110">
							</colgroup>

							<thead>
								<tr>
									<th scope="row">번호</th>
									<th scope="row">주문일자</th>
									<th scope="row" style="text-align:left">상품명</th>
									<th scope="row">결제금액</th>
									<th scope="row">주문상세</th>
									<th scope="row">배송현황</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="list" items="${list}">
									<tr style="position: relative">
										<td>${list.replyNum}</td>
										<td><i class="fa-solid fa-lock"></i></td>
										<td style="font-size: 13px;">${list.replyCategory}</td>
										<td style="padding-left: 30px; text-align: left;"><a
											href="${list.replyNum}" class="getReply">${list.replyTitle}</a></td>
										<td>${hiddenEmail}</td>
										<td><fmt:formatDate value="${list.replyRegDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!-- 내 게시글 -->
			<div class="content_box">
				<div class="content_box_body">
					<div>
						<h3 class="orderTitle">내 게시글</h3>
					</div>
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="250">
								<col width="*">
								<col width="200">
								<col width="250">
							</colgroup>

							<thead>
								<tr>
									<th scope="col">주문일</th>
									<th scope="col" style="text-align: left">상품정보</th>
									<th scope="col">결제금액</th>
									<th scope="col">주문상세</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="list" items="${list}">
									<tr style="position: relative">
										<td>${list.replyNum}</td>
										<td><i class="fa-solid fa-lock"></i></td>
										<td style="font-size: 13px;">${list.replyCategory}</td>
										<td style="padding-left: 30px; text-align: left;"><a
											href="${list.replyNum}" class="getReply">${list.replyTitle}</a></td>
										<td>${hiddenEmail}</td>
										<td><fmt:formatDate value="${list.replyRegDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!-- 배송지 주소록 관리 -->
			<div class="content_box">
				<div class="content_box_body">
					<div>
						<h3 class="orderTitle">배송지 주소록 관리</h3>
					</div>
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="250">
								<col width="*">
								<col width="200">
								<col width="250">
							</colgroup>

							<thead>
								<tr>
									<th scope="col">주문일</th>
									<th scope="col" style="text-align: left">상품정보</th>
									<th scope="col">결제금액</th>
									<th scope="col">주문상세</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="list" items="${list}">
									<tr style="position: relative">
										<td>${list.replyNum}</td>
										<td><i class="fa-solid fa-lock"></i></td>
										<td style="font-size: 13px;">${list.replyCategory}</td>
										<td style="padding-left: 30px; text-align: left;"><a
											href="${list.replyNum}" class="getReply">${list.replyTitle}</a></td>
										<td>${hiddenEmail}</td>
										<td><fmt:formatDate value="${list.replyRegDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<div class="del_member">
				<p>탈퇴를 원하시면 회원탈퇴 버튼을 눌러주세요.</p>
				<a href="">회원탈퇴</a>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp" />

</body>
</html>