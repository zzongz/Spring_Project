<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<title>Insert title here</title>
<link href="/controller/resources/css/list.css" rel="stylesheet" />

<script>
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="/controller/member/logout";
		})
	})

	function write_popup(){
		var url = "writeView"
		var name = "write_popup";
		window.open(url, name, "width=1000, height=1000, toolbar=no, status=no, loaction=no, scrollbars=yes, menubar=no,resizable=yes, left=50, right=50");
	}
	
	function read_popup(seq){
		var url = "readView?seq="+seq;
		var name = "read_popup";
		window.open(url, name, "width=1000, height=1000, toolbar=no, status=no, loaction=no, scrollbars=yes, menubar=no,resizable=yes, left=50, right=50");
	}
	
</script>
	<div class="main_container">
		<br>
		<div id="main">
			<form role="form" method="get">
			<div id="listdiv">
				<c:forEach items="${list}" var="list">
				<div id="listdiv_first">
					<div id="list_top">
						<div id="list_subject">
						<p style="display:none"><c:out value="${list.seq}" /></p>
						<p><a href="" onclick="read_popup(${list.seq})"><c:out value="${list.subject}" escapeXml="false" /></a></p>
						<p style="display:none"><c:out value="${list.content}" /></p>
						</div>
						<img src="<spring:url value='/resources/img/${list.ORG_FILE_NAME}' />" style="width:292px; height:147px;" />
					</div>
					<div id="list_bottom" style="font-size:12px;">
						<p>작성자:<c:out value="${list.name}" />/</p>
						<p>작성일:<fmt:formatDate value="${list.regist_date}" pattern="yyyy-MM-dd HH:mm:ss" />/</p>
						<p>조회수:<c:out value="${list.read_count}" /></p>
					</div>
				</div>
				</c:forEach>
			</div>
			<br>
			</form>
		</div>
		<div>
			<button type="button" onclick="write_popup()" class="btn btn-primary">글쓰기</button>
		</div>
		<div id="bottom">
			<ul class="pagination">
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
					<a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
