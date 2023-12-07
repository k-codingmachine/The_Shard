<!-- itemSearch/nosearch.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>No Search Result</title>
    <script>
        function goBack() {
            window.history.back();
        }

        window.onload = function () {
            alert("검색 결과가 없습니다.");
            goBack();
        };
    </script>
</head>
<body>
   
</body>
</html>
