<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[name='readForm']");

		// 수정 
		$(".update_btn").on("click", function() {
			formObj.attr("action", "/controller/board/updateView");
			formObj.attr("method", "get");
			formObj.submit();
		})

		// 삭제
		$(".delete_btn").on("click", function() {
			var seq = $("#seq").val();
			
			$.ajax({
				url : "/controller/board/delete",
				type : "POST",
				data : {
					"seq" : seq
				},
				success : function(data){
					if(data == "delete"){
						alert("삭제 성공");
						window.opener.document.location.href='list';
						self.close();
					}else{
						alert("삭제 실패");
					}
				}
			})
		})
		
 		// 답글
  		$("#answerBtn").on("click", function()	{
  			let seq = "${seq}"
			location.href = "/controller/board/answerwriteView?seq="+seq;
		})
		
		// 댓글 작성
		$(".replyWriteBtn").on("click", function(){
			var formObj = $("form[name='replyForm']");
			formObj.attr("action", "/controller/board/replyWrite");
			formObj.attr("method", "POST");
			formObj.submit();
		})
		
		//댓글 수정
		$(".replyUpdateBtn").on("click", function(){
			location.href = "/controller/board/replyUpdateView?seq=${read.seq}"
							+ "&rseq="+$(this).attr("data-rseq");
		})
				
		//댓글 삭제
		$(".replyDeleteBtn").on("click", function(){
			location.href = "/controller/board/replyDeleteView?seq=${read.seq}"
				+ "&rseq="+$(this).attr("data-rseq");
		})
		
		// 좋아요 등록
		$("#likein_btn").on("click", function(){
			var board_seq = $("#seq").val();
			
			$.ajax({
				url : "/controller/board/likeinsert",
				type : "POST",
				data : {
					"seq" : board_seq
				},
				success : function(data){
					location.reload();
				},
				error : function(){
				}
			});
		})
		
		// 좋아요 취소
		$("#likedel_btn").on("click", function(){
			var board_seq = $("#seq").val();
			
			$.ajax({
				url : "/controller/board/likedelete",
				type : "POST",
				data : {
					"seq" : board_seq
				},
				success : function(data){
					location.reload();
				},
				error : function(){
				}
			});
		})
		
	})
	
	function fn_fileDown(fileNo){
		var filename = $(event.target).text();
		var formObj = $("form[name='readForm']");
		$("#FILE_NO").attr("value", fileNo);
		$("#filename2").val(filename);
		formObj.attr("action", "/controller/board/fileDown");
		formObj.submit();
		setTimeout(function(){
			location.reload();
		}, 100);
	}
</script>
<body>
	<div class="container">
		<header>
			<h1>글 조회</h1>
		</header>
		<hr />
		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="seq" name="seq" value="${read.seq}" />
				<input type="hidden" id="page" name="page" value="${scri.page}"> 
				<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}">
				<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}">
				<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
				<input type="hidden" id="FILE_NO" name="FILE_NO" value="">
				<input type="hidden" id="filename2" name="filename2" value="">
			</form>
			<table>
				<tbody>
					<tr>
						<td>
						<div class="form-group">
							<label for="subject" class="col-sm-2 control-label" style="display:contents">제목</label>
							<input type="text" id="subject" name="subject" class="form-control" value="${read.subject}" readonly="readonly" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="content" class="col-sm-2 control-label" style="display:contents">내용</label>
							<textarea id="content" name="content" class="form-control" readonly="readonly"><c:out value="${read.content}" /></textarea>
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label" style="display:contents">작성자</label>
							<input type="text" id="name" name="name" class="form-control" value="${read.name}" readonly="readonly" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="regist_date" class="col-sm-2 control-label" style="display:contents">날짜</label>
							<fmt:formatDate value="${read.regist_date}" pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<c:forEach var="file" items="${file}">
									<img src="<spring:url value='/resources/img/${file.ORG_FILE_NAME}' />" style="width:500px; height:500px;" />
								</c:forEach>
							</div>
						</td>
					</tr>
					<hr />
					<tr>
						<td>
						<span>파일 목록</span>
						<div class="form-group" style="border: 1px solid #dbdbdb;">
							<c:forEach var="file" items="${file}">
								<a href="#" onclick="fn_fileDown('${file.FILE_NO}'); return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
								<p>다운로드 : ${file.DOWNLOAD}</p>
							</c:forEach>
						</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<c:if test="${sessionScope.member.userId eq read.name}">
					<button type="submit" class="update_btn btn btn-warning">수정</button>
					<button type="submit" class="delete_btn btn btn-danger">삭제</button>
				</c:if>
				<c:if test="${likeChk eq 1}">
					<button type="button" id="likedel_btn" class="btn btn-info">좋아요 취소</button>
				</c:if>
				<c:if test="${likeChk ne 1}">
					<button type="button" id="likein_btn" class="btn btn-info">좋아요</button>
				</c:if>
				<button type="button" id="answerBtn" class="btn btn-success">답글</button>
			</div>
		</section>
		<hr />
 		<!-- 댓글 -->
		<h1>댓글</h1>
		<div id="reply">
			<ol class="replyList">
				<c:forEach items="${replyList}" var="replyList">
					<li>
						<p>
							작성자 : ${replyList.writer}<br /> 작성 날짜 :
							<fmt:formatDate value="${replyList.regist_date}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</p>
						<p>${replyList.content}</p>
						<div>
							<c:if test="${sessionScope.member.userId eq replyList.writer}">
							<button type="submit" class="replyUpdateBtn btn btn-warning" data-rseq="${replyList.rseq}">수정</button>
							<button type="button" class="replyDeleteBtn btn btn-danger" data-rseq="${replyList.rseq}">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ol>
		</div>
		<hr>
		<!-- 댓글 작성 폼 -->
		<h1>댓글 작성</h1>
		<form name="replyForm" method="post" class="form-horizontal">
	  			<input type="hidden" id="seq" name="seq" value="${read.seq}" />
				<input type="hidden" id="page" name="page" value="${scri.page}"> 
  				<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
  				<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
  				<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}">
  			<div class="form-group">
    			<label for="writer" class="col-sm-2 control-label">작성자</label>
    			<div class="col-sm-10">
    				<input type="text" id="writer" name="writer" class="form-control" value="${sessionScope.member.userId}" readonly="readonly" />
   				</div>
   			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">댓글 내용</label>
				<div class="col-sm-10">
					<input type="text" id="content" name="content" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="replyWriteBtn btn btn-success">작성</button>
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
</html>