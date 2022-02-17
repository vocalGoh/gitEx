<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- 5-2. 로그인 했으니 sesssion 값은 true가 되어야함 -->
<%@ page session="true" %>


<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
<%@ include file="include/header.jsp" %>	
</head>
<body>
<%@ include file="include/menu.jsp" %>
<c:if test="${sessionScope.userid != null}">
 <h2>
  ${sessionScope.name}
  (${sessionScope.userid})님의 방문을 환영합니다.
 </h2>
</c:if>


<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- /spring02/src/main/webapp/WEB-INF/views/home.jsp에서 수정 -->
<%= application.getRealPath("/webapp/WEB-INF/views/images/") %>

</body>
</html>
