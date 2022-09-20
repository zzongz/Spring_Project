<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<script>
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="/controller/member/logout";
		})
		
		if("no" == "${error}"){
			alert("이미 참여하신 설문입니다.");
		}
	})
</script>
	<div class="main_container">
		<br>
		<div id="main">
			<form role="form" method="get">
				<table class="table table-hover"
					style="text-align: center; border: 2px solid #dddddd">
					<thead>
						<tr>
							<th style="background-color: skyblue; text-align: center">제목</th>
							<th style="background-color: skyblue; text-align: center">글쓴이</th>
							<th style="background-color: skyblue; text-align: center">등록 날짜</th>
							<th style="background-color: skyblue; text-align: center">진행 여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td style="display:none"><c:out value="${list.survey_seq}" /></td>
								<td><a href="/controller/survey/info?survey_seq=${list.survey_seq}"><c:out value="${list.subject}" /></a></td>
								<td><c:out value="${list.userid}" /></td>
								<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><c:out value="${list.progress}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>