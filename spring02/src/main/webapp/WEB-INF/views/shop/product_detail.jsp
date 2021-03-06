<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3-6. 상품 정보 페이지</title>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<h2>상품정보</h2>
	<table>
		<tr>
			<td><img src="${path}/images/${dto.picture_url}" width="300px" height="300px"></td>
			<td align="center">
				<table>
					<tr> <td>상품명</td> <td>${dto.product_name}</td> </tr>
					<tr> <td>가격</td> <td>${dto.price}</td> </tr>
					<tr> <td>&nbsp;</td> <td>${dto.description}</td> </tr>
					<tr>
						<td colspan="2">
							<form name="form1" method="post" action="${path}/shop/cart/insert.do">
								<input type="hidden" name="product_id" value="${dto.product_id}">
								<select name="amount">
									<c:forEach begin="1" end="10" var="i">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>
								
								<!-- 3-7. 장바구니에 담기 버튼을 누르면 form 태그에 의해 CartController로 이동하게 됨(3-8번)--> 
								<input type="submit" value="장바구니에 담기">
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>