$(function () {
    // 전체 선택을 위한 마스터 체크박스
    const masterCheckbox = document.getElementById('selectAllCheckbox');

    // 각 행의 체크박스들
    const itemCheckboxes = document.querySelectorAll('.itemCheckbox');

    // 페이지 로드 시 마스터 체크박스와 모든 하위 체크박스 선택
    masterCheckbox.checked = true;
    itemCheckboxes.forEach(function (checkbox) {
        checkbox.checked = true;
    });

    // 전체 선택을 위한 마스터 체크박스 상태 변경 시
    masterCheckbox.addEventListener('change', function () {
        // 전체 체크박스들의 상태를 마스터 체크박스에 맞게 설정
        itemCheckboxes.forEach(function (checkbox) {
            checkbox.checked = masterCheckbox.checked;
        });
    });

    // 각 행의 체크박스 상태 변경 시
    itemCheckboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            // 모든 체크박스가 선택되었는지 확인
            const allChecked = Array.from(itemCheckboxes).every(function (checkbox) {
                return checkbox.checked;
            });
            // 마스터 체크박스의 상태를 변경
            masterCheckbox.checked = allChecked;
        });
    });

    $('.plus').click(function(e){
    	e.preventDefault();
        var plusrealSale = parseInt($(this).parent().siblings(".itemRealSale").val());
        var realPoint = parseInt($(this).parent().siblings(".itemRealPoint").val());
        var itemCount = $(this).parent().siblings(".itemCount").val();
        itemCount++;
        $(this).parent().siblings(".itemCount").val(itemCount);
        var sale = parseInt($(this).parents().siblings(".itemSale").text());
        var point = parseInt($(this).parents().siblings(".reward").text());
        sale = sale + plusrealSale;
        point = point + realPoint;
        $(this).parents().siblings(".itemSale").text(sale);
        $(this).parents().siblings(".reward").text(point);
        $(this).parents().siblings(".totalPriceList").val(sale);
        $(this).parents().siblings(".itemRewardPoints").val(point);
        var itemNum = parseInt($(this).parent().siblings(".itemNum").val());
        $.ajax({
            type: "POST",
            url: "/order/updateExpectedPlusAmount",
            data: {
                itemNum: itemNum
            },
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                // 요청이 실패한 경우의 동작
                console.log(error);
            }
        });
       
    });

    $('.minus').click(function(e){
    	e.preventDefault();
        var minusrealSale = parseInt($(this).parent().siblings(".itemRealSale").val());
        var realPoint = parseInt($(this).parent().siblings(".itemRealPoint").val());
        var itemCount = $(this).parent().siblings(".itemCount").val();
        itemCount--;
        $(this).parent().siblings(".itemCount").val(itemCount);
        var sale = parseInt($(this).parents().siblings(".itemSale").text());
        realSale = parseInt($(this).parents().siblings(".itemSale").text());
        sale = sale - minusrealSale;
        point = point - realPoint;
       $(this).parents().siblings(".itemSale").text(sale);
        $(this).parents().siblings(".reward").text(point);
        $(this).parents().siblings(".totalPriceList").val(sale);
        $(this).parents().siblings(".itemRewardPoints").val(point);
        var itemNum = parseInt($(this).parent().siblings(".itemNum").val());
        $.ajax({
            type: "POST",
            url: "/order/updateExpectedMinusAmount",
            data: {
                itemNum: itemNum
            },
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                // 요청이 실패한 경우의 동작
                console.log(error);
            }
        });
    });

    $(".usePoint").click(function() {
        // 입력된 포인트 가져오기
        var point = parseInt($("#pointInput").val());
        console.log(point);
        var customerPoint = $(".customerPoint").text();
        console.log(customerPoint);
        // 포인트가 5000 이상이고 적립되어있는 금액보다 적어야함
        $(".pointAlert").css('color', 'black');
        if (point >= 5000 && point <= customerPoint) {
            //100원 단위로 내림
            point = Math.floor(point / 100) * 100;
            console.log("----포인트---");
            $(".pointAlert").remove();
            $(".pointAlert + div").remove();
            handleDiscountedAmount(point);
        } else if(point < 5000) {
            // 5000 미만의 포인트는 처리하지 않음 또는 에러 메시지 등을 표시
            $(".pointAlert").css('color', 'red');
            console.log("포인트는 5000 이상이어야 합니다.");
        } else if(point > customerPoint){
            $(".pointAlert").after('<div>❗ 가용포인트보다 높은 금액을 입력했습니다. </div>').next().css('color', 'red');
            $(".pointAlert").css('color', 'black');
            console.log("가용포인트보다 높은 금액을 입력했습니다.");
        }
    });
});


