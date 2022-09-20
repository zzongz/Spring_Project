<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<style>
	h1{
		margin-top:0px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="/controller/member/logout";
		})
		
		$("#registBtn").on("click", function(){
			location.href="/controller/member/register";
		})
	})
</script>
<h1>로그인</h1>
<form name='homeForm' method="post" action="/controller/member/login">
	<c:if test="${member == null}">
		<div>
			<label for="userId">아이디</label>
			<input type="text" id="userId" name="userId">
		</div>
		<br>
		<div>
			<label for="userPass">비밀번호</label>
			<input type="password" id="userPass" name="userPass">
		</div>
		<br>
		<div>
			<button class="btn btn-default" type="submit">로그인</button>
			<button class="btn btn-default" type="button" id="registBtn">회원가입</button>
		</div>
	</c:if>
	<c:if test="${member != null }">
		<div>
			<p style = "font-size:15px">${member.userId}님 환영 합니다.</p>
			<button class="btn btn-default" id="logoutBtn" type="button">로그아웃</button>
		</div>
	</c:if>
	<c:if test="${msg == false}">
		<p style="color: red;">아이디와 비밀번호를 확인해주세요.</p>
	</c:if>
</form>
