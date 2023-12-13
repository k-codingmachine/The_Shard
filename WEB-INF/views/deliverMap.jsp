<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tmap Example</title>
<!-- Tmap API 스크립트 추가 -->
<script
	src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=SRhj0RpxOR37VRnfRFGGl9tRMPfKn40d546q5pUO"></script>
	
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="/resources/js/deliverMap.js"></script>
</head>
<body>
	<input type="hidden" id="customerAddr" value="경기도 수원시 영통구 인계로 102"/>

	<div id="map_div"></div>
	
	<div class="map_act_btn_wrap clear_box"></div>
	<p id="result"></p>
	<br />
</body>
</html>