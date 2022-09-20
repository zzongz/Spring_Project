<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script>
	$(document).ready(function(){
		
		$("#login_btn").on("click", function(){
			location.href="/controller/member/loginView";
		})
		
		$("#logout_btn").on("click", function(){
			location.href="/controller/member/logout";
		})
		
		$('#searchBtn').click(function(){
			location.href = "/controller/board/alllist?page=1" + 
			"&searchType=" + $("select option:selected").val() + 
			"&keyword=" + encodeURIComponent($('#keywordInput').val());
		})
		
		$("#logout_btn").on("click", function(){
			location.href="/controller/member/logout";
		})
		
		$(".mlink").on("click", function(){
			var menu_name = $(this).text();
			var menu_seq = $(this).next().next().val();
			var userAuth = $("#userAuth").val();
			location.href = $(this).next().val();
			
			if(userAuth == ''){
				return false;	
			}else{
				$.ajax({
					url : "/controller/menu/menucount",
					type : "POST",
					data : {
						"menu_seq" : menu_seq,
						"menu_name" : menu_name
					},
					success:function(){
					}
				})
			}
		})
		
	})
</script>
<div id="top">
<input type = "hidden" id = "userAuth" value = "${sessionScope.member.auth }"/>
	<div>
	<img src="/controller/resources/img/logo.png" id="fusion_img">
	</div>
	<div class="search row" style="margin-left:450px; margin-top:30px;">
		<div class="col-xs-2 col-sm-2">
		<select name="searchType" class="form-control">
			<option value="t"
				<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
			<option value="c"
				<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>작성자</option>
			<option value="w"
				<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>파일명</option>
		</select>
		</div>
		<div class="col-xs-10 col-sm-10">
			<div class="input-group" style="display:flex">
				<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" class="form-control" style="width:600px;"/>
				<span class="input_group_btn" style="margin-left:20px;"><button id="searchBtn" type="button" class="btn btn-info">검색</button></span>
			</div>
		</div>
	</div>
	<div>
	<c:if test="${sessionScope.member eq null}">
		<button type="button" class="btn btn-info" id="login_btn">로그인</button>
	</c:if>
	<c:if test="${sessionScope.member ne null}">
		<button type="button" class="btn btn-info" id="logout_btn">로그아웃</button>
	</c:if>
	</div>
</div>
<div id="bottom">
	<tiles:importAttribute name="menuList"/>
	<c:choose>
		<c:when test="${sessionScope.member eq null || sessionScope.member.auth == 'U'}">
			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.user_auth == 'U'}">
					<a class = "mlink">${menu.menu_name}</a>
					<input type = "hidden" id = "menuLink" value = "/controller${menu.menu_link}">
					<input type = "hidden" value="${menu.menu_seq}">
				</c:if>
			</c:forEach>
		</c:when>
		<c:when test="${sessionScope.member.auth == 'A'}">
			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.user_auth == 'A'}">
					<a href="/controller${menu.menu_link}">${menu.menu_name}</a>
				</c:if>
			</c:forEach>
		</c:when>
	</c:choose>
	
</div>
