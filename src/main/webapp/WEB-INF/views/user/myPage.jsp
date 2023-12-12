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
					<li><a href="#"> <span class="point imgadd"></span>
							<p>적립금 ></p> <span>${user.point}</span>
					</a></li>
					<li><a href="#"> <span class="coupon imgadd"></span>
							<p>쿠폰 ></p> <span>${couponCount}</span>
					</a></li>
					<li><a href=""> <span class="cart imgadd"></span>
							<p>장바구니 ></p> <span>${cartItemCount}</span>
					</a></li>
				</ul>
			</div>
		</div>

	</div>
	<div class="content">
		<div class="contentWrap">
			<div class="menu">
				<a href="#">마이페이지</a> 
				<a href="#">적립금내역</a> 
				<a href="#">쿠폰내역</a> 
				<a href="#">주문내역</a> 
				<a href="#">내 게시글</a> 
				<a href="#">배송지 주소록 관리</a>
			</div>

			<!-- 마이페이지 -->
			<div class="content_box" id="myPageWishlist">
				<div class="content_box_body">
					<div class="wish_box">
						<h3>관심 상품</h3>
					</div>

					<ul class="wishlist">
						<c:forEach var="item" items="${itemList}">
							<li>
								<div>
									<a href="/item/itemInfo?itemNum=${item.itemNum}&pageNum=1">
									<img src="${item.mainImg}" alt="상품 이미지"></a>
								</div>
								<p class="itemName">${item.itemName}</p>
								<p class="itemSale">
									<fmt:formatNumber pattern="#,###원" value="${item.sale}" />
								</p>
							</li>
						</c:forEach>
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
						<p>
							사용가능 적립금<span class="pointPrice">0</span><span class="pointTxt">원</span>
						</p>
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
						<p
							style="color: #000; padding: 8px 0; font-size: 17.5px; font-weight: bold">${user.userName}님이
							보유하고 계신 쿠폰은 총 ${couponCount}장 입니다.</p>
					</div>
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="150">
								<col width="*">
								<col width="370">
								<col width="250">
							</colgroup>

							<thead>
								<tr>
									<th scope="row" style="background-color: #f6f6f6;">쿠폰유형</th>
									<th scope="row" style="background-color: #f6f6f6;">쿠폰이름</th>
									<th scope="row" style="background-color: #f6f6f6;">쿠폰안내</th>
									<th scope="row" style="background-color: #f6f6f6;">유효기간</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="coupon" items="${coupon}">

									<tr style="position: relative">
										<td>상품쿠폰</td>
										<td>가입축하쿠폰</td>
										<c:forEach var="couponVO" items="${couponVO}">
											<c:if test="${coupon.couponNum == couponVO.couponNum}">
												<td>${couponVO.discountRate}%쿠폰</td>
											</c:if>
										</c:forEach>
										<td>~ <fmt:formatDate pattern="yyyy-MM-dd"
												value="${coupon.issueED}" />까지
										</td>
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
									<th scope="row" style="text-align: left">상품명</th>
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
						<p class="myPageQnA">
							전체 게시판 <span>총 게시글 : ${qnaCount}건</span>
						</p>
					</div>
					<div class="orderTable">
						<table>
							<colgroup>
								<col width="100">
								<col width="200">
								<col width="*">
								<col width="120">
							</colgroup>

							<thead>
								<tr>
									<th scope="row">번호</th>
									<th scope="row">게시판</th>
									<th scope="row" style="text-align: left">제목</th>
									<th scope="row">날짜</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="qna" items="${qna}">
									<tr style="position: relative">
										<td>${qna.replyNum}</td>
										<td style="font-size: 13px;">Q&A</td>
										<c:choose>
											<c:when test="${empty qna.replyTitle}">
												<td  style="padding-left: 30px; text-align: left;"><a
													href="/qna/get?replyNum=${qna.inquiryNum}" class="getReply">재문의</a></td>
											</c:when>
											<c:otherwise>
												<td style="padding-left: 30px; text-align: left;"><a
													href="/qna/get?replyNum=${qna.replyNum}" class="getReply">${qna.replyTitle}</a></td>
											</c:otherwise>
										</c:choose>
										<td><fmt:formatDate value="${qna.replyRegDate}"
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
								<col width="250">
							</colgroup>

							<thead>
								<tr>
									<th scope="row">배송지 이름</th>
									<th scope="row">주소</th>
									<th scope="row">기본 배송지여부</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="address" items="${address}">
									<tr style="position: relative">
										<td>${address.addrName}</td>
										<td>${address.roadAddress}  ${address.detailAddr}</td>
										<c:choose>
											<c:when test="${address.defaultWhether == 1}">
												<td>기본배송지</td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>
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