// 재고 및 상품 ajax
function showItemDetails() {
    var sizeSelect = document.getElementById("sizeSelect");
    var sizeOptionM = document.getElementById("sizeOptionM");
    var sizeOptionL = document.getElementById("sizeOptionL");
    var sizeOptionXL = document.getElementById("sizeOptionXL");
    var itemDetailsDiv = document.getElementById("itemDetails")

    // 각 사이즈의 재고에 따라 옵션을 제어합니다.
    if (itemCountM === 0) {
        sizeOptionM.style.display = "none";
    } else {
        sizeOptionM.style.display = "block";
    }

    if (itemCountL === 0) {
        sizeOptionL.style.display = "none";
    } else {
        sizeOptionL.style.display = "block";
    }

    if (itemCountXL === 0) {
        sizeOptionXL.style.display = "none";
    } else {
        sizeOptionXL.style.display = "block";
    }

    // 모든 사이즈의 재고가 0일 경우 "품절되었습니다"를 표시합니다.
    if (itemCountM === 0 && itemCountL === 0 && itemCountXL === 0) {
        itemDetailsDiv.innerHTML = "품절되었습니다";
        itemDetailsDiv.style.display = "block";
        sizeSelect.style.display = "none";
    } else {
        itemDetailsDiv.style.display = "none";
        sizeSelect.style.display = "block";
    }

    var selectElement = document.getElementById("sizeSelect");
    var selectedValue = selectElement.options[selectElement.selectedIndex].value;

    if (selectedValue === "사이즈 선택") {
        itemDetailsDiv.style.display = "none";
    } else {
        $.ajax({
            type: 'get',
            url: '/shard/itemInfo',
            data: { sizeSelect: selectedValue },
            success: function(response) {
                var itemDetailsDiv = document.getElementById("itemDetails");
                itemDetailsDiv.innerHTML = response;
                itemDetailsDiv.style.display = "block";
            },
            error: function() {
                console.log("에러 발생");
            }
        });
    }
}


//info페이지에서 itemNum을 가지고 qna로 넘겨서 상품문의가 가능하도록 함, 완료
function redirectToInsertQnA() {
	    var queryString = window.location.search;
	    var urlParams = new URLSearchParams(queryString);
	    
	    // itemNum 값 추출
	    var itemNum = urlParams.get('itemNum');
	    var pageNum = 1; // pageNum 값 설정 (필요에 따라 변경 가능)
	
	    if (itemNum) {
	        // itemNum과 pageNum을 포함한 URL 생성
	        var url = '/item/insertQna?itemNum=' + itemNum + '&pageNum=' + pageNum;
	
	        // URL로 페이지 이동
	        window.location.href = url;
	    } else {
	        console.error('Item number not found.');
	        // 처리할 수 없는 경우 에러 메시지 출력 또는 다른 작업 수행
	    }
}

// whislist 관련
    function gologin() {
    // 사용자에게 경고창을 표시
    var confirmation = confirm("로그인이 필요한 페이지입니다. 로그인 페이지로 이동하시겠습니까?");

    // 사용자가 확인을 선택했을 때만 이동
    if (confirmation) {
      window.location.href = "/shard/login"; // 페이지 이동
    }
  }



