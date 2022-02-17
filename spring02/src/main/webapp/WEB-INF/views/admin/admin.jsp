<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11-8. 관리자 로그인 성공 페이지</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>
 ${sessionScope.admin_name}
 (${sessionScope.admin_userid})님 환영합니다.
</h2>

<!-- 이제 AdminController의 11-9번으로 이동하여 로그아웃 처리됐을 때를 살펴보자 -->

</body>
</html>