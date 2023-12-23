<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>상품검색</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/css/itemlist.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>상품 목록</title>
<script type="text/javascript">
	$(function(){
		var color = "${color}";
		
		$('#color option').each(function(){
			if($(this).val() == color){
				$(this).prop("selected", true);
			}
		});
	});
</script>
</head>
<body>

	<jsp:include page="../header.jsp" />

	<!-- 검색조건 처리 -->
	<div id="search" class="row">
		<div class="col-lg-12">
			<form id="searchForm" action="/itemSearch/list" method="get">
				<select name="color" id="color">
					<option value="">--</option>
					<option value="블랙">블랙</option>
					<option value="브라운">브라운</option>
					<option value="그린">그린</option>
					<option value="그레이">그레이</option>
					<option value="그레이프">그레이프</option>
					<option value="라임">라임</option>
					<option value="레드">레드</option>
					<option value="레몬">레몬</option>
					<option value="망고">망고</option>
					<option value="민트">민트</option>
					<option value="베이지">베이지</option>
					<option value="블루">블루</option>
					<option value="아보카도">아보카도</option>
					<option value="아이보리">아이보리</option>
					<option value="오렌지">오렌지</option>
					<option value="오렌지">오렌지</option>
					<option value="오트밀">오트밀</option>
					<option value="옐로우">옐로우</option>
					<option value="올리브">올리브</option>
					<option value="와인">와인</option>
					<option value="크림">크림</option>
					<option value="코코아">코코아</option>
					<option value="코랄">코랄</option>
					<option value="카멜">카멜</option>
					<option value="카키">카키</option>
					<option value="크림">크림</option>
					<option value="초코">초코</option>
					<option value="코코아">코코아</option>
					<option value="핑크">핑크</option>
					<option value="화이트">화이트</option>
				</select>
				<div id="searchbar">
					<input type="text" class="inputbox" name="itemName"
						placeholder="검색어 입력" value="${itemName}" /> <img
						src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
						class="img"> <input type="hidden" name="categoryNum"
						value="${categoryNum}" />
				</div>
				<button type="submit" class="btn btn-outline-primary">검색</button>
			</form>
		</div>


		<!-- 검색조건 처리 -->
		<div class="container" id="sort">
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link"
					href="/itemSearch/list?color=${color}&itemName=${itemName}&sortType=HPrice">높은
						가격순</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/itemSearch/list?color=${color}&itemName=${itemName}&sortType=RPrice">낮은
						가격순</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/itemSearch/list?color=${color}&itemName=${itemName}&sortType=Latest">최신순</a>
				</li>

			</ul>
		</div>
	</div>


	<div id="itemlist" class="prdlist_rollup">
		<c:choose>
			<c:when test="${empty list}">
				<!-- 검색 결과가 없는 경우 -->
				<script>
					alert("검색 결과가 없습니다.");
					window.history.back();
				</script>
			</c:when>
			<c:otherwise>
				<c:forEach var="item" items="${list}">
					<li class="item">
						<div class="box">
							<div class="prdImg">
								<a href="/item/itemInfo?itemNum=${item.itemNum}&pageNum=1"> <img src="<c:url value='${item.mainImg}'/>"
									class="Main Img">
								</a>
							</div>
							<div class="prd_info_box" id="prd_info_boxs"
								onclick="location.href='';">
								<div class="name">
									<a href=""> <font><b>${item.itemName}</b></font>
									</a>
								</div>
								<div class="list_info">
									<ul>
										<li class="cash text-center"><class ="price" id="sale">${item.sale}원</li>
									</ul>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
	<!-- 페이징처리 -->
	<div class="center">
		<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev}">
				<li class="page-item"><a class="page-link"
					href="/itemSearch/list?pageNum=${pageMaker.startPage - 1}&color=${color}&itemName=${itemName}&sortType=${sortType}">&laquo;</a></li>
			</c:if>

			<c:forEach var="num" begin="${pageMaker.startPage}"
				end="${pageMaker.endPage}" varStatus="loop">
				<li class="page-item ${pageMaker.pageNum == num ? 'active' : ''}">
					<a class="page-link"
					href="/itemSearch/list?pageNum=${num}&color=${color}&itemName=${itemName}&sortType=${sortType}">${num}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li class="page-item"><a class="page-link"
					href="/itemSearch/list?pageNum=${pageMaker.endPage + 1}&color=${color}&itemName=${itemName}&sortType=${sortType}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
	<!-- /페이징처리 -->

	<jsp:include page="../footer.jsp" />

</body>


</html>
