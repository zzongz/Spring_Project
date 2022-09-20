<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/controller/resources/css/list.css" rel="stylesheet" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	function fncXhdrP(type, param) {
		$.ajax({
            url : "/controller/menu/totallist",
            type: "POST",
            data : {
            	"type" : type
            },
            success : function(data){
            	var keyList = data.keyList;
            	var totalList = data.totalList;
            	
            	var inputHeadHtml = '<tr>';
            	for(var i = 0; i < keyList.length; i++) {
            		inputHeadHtml += '<th>' + keyList[i] + '</th>';
            	}
            	inputHeadHtml += '</tr>';
            	
            	$("#theadResult").html(inputHeadHtml);
            	
            	var inputBodyHtml = '';
            	
            	for(var i = 0; i < totalList.length; i++) {
            		inputBodyHtml += '<tr>';
                	for(var j = 0; j < keyList.length; j++) {
                		inputBodyHtml += '<td>' + totalList[i][keyList[j]] + '</td>';
                	}
                	inputBodyHtml += '</tr>';
            	}
            	
            	$("#tbodyResult").html(inputBodyHtml);
            	
            	console.log(keyList);
            	console.log(totalList);
            }
        });
	}
</script>
</head>
<body>
	<input type="button" value="연도별" onclick="fncXhdrP('year', '');">
	<input type="button" value="월별" onclick="fncXhdrP('month', '');">
	<input type="button" value="일별" onclick="fncXhdrP('day', '');">
	<input type="button" value="시간별" onclick="fncXhdrP('hour', '');">
	
	<table class="table table-hover"
					style="text-align: left; border: 2px solid #dddddd">
		<thead id="theadResult">
		</thead>
		<tbody id="tbodyResult">
		</tbody>
	</table>
</body>
</html>