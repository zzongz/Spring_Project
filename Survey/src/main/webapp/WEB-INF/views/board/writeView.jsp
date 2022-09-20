<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function(){
	
 		$(".write_btn").on("click", function(){
 			var form = $('#writeform')[0];
 			var formdata = new FormData(form);
 			
			$.ajax({
				url : "/controller/board/write",
				type : "POST",
				enctype : 'multipart/form-data',
				data : formdata,
				contentType : false,
				processData : false,
				success : function(data){
					if(data == "insert"){
						alert("insert 성공");
						window.opener.document.location.href='list';
						self.close();
					}else{
						alert("insert 실패");
					}
				}
			})
		});
			
		fn_addFile();
	})
	
	function fn_addFile(){
		var fileIndex = 1;
		
		$(".fileAdd_btn").on("click", function(){
			$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
		});
		$(document).on("click", "#fileDelBtn", function(){
			$(this).parent().remove();
		});
	}
</script>
<body>
	<div class="container">
		<header>
			<h1>글 등록</h1>
		</header>
		<hr />
		<section id="container">
			<form id="writeform">
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
						</tr>
						<tr>
							<td id="fileIndex">
								<!-- <input type="file" name="file"> -->
							</td>
						</tr>
						<tr>
							<td>
								<button class="fileAdd_btn" type="button">파일 추가</button>
								<button type="button" class="write_btn btn btn-warning">등록</button>
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