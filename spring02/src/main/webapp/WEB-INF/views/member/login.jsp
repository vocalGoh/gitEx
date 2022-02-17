<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4-6. 로그인 페이지</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
$(function(){ //4-7. 입력검증 및 로그인
	$("#btnLogin").click(function() {//아래 로그인 버튼을 누르면
		var userid=$("#userid").val();//태그의 value 속성값
		var passwd=$("#passwd").val();
		if(userid==""){
			alert("아이디를 입력하세요.");
			$("#userid").focus();//입력 포커스 이동
			return;//함수 종료
		}
		if(passwd==""){
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();//입력 포커스 이동
			return;//함수 종료
		}
		//폼 데이터를 서버로 제출
		document.form1.action="${path}/member/login_check.do";
		document.form1.submit();//4-8. Controller로 이동(4-9번)
	});
});
</script>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<h2>로그인</h2>
	<form name="form1" method="post">
		<table border="1" style="width: 100%">
			<tr> <td>아이디</td> <td><input name="userid" id="userid"></td> </tr>
			<tr> <td>비밀번호</td> <td><input type="password" name="passwd" id="passwd"></td> </tr>
			<tr> 
				<td colspan="2" align="center"> <input type="button" id="btnLogin" value="로그인"> 
					<c:if test="${param.message == 'nologin'}"> <div style="color: red;">로그인 하신 후 사용하세요.</div> </c:if> 
					<c:if test="${message == 'error'}"> <div style="color: red;">아이디 또는 비밀번호가 일치하지 않습니다.</div> </c:if> 
					<c:if test="${message == 'logout'}"> <div style="color: blue;">로그아웃 처리되었습니다.</div> </c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>