<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자용 상품 목록 페이지</title>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<h2>상품목록</h2>
	<table border="1" style="width: 100%">
		<tr>
			<th>상품코드</th>
			<th>&nbsp;</th>
			<th>상품명</th>
			<th>가격</th>
		</tr>
		<c:forEach var="row" items="${list}">
			<tr>
			 <td>${row.product_id}</td>
			  <td><img src="${path}/images/${row.picture_url}" width="100px" height="100px"></td>

				<!-- 13. 상품 편집 기능 추가하기 -->
				<td>
					<a href="${path}/shop/product/detail/${row.product_id}">${row.product_name}</a> 
						<!-- 13-1. 관리자에게만 편집 버튼 표시. 편집 버튼을 누르면 ProductController로 이동(13-2번) --> 
						<c:if test="${sessionScope.admin_userid != null}">
							<a href="${path}/shop/product/edit/${row.product_id}">[편집]</a>
						</c:if>
				</td>

				<td><fmt:formatNumber value="${row.price}" pattern="#,###" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>