// 전체상품삭제 버튼 클릭 시
$("#allDeleteButton").click(function () {
    // cartNum 값을 가져오기
    var cartNum = $("#cartNum").val();
    var email = $("#email").val();
    console.log(cartNum);
    // AJAX 호출
    $.ajax({
        type: "POST",
        url: "/order/allDetailCartDelete",
        data: {
            cartNum: cartNum,
        },
        success: function () {
            console.log(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 에러 처리
            console.error("AJAX 요청 중 에러 발생:", textStatus, errorThrown);
            alert("에러가 발생했습니다.");
        }
    });
});

//선택한 항목 삭제를 처리하는 함수  
function deleteSelectedItems() {
    var selectedItems = [];
    // 모든 체크박스를 순회
    $('.itemCheckbox:checked').each(function () {
       var itemIndex = parseInt($(this).siblings(".itemNum").val());
        console.log(itemIndex);
        // 체크박스 ID에서 항목 번호를 추출
        selectedItems.push(itemIndex);
    });
    var cartNum =parseInt($("#cartNum").val());
    selectedItems.push(cartNum);
    console.log(selectedItems);
   
    // 선택한 항목이 있는지 확인
    if (selectedItems.length > 0) {
        
        $.ajax({
            type: 'POST',
            url: '/order/chooseDetailCartDelete',
            contentType: "application/json",
            traditional: true,
            data: JSON.stringify(selectedItems),
            success: function(response) {
                $(".itemCheckbox:checked").each(function() {
                    // 체크되어 있는 경는
                    var $currentTr = $(this).closest("tr");
                    
                    // 현재 <tr> 및 그 다음 형제 <tr>을 모두 제거
                    $currentTr.remove();
                    
                  });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // 에러 처리
                console.error("AJAX 요청 중 에러 발생:", textStatus, errorThrown);
                alert("에러가 발생했습니다.");
            }
        });
    } else {
        alert('삭제할 항목을 선택하세요.');
    }
}


//선택항목 주문
function selectOrder() {
var selectedItems = [];
    $(".itemCheckbox:not(:checked)").each(function() {
        // 체크되어 있는 경는
        var $currentTr = $(this).closest("tr");
     
        // 현재 <tr> 및 그 다음 형제 <tr>을 모두 제거
        $currentTr.remove();
        
      });
      $('#checkoutForm').submit();
};

//쿠폰,적립금 선택
function handleDiscountedAmount(point) {
    // 선택된 옵션 요소 가져오기
    var selectedOption = $("#selectbox option:selected");

    // 선택된 쿠폰의 데이터 추출
    var discount = selectedOption.data("coupon-discount");
    var totalPrice = $("#totalPrice").val();
    var deliveryCharge = $("#deliveryCharge").val();
    // point가 주어지지 않았다면 기본값으로 $(".pointInput").val() 사용
    point = (typeof point !== 'undefined') ? point : $("#point").val();
    // totalPrice와 discount가 데이터가 없을 경우 0으로 처리
    point = (point) ? point : 0;
    discount = (discount) ? discount : 0;
    console.log(point);
    console.log(discount);
    // Ajax를 사용하여 서버에 데이터 전송
    $.ajax({
        url: "/order/discountedAmount",
        method: "POST",
        dataType : "json",
        data: { selectCouponDiscountRate: discount,
                totalPrice: totalPrice,
                deliveryCharge: deliveryCharge,
                point: point
        },
        success: function (response) {
            // 서버 응답 처리
            console.log("서버 응답:", response);
  
        // 숫자를 #,### 형식으로 변환
        var TotalPriceWithShippingformattedData = (response.TotalPriceWithShipping).toLocaleString();
        var couponDiscountformattedData = (response.couponDiscount).toLocaleString();
        var pointDiscountformattedData = (response.point).toLocaleString();
            $(".couponDiscount").text(couponDiscountformattedData+"원"); // 쿠폰 할인 업데이트
            $(".TotalPriceWithShipping").text(TotalPriceWithShippingformattedData+"원");//최종 결제금액 업데이트
            $(".pointDiscount").text(pointDiscountformattedData+"원");
        },
        error: function (error) {
            console.error("Ajax 오류:", error);
        }
    });
}
//배송지 입력시 BYTE 계산하여 넘길경우 알림창 띄우기
function fc_chk_byte(aro_name,ari_max) {
          var ls_str = aro_name.value;
          var li_str_len = ls_str.length;
          var li_max = ari_max;
          var i = li_byte = li_len = 0;
          var ls_one_char = ls_str2 = "";
          for(i=0; i< li_str_len; i++) {
            ls_one_char = encodeURIComponent(ls_str.charAt(i));
            if( ls_one_char.length == 1 ) li_byte++;
            else if( ls_one_char.indexOf("%u") != -1 )  li_byte += 2;//Db가 한글을 3byte로 인식하여 2->3
            else if( ls_one_char.indexOf("%") != -1 ) li_byte += ls_one_char.length/3;
      if(li_byte <= li_max){
       li_len = li_byte;
      }
          }
          if(li_byte > li_max) {
            alert( li_max + "byte, 초과 ");
            ls_str2 = ls_str.substr(0, li_len);
            aro_name.value = ls_str2;
            document.sms.char_byte.value = 200;
          }else {
            document.sms.char_byte.value = li_byte;
          }
          aro_name.focus();
        }
//배송메세지 TEXTAREA 입력하면 배송메세지 선택 0으로 돌려줌
function updateSelect(textarea) {
    var select = document.getElementById("deliveryMessageSelect");
    select.selectedIndex = 0;
}
//배송메세지 선택하면 TEXTAREA 비워줌
function clearTextarea() {
    $("#txta").val(""); // 배송메세지 선택 시 텍스트 영역 비우기
}

//주문 완료후 데이터 넘겨주기
 