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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
	function write_popup(){
		var url = "writeView"
		var name = "write_popup";
		window.open(url, name, "width=1000, height=1000, toolbar=no, status=no, loaction=no, scrollbars=yes, menubar=no,resizable=yes, left=50, right=50");
	}
	
	function read_popup(menu_seq){
		var url = "readView?menu_seq="+menu_seq;
		var name = "read_popup";
		window.open(url, name, "width=1000, height=1000, toolbar=no, status=no, loaction=no, scrollbars=yes, menubar=no,resizable=yes, left=50, right=50");
	}	
</script>
</head>
<body>
	<div class="main_container">
		<div id="main">
			<form role="form" method="get">
				<table class="table table-hover"
					style="text-align: center; border: 2px solid #dddddd">
					<thead>
						<tr>
							<th style="background-color: skyblue; text-align: center">메뉴</th>
							<th style="background-color: skyblue; text-align: center">종류</th>
							<th style="background-color: skyblue; text-align: center">링크</th>
							<th style="background-color: skyblue; text-align: center">게시판 번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td style="display:none"><c:out value="${list.menu_seq}" /></td>
								<td><a href="" onclick="read_popup(${list.menu_seq})"><c:out value="${list.menu_name}" /></a></td>
								<td><c:out value="${list.menu_type}" /></td>
								<td><c:out value="${list.menu_link}" /></td>
								<td><c:out value="${list.board_seq}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
						<button type="button" onclick="write_popup()" class="btn btn-default">메뉴 등록</button>
				</div>
			</form>
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
</body>
</html>