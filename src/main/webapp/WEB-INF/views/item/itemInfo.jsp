<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<script src="/resources/js/itemInfo.js"></script>
<!-- ajax 사용시 필수 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 

<link rel="stylesheet" href="/resources/css/common.css" />
<link rel="stylesheet" href="/resources/css/ItemReply.css">
<link rel="stylesheet" href="/resources/css/ItemDetail.css">
<link rel="stylesheet" href="/resources/css/myPage.css" />

<title>${itemInfo.itemName}</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 <script src="https://kit.fontawesome.com/f21f7d3508.js" crossorigin="anonymous"></script>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    function toggleWishlist() {
    	var email = $("#email").val();
    	var itemNum = "<c:out value='${itemNum}' />";
        $.ajax({
            type: 'GET',
            url: '/item/wishlist',
            data: { itemNum: itemNum, email: email },
            success: function(response) {
                console.log(response);

                // 서버 응답에 따라 다른 alert 메시지 표시
                if (response === "ADD") {
                    $("#heartIcon").css("color", "#FF0000");
                } else if (response === "REMOVE") {
                    alert("위시리스트에서 제거되었습니다.");
                    $("#heartIcon").css("color", "#fff");
                } else {
                    alert("알 수 없는 응답입니다.");
                }
            },
            error: function(error) {
                console.error(error);
                // 에러 발생 시 동작을 추가할 수 있습니다.
            }
        });
    }
</script>
<jsp:include page="../header.jsp" />
		<sec:authorize access="isAuthenticated()">
			<input type="hidden" name="email" id="email" value='<sec:authentication property="principal.member.email"/>' />
		</sec:authorize>
	
		<div class="itemDetailWrap">
			<table>
				<tr>
					<td class="thumb-info"><img src="${itemInfo.mainImg}"
						alt="Main Image"></td>
					<td>
						<div class="detil-title">
							<h3>${itemInfo.itemName}</h3>
						</div>
						<div class="detil-sale">${itemInfo.sale}</div>
						<div class="option-tbl">
							<div class="made-where">제조사/제조국</div>
							<div class="made-from">대한민국/자체제작</div>
						</div>
						<div class="itemDetailWrap">
							<div class="left-column">
								<select id="sizeSelect" onchange="showItemDetails">
									<option value="사이즈 선택">사이즈 선택</option>
									<option value="사이즈 M" id="sizeOptionM">사이즈 M</option>
									<option value="사이즈 L" id="sizeOptionL">사이즈 L</option>
									<option value="사이즈 XL" id="sizeOptionXL">사이즈 XL</option>
								</select>

								<div id="itemDetails" style="display: none;">

									<!-- 여기에 아이템의 세부 정보를 표시할 내용을 넣어주세요 -->
								</div>
								<input type="hidden" name="itemNum" value="${itemInfo.itemNum}" />
								<input type="hidden" name="itemRegDate"
									value="${itemInfo.itemRegDate}" /> <input type="hidden"
									name="readCount" value="${itemInfo.readCount}" /> <input
									type="hidden" name="categoryNum"
									value="${itemInfo.categoryNum}" />
							</div>
							<div class="button_set">
								<a class="btn_cart">장바구니 담기</a> <a class="btn_buy">바로구매</a>
						        <sec:authorize access="!isAuthenticated()">
						        <a href="#" class="ws_icon" onclick="gologin()">
						         <span>
						 	       <i id="heartIcon" class="fa-solid fa-heart" style="color:#fff;"></i>
						         </span>
					            </a>
					            </sec:authorize>
								<sec:authorize access="isAuthenticated()">
								<a href="#" class="ws_icon" onclick="toggleWishlist()">
								    <span>
								        <i id="heartIcon" class="fa-solid fa-heart" style="color:#fff;"></i>
								    </span>
								</a>
					            </sec:authorize>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<!-- itemDetailWrap -->



		<!-- 서브 이미지 영역 -->
		<div class="itemIMGWrap">
			<div>
				<img src="${itemInfo.subImg1}" alt="Sub Image 1"><br> <img
					src="${itemInfo.subImg2}" alt="Sub Image 2"><br> <img
					src="${itemInfo.subImg3}" alt="Sub Image 3"><br> <img
					src="${itemInfo.subImg4}" alt="Sub Image 4"><br>
			</div>
		</div>







		<div class="itemReplyWrap">
			<div class="container">
				<h2>Review</h2>
				<p>고객님의 소중한 리뷰를 작성해주세요</p>
				<input type="hidden" name="replyNum" value="${reply.replyNum}" /> <input
					type="hidden" name="itemNum" value="${reply.itemNum}" />
				<table class="table table-hover">

					<thead>
						<tr>
							<th>상품이미지</th>
							<th>제목</th>
							<th>내용</th>
							<th>평점</th>
							<th>리뷰 등록일</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="reply" items="${list}">
							<tr>
								<td class="reply-img"><img src="${reply.img1}" alt="리뷰 이미지" /></td>
								<td>${reply.replyTitle}</td>
								<td>${reply.replyContent}</td>
								<td>${reply.starScore}</td>
								<td>${reply.itemRepRegDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="pageButton">
				<ul>
					<c:if test="${page.prev}">
						<li><a href="item/itemInfo/list?pageNum=${page.startPage -1}">이전</a></li>
					</c:if>
					<c:forEach var="num" begin="${page.startPage}"
						end="${page.endPage}">
						<li><a
							href="/item/itemInfo?pageNum=${num}&itemNum=${itemInfo.itemNum}"
							class="${page.pageNum eq num ? 'active' : '' }">${num}</a></li>
					</c:forEach>
					<c:if test="${page.next}">
						<li><a href="/item/itemInfo?pageNum=${page.endPage + 1}">다음</a></li>
					</c:if>
				</ul>
			</div>

			<!-- 리뷰 추가 폼 -->
			<form action="/item/insertReply" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="itemNum" value="${itemInfo.itemNum}" />
				<input type="hidden" name="pageNum" value="${page.pageNum}" /> <input
					type="text" name="replyTitle" placeholder="리뷰 제목" /><br />
				<textarea name="replyContent" placeholder="리뷰 내용"></textarea>
				<br /> <input type="number" name="starScore" placeholder="별점(1~5)"
					min="1" max="5" /><br /> <input type="file" name="img" />이미지 파일만
				등록 가능합니다.<br />
				<!-- 필요한 입력 필드 추가 -->

				<input type="submit" value="리뷰 작성" />
			</form>

			<a name="review"></a>
			<div class="talbe-slide reivew-list"></div>


		</div>
		<!-- itemReplyWrap -->



		<div class="itemQnAWrap">

			<div class="roadQnABtn">
				<button type="button" onclick="redirectToInsertQnA()">상품 문의</button>
			</div>

		</div>
		<!-- itemInfoWrap -->

		<!-- Flash 메시지를 출력할 부분 -->
		<c:if
			test="${not empty requestScope['org.springframework.web.servlet.support.FlashMap']['message']}">
			<div class="flash-message">
				${requestScope['org.springframework.web.servlet.support.FlashMap']['message']}
			</div>
		</c:if>
	</div>
</body>

<footer>
	<jsp:include page="../footer.jsp" />
</footer>
</html>