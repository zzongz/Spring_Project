<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
/* 		var formObj = $("form[name='updateForm']"); */

		$(document).on("click", "#fileDel", function(){
			$(this).parent().remove();
		})
		
		fn_addFile();
		
/* 		$(".update_btn").on("click", function(){
			formObj.attr("action", "/controller/board/update");
			formObj.attr("method", "post");
			formObj.submit();
		}) */
		
 		$(".update_btn").on("click", function(){
 			var form = $('#updateform')[0];
 			var formdata = new FormData(form);
 			
			$.ajax({
				url : "/controller/board/update",
				type : "POST",
				enctype : 'multipart/form-data',
				data : formdata,
				contentType : false,
				processData : false,
				success : function(data){
					if(data == "success"){
						alert("update 성공");
						window.opener.document.location.href='list';
						self.close();
					}else{
						alert("update 실패");
					}
				}
			})
		});
	})
	
	function fn_addFile(){
		var fileIndex = 1;
		
		$(".fileAdd_btn").on("click", function(){
			$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_" +(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
		});
		$(document).on("click", "#fileDelBtn", function(){
			$(this).parent().remove();
		});
	}
	
	var fileNoArry = new Array();
	var fileNameArry = new Array();
	function fn_del(value, name){
		
		fileNoArry.push(value);
		fileNameArry.push(name);
		$("#fileNoDel").attr("value", fileNoArry);
		$("#fileNameDel").attr("value", fileNameArry);
	}
</script>
<body>
	<div class="container">
		<header>
			<h1>글 수정</h1>
		</header>
		<hr />
		<section id="container">
			<form id="updateform">
				<input type="hidden" name="seq" value="${update.seq}" readonly="readonly" />
				<input type="hidden" id="fileNoDel" name="fileNoDel[]" value="">
				<input type="hidden" id="fileNameDel" name="fileNameDel[]" value="">
				<table>
					<tbody>
						<tr>
							<td>
							<div class="form-group">
								<label for="subject" class="col-sm-2 control-label" style="display:contents">제목</label>
								<input type="text" id="subject" name="subject" class="form-control" value="${update.subject}" />
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="content" class="col-sm-2 control-label" style="display:contents">내용</label>
								<textarea id="content" name="content" class="form-control"><c:out value="${update.content}" /></textarea>
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="name"  class="col-sm-2 control-label" style="display:contents">작성자</label>
								<input type="text" id="name" name="name" class="form-control" value="${update.name}" readonly="readonly" />
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="regist_date" class="col-sm-2 control-label" style="display:contents">날짜</label>
								<fmt:formatDate value="${update.regist_date}" pattern="yyyy-MM-dd HH:mm:ss" />
							</div>
							</td>
						</tr>
						<tr>
							<td id="fileIndex">
								<c:forEach var="file" items="${file}" varStatus="var">
								<div>
									<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.FILE_NO}">
									<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
									<a href="#" id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
									<button id="fileDel" onclick="fn_del('${file.FILE_NO}','FILE_NO_${var.index}');" type="button">삭제</button><br>
								</div>
								</c:forEach>
							</td>
						</tr>
					</tbody>
				</table>
				<div>
					<button type="button" class="fileAdd_btn">파일 추가</button>
					<button type="button" class="update_btn btn btn-warning">저장</button>
				</div>
			</form>
		</section>
		<hr />
	</div>
</body>
</html>