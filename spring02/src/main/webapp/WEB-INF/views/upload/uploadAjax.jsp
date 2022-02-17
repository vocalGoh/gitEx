<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<style type="text/css">
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}
</style>


<script type="text/javascript">
function checkImageType(fileName){
	var pattern=/jpg|png|gif/i; //정규표현식(i는 대소문자 무시)
	return fileName.match(pattern);// 규칙에 맞으면 true가 리턴
}
function getOriginalName(fileName){
	if(checkImageType(fileName)){//이미지 파일이면 skip
		return;
	}
	var idx=fileName.indexOf("_")+1;//uuid를 제외한 파일이름만 뽑음
	return fileName.substr(idx);
}
function getImageLink(fileName){
	if(!checkImageType(fileName)){//이미지 파일이 아니면 skip
		return;
	}
	// 2022/01/25/s_5f7435a6-639e-4675-8267-7da10c663bc7_apple.jpg
	var front=fileName.substr(0,12);//(0~11번째까지 자르고)
	var end=fileName.substr(14);//14번째 문자열 앞의 s_제거
	return front+end;
}









$(function(){
	//드래그알때 기본효과를 막음
	//dragenter: 마우스가 대상 객체의 위로 처음 진입할 때,
	//dragover: 드래그하면서 마우스가 대상 객체의 위에 자리 잡고 있을 때
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();//기본 효과를 막음
	});
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();//기본 효과를 막음
		//첨부파일 배열(여러개를 동시에 선택해서 드래그 할 수도 있기 때문에 배열처리 )
		var files=event.originalEvent.dataTransfer.files;
		var file=files[0]; //첫번째 첨부파일
		//FormData()는 Ajax방식의 파일업로드의 핵심 객체로 form태그와 같은 역할을 함
		var formData=new FormData();
		formData.append("file", file);//폼에 file변수 추가
		
		//서버에 파일 업로드(백그라운드에서 실행)
		$.ajax({
			type: "post",
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,//파일전송시 자동으로 쿼리스트링형식으로 전송되지 않도록 막는 처리
			contentType: false, //multipart/form-data로 처리되는것과 같음
			success: function(data,status,req){
				console.log("data:"+data); //업로드된 파일 이름
				console.log("status:"+status);//성공,실패 여부
				console.log("req:"+req.status);//요청코드값
				
				var str="";
				if(checkImageType(data)){ //이미지 파일
					str="<div><a href='${path}/upload/displayFile?fileName="
							+getImageLink(data)+"'>";
					str+="<img src='${path}/upload/displayFile?fileName="+data+"'></a>";		
				}else{ //이미지가 아닌 경우
					str="<div>";
					str+="<a href='${path}/upload/displayFile?fileName="+data+"'>"
							+getOriginalName(data)+"</a>";
				}
				str+="<span data-src="+data+">[삭제]</span></div>";
				$(".uploadedList").append(str);
				
			}
		});
	});//end $(".fileDrop")

	
	
	
	//첨부파일 삭제 함수
	$(".uploadedList").on("click","span", 
			function(event){//내부적으로 span태그가 클릭되면 삭제
		var that=$(this);//this는 현재 클릭한 태그, 즉 span태그
		$.ajax({
			url: "${path}/upload/deleteFile",
			type: "post",
			data: {fileName: $(this).attr("data-src")},
			dataType: "text",
			success: function(result){
				if(result=="deleted"){
					that.parent("div").remove();//파일삭제되면 행전체<div>를 삭제 처리
					//that은 span태그를 의미하는데 그 부모인 div태그를 지운다는 뜻
				}
			}
		});
	});
	
	
});//end $(function(){


</script>



</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>Ajax File Upload</h2>
<!-- 파일을 업로드할 영역  -->
<div class="fileDrop"></div>
<!-- 업로드된 파일 목록을 출력할 영역 -->
<div class="uploadedList"></div>

</body>
</html>








