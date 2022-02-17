<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<!-- /spring02/src/main/webapp/WEB-INF/views/admin/admin_product_write.jsp 열고 수정 -->
<!-- ckeditor 라이브러리 적용 -->
<script src="${path}/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
function product_write(){
	var product_name=$("#product_name").val();
	var price=$("#price").val();
	var description=$("#description").val();
	if(product_name==""){//빈값이면
		alert("상품이름을 입력하세요");
		$("#product_name").focus();//입력포커스 이동
		return;
	}
	if(price==""){//빈값이면
		alert("가격을 입력하세요");
		$("#price").focus(); //입력포커스 이동
		return;
	}
/* 	if(description==""){//빈값이면
		alert("상품설명을 입력하세요");
		$("#description").focus(); //입력포커스 이동
		return;
	} */
	document.form1.action="${path}/shop/product/insert.do";
	document.form1.submit();
}
</script>
</head>











<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품 등록</h2>
<form name="form1" method="post" enctype="multipart/form-data">
<table>
 <tr>
  <td>상품명</td>
  <td><input name="product_name" id="product_name" ></td>
 </tr>
 <tr>
  <td>가격</td>
  <td><input name="price" id="price" ></td>
 </tr>
 <tr>
  <td>상품설명</td>
  <td><textarea rows="5" cols="60" name="description" id="description"></textarea>
   
  
  <script type="text/javascript">
   CKEDITOR.replace("description", {
	   filebrowserUploadUrl : "${path}/imageUpload.do"});
  </script>
  
  </td>
 </tr>
 <tr>
  <td>상품이미지</td>
  <td><input type="file" name="file1" id="file1" ></td>
 </tr>
 <tr>
  <td colspan="2" align="center">
   <input type="button" value="등록" onclick="product_write()">
   <input type="button" value="목록" 
   onclick="location.href='${path}/admin/list.do'">
  </td>
 </tr>
</table>
</form>

</body>
</html>




