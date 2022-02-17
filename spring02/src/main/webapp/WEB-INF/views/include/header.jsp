<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- ${pageContext.request.contextPath} : 내 현재 위치 -->
<c:set var="path" value="${pageContext.request.contextPath}" />


<!-- 4-6. 위치 수정 : header.jsp에서 위치를 수정했다! 참고하길...-->
<script src="${path}/include/jquery-3.6.0.min.js"></script>


<!-- 1-2-1. header.jsp에 css 추가 -->
<link rel="stylesheet" href="${path}/include/style.css">

