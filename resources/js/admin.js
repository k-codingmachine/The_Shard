$(function(){
	$('.logoutBtn').click(function(e){
		e.preventDefault();
		$('#logoutForm').submit();
	});

	$('.adminDeleteUser').click(function(e){
		e.preventDefault();

		if(confirm("정말 회원을 삭제하시겠습니까?")){
			var email = $(this).attr("href");

			$('#deleteUser').append("<input type='hidden' name='email' value="+ email +" />");
			$("#deleteUser").submit();
		}
	});
	
	$('.getNotice').click(function(e){
		e.preventDefault();
		var noticeNum = $(this).attr("href");
		
		$('#noticeGet').append("<input type='hidden' name='noticeNum' value='"+noticeNum+"' /> ");
		$('#noticeGet').submit();
	});
});

function NoticeCheck() {
	if ($('#noticeTitle').val() == "") {
    	alert("제목은 필수입력 사항입니다.");
        $('#noticeTitle').focus();
        return false;
    }
	
	if ($('#noticeContent').val() == "") {
    	alert("내용은 필수입력 사항입니다.");
        $('#noticeContent').focus();
        return false;
    }
	
	$('#noticeInsert').submit();
	
	
}