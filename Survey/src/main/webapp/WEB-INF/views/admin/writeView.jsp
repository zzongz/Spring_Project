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
/* 	$(document).ready(function(){
		var formObj = $("form[name='writeForm']");
 		var frm = $("form[name='writeForm']").serialize(); 
		
		$(".write_btn").on("click", function(){
			formObj.attr("action", "/controller/menu/write");
			formObj.attr("method", "post");
			formObj.submit();
		});
		
 		$(".write_btn").on("click", function(){
			$.ajax({
				type:"post",
				url:"controller/board/write",
				data: 
				success:function(){
					window.opener.document.location.href='list';
					self.close();
				}
			})
		}); 
			
	}) */
    $(document).ready(function () {
    	
    })
    
   	function write_popup_close(){
		opener.parent.location='list';
		window.close();
	}
</script>
<body>
	<div class="container">
		<header>
			<h1>메뉴 등록</h1>
		</header>
		<hr />
		<section id="container">
			<!-- <form name="writeForm" enctype="multipart/form-data"> -->
			<form role="form" method="post" action="/controller/menu/write">
			
				<table>
					<tbody>
						<tr>
							<td>
							<div class="form-group">
								<label for="menu_name" class="col-sm-2 control-label" style="display:contents">메뉴명</label> 
								<input type="text" id="menu_name" name="menu_name" class="form-control" placeholder="메뉴명을 입력하세요" />
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="menu_type" class="col-sm-2 control-label" style="display:contents">메뉴 종류</label><br>
								<select id="sel_type" name="menu_type" style="width:100px; hegiht:50px;">
									<option value="L">L</option>
								</select>
								<!-- <input type="hidden" id="menu_type" name="menu_type" class="form-control" /> -->
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="menu_link" class="col-sm-2 control-label" style="display:contents">링크</label>
								<input type="text" id="menu_link" class="form-control" name="menu_link" placeholder="링크를 입력하세요" />
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="form-group">
								<label for="menu_type" class="col-sm-2 control-label" style="display:contents">권한</label><br>
								<select id="sel_auth" name="user_auth" style="width:100px; hegiht:50px;">
									<option value="U">U</option>
							        <option value="A">A</option>
								</select>
								<!-- <input type="hidden" id="user_auth" name="user_auth" class="form-control" /> -->
							</div>
							</td>
						</tr>
						<tr>
							<td>
								<button type="submit" class="write_btn btn btn-warning">등록</button>
								<button type="button" onclick="write_popup_close()" class="btn btn-primary">닫기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</section>
	</div>
</body>
</html>