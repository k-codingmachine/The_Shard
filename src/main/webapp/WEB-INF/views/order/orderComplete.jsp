<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>check out</title>
<link href="/resources/css/common.css" rel="stylesheet">
<link href="/resources/css/order.css" rel="stylesheet">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/resources/js/cart.js"></script>
<script type="text/javascript" src="/resources/js/address.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/e73d217c71.js" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- Page Header Start -->

	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="min-height: 300px">
		<h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
		<div class="d-inline-flex">
			<p>01 장바구니 ></p>
			<p>&nbsp;02 주문결제 ></p>
			<p><a href="">&nbsp;03 주문완료</a></p>
		</div>
	</div>

	<div class="container-fluid pt-5">
		<div class="row px-xl-5">
			<div class="col-lg-8">
				<div class="mb-4">
					<h4 class="font-weight-semi-bold mb-4">주문이 성공적으로 완료되었습니다</h4>
				</div>
				<div class="col-lg-8">
					<button class="btn btn-block btn-dark my-3 py-3"
								onclick="selectOrder()">주문하기</button>
					<button class="btn btn-block btn-dark my-3 py-3"
							onclick="selectOrder()">주문하기</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>