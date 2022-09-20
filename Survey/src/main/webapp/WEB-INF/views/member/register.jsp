<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	 	
	 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
	 	</script>
		<title>회원가입</title>
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			// 취소
			$(".cancle").on("click", function(){
				location.href = "/controller/member/loginView";
			})
		
			$("#submit").on("click", function(){
				if($("#userId").val()==""){
					alert("아이디를 입력해주세요.");
					$("#userId").focus();
					return false;
				}
				if($("#userPass").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#userPass").focus();
					return false;
				}
				if($("#userName").val()==""){
					alert("성명을 입력해주세요.");
					$("#userName").focus();
					return false;
				}
				
				var idchkval = $("#idchk").val();
				
				if(idchkval == "N"){
					alert("중복 확인 버튼을 눌러주세요.");
					return false;
				}else if(idchkval == "Y"){
					$("#regform").submit();
					alert("회원 가입 완료.");
				}
			});
		})
		
		function fn_idchk(){
			var userId = $("#userId").val();
			
			$.ajax({
				url : "/controller/member/idchk",
				type : "POST",
				data : {
					"userId" : userId
				},
				success : function(data){
					if(data == 1){
						alert("이미 사용중인 아이디입니다.");
					}else if(data == 0){
						$("#idchk").attr("value", "Y");
						alert("사용 가능한 아이디입니다.");
					}
				}
			})
		}
	</script>
	<body>
		<section style="display:inline-block" id="container">
			<form action="/controller/member/register" method="post" id="regform">
				<div class="form-group has-feedback">
					<label class="control-label" for="userId">아이디</label>
					<input class="form-control" type="text" id="userId" name="userId" />
					<button class="btn btn-primary" type="button" id="idchk" onclick="fn_idchk()" value="N">중복 확인</button>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userPass">패스워드</label>
					<input class="form-control" type="password" id="userPass" name="userPass" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="userName">성명</label>
					<input class="form-control" type="text" id="userName" name="userName" />
				</div>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit">회원가입</button>
					<button class="cancle btn btn-danger" type="button">취소</button>
				</div>
			</form>
		</section>
	</body>
</html>