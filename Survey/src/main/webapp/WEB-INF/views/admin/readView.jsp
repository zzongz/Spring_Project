<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>
</head> --%>
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form[name='readForm']");

 		// 삭제
		$(".delete_btn").on("click", function() {
			formObj.attr("action", "/controller/menu/delete");
			formObj.attr("method", "post");
			formObj.submit();
			alert("삭제완료");
		})
	
	})
	
	function read_popup_close() {
		opener.parent.location = 'list';
		window.close();
	}

</script>
<!-- <body> -->
	<div class="container">
		<header>
			<h1>메뉴 상세 조회</h1>
		</header>
		<hr />
		<section id="container">
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="menu_seq" name="menu_seq" value="${read.menu_seq}" />
				<input type="hidden" id="page" name="page" value="${scri.page}"> 
				<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}">
			</form>
			<table>
				<tbody>
					<tr>
						<td>
						<div class="form-group">
							<label for="menu_name" class="col-sm-2 control-label" style="display:contents">메뉴</label>
							<input type="text" id="menu_name" name="menu_name" class="form-control" value="${read.menu_name}" readonly="readonly" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="menu_type" class="col-sm-2 control-label" style="display:contents">종류</label>
							<input type="text" id="menu_type" name="menu_type" class="form-control" value="${read.menu_type}" readonly="readonly" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="menu_link" class="col-sm-2 control-label" style="display:contents">링크</label>
							<input type="text" id="menu_link" name="menu_link" class="form-control" value="${read.menu_link}" readonly="readonly" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="board_seq" class="col-sm-2 control-label" style="display:contents">게시판 번호</label>
							<input type="text" id="board_seq" name="board_seq" class="form-control" value="${read.board_seq}" readonly="readonly" />
						</div>
						</td>
					</tr>
					<tr>
						<td>
						<div class="form-group">
							<label for="user_auth" class="col-sm-2 control-label" style="display:contents">권한</label>
							<input type="text" id="user_auth" name="user_auth" class="form-control" value="${read.user_auth}" readonly="readonly" />
						</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<button type="submit" class="delete_btn btn btn-danger">삭제</button>
				<button type="button" class="btn btn-primary" onclick="read_popup_close()">닫기</button>
			</div>
		</section>
	</div>
<!-- </body>
</html> -->