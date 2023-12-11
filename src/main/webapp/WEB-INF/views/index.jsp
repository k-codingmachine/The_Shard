<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The shard</title>
<link rel="stylesheet" href="/resources/css/common.css" />
<link rel="stylesheet" href="/resources/css/home.css" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var result = "${result}";
		if (result == "logout") {
			alert("성공적으로 로그아웃되었습니다.");
		}
	</script>


	<jsp:include page="header.jsp"></jsp:include>
				<div class="title">
				<h2>category</h2>
				</div>
  <div class="categoryList">
	<ul class="cate">
		<li><a href="/itemSearch/list/category/1"> <img
		        src="https://image.msscdn.net/images/goods_img/20210812/2060994/2060994_16928433530278_500.jpg"
		        alt="후드">후드
		</a></li>

		<li><a href="/itemSearch/list/category/2"> <img
				src="https://image.msscdn.net/images/goods_img/20210910/2122596/2122596_2_500.jpg"
				alt="후드집업">후드집업
		</a></li>
		<li><a href="/itemSearch/list/category/3"> <img
				src="https://image.msscdn.net/images/goods_img/20200205/1291017/1291017_1_500.jpg"
				alt="집업">집업
		</a></li>
		<li><a href="/itemSearch/list/category/4"> <img
				src="https://image.msscdn.net/images/goods_img/20230803/3444190/3444190_16951073068985_500.jpg"
				alt="셔츠">셔츠
		</a></li>
		<li><a
			href="/itemSearch/list/category/5"> 
				<img					
				src="https://image.msscdn.net/images/goods_img/20160322/324416/324416_16824008874265_500.jpg"
				alt="무지">무지
		</a></li>
		<li><a
			href="/itemSearch/list/category/6"> 
				<img
				src="https://image.msscdn.net/images/goods_img/20230710/3400300/3400300_16964807034853_500.jpg"
				alt="데님펜츠">데님펜츠
		</a></li>
		<li><a
			href="/itemSearch/list/category/7"> 
				<img
				src="https://image.msscdn.net/images/goods_img/20210121/1758197/1758197_16746129975024_500.jpg"
				alt="자켓">자켓
		</a></li>
		<li><a
			href="/itemSearch/list/category/8"> 
				<img
				src="https://image.msscdn.net/images/goods_img/20231006/3607360/3607360_16975953449038_500.jpg"
				alt="코튼펜츠">코튼펜츠
		</a></li>
		<li><a
			href="/itemSearch/list/category/9"> 
			<img
				src="https://image.msscdn.net/images/goods_img/20221108/2928316/2928316_1_500.jpg"
				alt="ACC">ACC
		</a></li>
		<li><a
			href="/itemSearch/list/category/10"> 
			 <img
				src="https://image.msscdn.net/images/goods_img/20220727/2684358/2684358_16945862914546_500.jpg"
				alt="니트">니트
		</a></li>
		<li><a
			href="/itemSearch/list/category/11"> 
				<img src="https://image.msscdn.net/images/goods_img/20230801/3437355/3437355_16925831814491_500.jpg"
				alt="맨투맨">맨투맨
		</a></li>
		<li><a
			href="/itemSearch/list/category/12"> 
			<img
				src="https://image.msscdn.net/images/goods_img/20231018/3637264/3637264_17013272004950_500.jpg"
				alt="티셔츠">티셔츠
		</a></li>

	</ul>
 </div>
 <div class="title">
				<h2>New</h2>
				</div>
 <div id="itemlist" class="prdlist_rollup">
				<c:forEach var="item" items="${list}">
					<li class="item">
						<div class="box">
							<div class="prdImg">
								<a href=""> <img src="<c:url value='${item.mainImg}'/>"
									class="Main Img">
								</a>
							</div>
							<div class="prd_info_box" id="prd_info_boxs"
								onclick="location.href='';">
								<div class="name">
									<a href=""> <font color="007cd8"><b>${item.itemName}</b></font>
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
	</div>
 
 



	

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>