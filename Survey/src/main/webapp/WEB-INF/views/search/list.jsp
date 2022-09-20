<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/controller/resources/css/list.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	function read_popup(seq){
		var url = "readView?seq="+seq;
		var name = "read_popup";
		window.open(url, name, "width=1000, height=1000, toolbar=no, status=no, loaction=no, scrollbars=yes, menubar=no,resizable=yes, left=50, right=50");
	}
</script>
</head>
<body>
	<div class="main_container">
		<div id="main">
			<form role="form" method="get">
				<h1>포토 갤러리</h1>
				<table class="table table-hover" style="text-align: center; border: 2px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: skyblue; text-align: center">제목</th>
						<th style="background-color: skyblue; text-align: center">작성자</th>
						<th style="background-color: skyblue; text-align: center">작성 일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${alllist}" var="alllist">
						<c:if test="${alllist.typeid == 'B'}">
						<tr>
							<td><a href="" onclick="read_popup(${alllist.seq})"><c:out value="${alllist.subject}" /></a></td>
							<td><c:out value="${alllist.name}" /></td>
							<td><c:out value="${alllist.regist_date}" /></td>
						</tr>
						</c:if>
					</c:forEach>
					<c:forEach items="${filelist}" var="filelist">
						<c:if test="${filelist.typeid == 'F'}">
						<tr>
							<td><a href="" onclick="read_popup(${filelist.seq})"><c:out value="${filelist.subject}" /></a></td>
							<td><c:out value="${filelist.name}" /></td>
							<td><c:out value="${filelist.regist_date}" /></td>
						</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<h1>설문 조사</h1>
			<table class="table table-hover"
				style="text-align: center; border: 2px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: skyblue; text-align: center">제목</th>
						<th style="background-color: skyblue; text-align: center">작성자</th>
						<th style="background-color: skyblue; text-align: center">작성 일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${alllist}" var="alllist">
						<c:if test="${alllist.typeid == 'S'}">
						<tr>
							<td><a href="/controller/survey/info?survey_seq=${alllist.seq}"><c:out value="${alllist.subject}" /></a></td>
							<td><c:out value="${alllist.name}" /></td>
							<td><c:out value="${alllist.regist_date}" /></td>
						</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<br>
			</form>
		</div>
	</div>
</body>
</html>