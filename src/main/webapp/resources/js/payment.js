var orderName = "";
var orderPhone = "";
var postcode;
var roadAddress = "";
var detailAddr = "";
var email = "";
var cartNum = "";
var pointInput = "";
var token = "";
var itemNumArray = [];

$(function () {
	itemNumArray = $('input[name=itemNum]').map(function () {
		// input의 값을 가져와서 숫자로 변환하여 배열에 추가
		return parseInt($(this).val(), 10);
	}).get();

	orderName = $(".userName").val();
	orderPhone = $(".phone").val();
	postcode = parseInt($(".postcode").val());
	roadAddress = $(".roadAddress").val();
	detailAddr = $(".detailAddr").val();
	email = $("#email").val();
	cartNum = $('#cartNum').val();
	pointInput = $('#pointInput').val();
	token = $('#token').val();

	// 배열 확인
	console.log(itemNumArray);
	console.log(orderName);
	console.log(orderPhone);
	console.log(postcode);
	console.log(roadAddress);
	console.log(detailAddr);
	console.log(email);
	console.log(cartNum);
	console.log(token);

})

function statusCheckboxChange() {
	var checkbox = document.getElementById("shipto");
	if (checkbox.checked) {
		// checkbox가 선택된 경우 실행할 코드
		orderName = $(".orderName").val();
		orderPhone = $(".orderPhone").val();
		postcode = $("#postcode").val();
		roadAddress = $("#roadAddress").val();
		detailAddr = $("#detailAddr").val();
	}
}

function requestPay() {
	IMP.init('imp21466554');

	//다른주소 입력 클릭확인/주문정보변경
	statusCheckboxChange();

	IMP.request_pay({
		pg: 'inicis', // version 1.1.0부터 지원.
		pay_method: 'card',
		merchant_uid: 'merchant_' + new Date().getTime(),
		name: '주문명:상품결제',
		amount: 100, //판매 가격
		buyer_email: email,
		buyer_name: orderName,
		buyer_tel: orderPhone,
		buyer_addr: roadAddress,
		buyer_postcode: postcode
	}, function (rsp) {
		console.log(rsp);
		// 결제검증
		$.ajax({
			type: "POST",
			headers: {
				"X-CSRF-TOKEN": token
			},
			url: "/verifyIamport/" + rsp.imp_uid
		}).done(function (data) {


			// 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
			if (rsp.paid_amount == data.response.amount) {
				var msg = '결제 및 결제검증완료 - 결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;

				var itemNum = JSON.stringify(itemNumArray);

				$.ajax({
					type: 'POST',
					url: '/order/payComplete',
					headers: {
						"X-CSRF-TOKEN": token
					},
					dataType: 'application/json',
					data: {
						itemNum: itemNum,
						postcode: postcode,
						roadAddress: roadAddress,
						detailAddr: detailAddr,
						email: email,
						cartNum: cartNum,
						usePoint: pointInput
					},
					success: function () {
						console.log("전송 완료");
					},
					error: function () {
						alert("전송 실패");
					}
				});
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				alert(msg);
			}
		});
	});
}

