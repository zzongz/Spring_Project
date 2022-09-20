<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script>
	function write_popup_close(){
		opener.parent.location='list';
		window.close();
	}
</script>
</head>
<body>
	<div class="container">
		<header>
			<h1>답글 등록</h1>
		</header>
		<hr />
		<section id="container">
			<form role="form" method="post" action="/controller/board/answerwrite">
				<input type="hidden" name="seq" value="${seq }"/>
				<table>
					<tbody>
						<tr>
							<td>
							<div class="form-group">
								<label for="subject" class="col-sm-2 control-label" style="display:contents">제목</label> 
								<input type="text" id="subject" name="subject" class="form-control" placeholder="제목을 입력하세요" />
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="content" class="col-sm-2 control-label" style="display:contents">내용</label>
								<textarea id="content" name="content" class="form-control" placeholder="내용을 입력하세요"></textarea>
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label" style="display:contents">작성자</label>
								<input type="text" id="name" class="form-control" name="name" value="${sessionScope.member.userId}" placeholder="작성자를 입력하세요" readonly="readonly" />
							</div>
							</td>
						<tr>
							<td>
								<button type="submit" class="btn btn-warning">등록</button>
								<button type="button" onclick="write_popup_close()" class="btn btn-primary">닫기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</section>
		<hr />
	</div>
</body>
</html>