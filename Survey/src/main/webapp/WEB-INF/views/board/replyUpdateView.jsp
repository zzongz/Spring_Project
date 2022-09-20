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
				location.href = "/board/readView?seq=${replyUpdate.seq}";
			})
		})
	</script>
	<body>
		<div class="container">
			<header>
				<h1>댓글 수정</h1>
			</header>
			<hr />
			<section id="container">
				<form name="updateForm" role="form" method="post" action="/controller/board/replyUpdate">
					<input type="hidden" name="seq" value="${replyUpdate.seq}" readonly="readonly"/>
					<input type="hidden" id="rseq" name="rseq" value="${replyUpdate.rseq}" />
					<table>
						<tbody>
							<tr>
								<td>
								<div class="form-group">
									<label for="content" class="col-sm-2 control-label" style="display:contents">내용</label>
									<input type="text" id="content" class="form-control" name="content" value="${replyUpdate.content}"/>
								</div>
								</td>
							</tr>
						</tbody>			
					</table>
					<div>
						<button type="submit" class="update_btn btn btn-warning">저장</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>