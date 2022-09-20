<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function(){
				location.href = "/controller/board/readView?seq=${replyDelete.seq}";
			})	
		})	
	</script>
	<body>
		<div class="container">
			<header>
				<h1>댓글 삭제</h1>
			</header>
			<hr />
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/controller/board/replyDelete">
					<input type="hidden" name="seq" value="${replyDelete.seq}" readonly="readonly"/>
					<input type="hidden" id="rseq" name="rseq" value="${replyDelete.rseq}" />
					<div>
						<h4>삭제 하시겠습니까?</h4>
						<button type="submit" class="delete_btn btn btn-danger">삭제</button>
						<button type="button" class="cancel_btn btn btn-primary">취소</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>