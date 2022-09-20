<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
	li{float:left; padding:6px;}
</style>
<script>

	// 응답 저장
	var survey_seq = ${questionlist[0].survey_seq};
	$(document).ready(function(){
	
	// 체크 박스
   	$(".checkboxClass").on("click", function(){
		var answer = $(this).val();
    	var question_seq = $(this).closest(".divItem").find(".question_seq").val();
    	var seltype = $("#q_seltype_" + question_seq).val();
    	var question_type = $("#q_questiontype_" + question_seq).val();
    	var ajaxrun = true;
    	
    	if(seltype == '1'){
    		$("#" + question_seq +' .checkboxClass').prop('checked',false);
    		$(this).prop('checked',true);
    	}
    	else if(seltype == '2'){
    		var checkcount = $("#" + question_seq +' .checkboxClass:checked').length;
    		
    		if(checkcount > 2){
    			$(this).prop('checked', false);
    			alert("2개를 선택하셨습니다.");
    			ajaxrun = false;
    		}
    	}
	
    	var flag = $(this).is(":checked");
    	
    	if(ajaxrun){
    	 $.ajax({
            url : "/controller/survey/answerInsert",
            type: "POST",
            data : {
            	"answer" : answer,
            	"survey_seq" : survey_seq,
            	"question_seq" : question_seq,
            	"seltype" : seltype,
            	"flag"		: flag
            },
            success : function(data){
            	if(data == "insert") {
            		if(question_type != '자율'){
	            		if(seltype==2){
	            			if($("#" + question_seq +' .checkboxClass:checked').length == 2){
	            				var barg = $('#bar').val();
	    	            		$('#bar').val(barg + 1);
	            			}
	            		}else{
		            		var barg = $('#bar').val();
		            		$('#bar').val(barg + 1);
	            		}
            		}
            	}
            	else if(data == "update"){
            	}
            	else if(data == "delete"){
            		if(question_type != '자율'){
	            		if($("#" + question_seq +' .checkboxClass:checked').length == 0){
	        				var barg = $('#bar').val();
		            		$('#bar').val(barg - 1);
	        			}
            		}
            	}
            	else if(data == "fail"){
            		alert("실패");
            	}
            	else{
            	}
            }
        });
    	}
    });
    
   	// 서술형
    $("#word").keyup(function(){
		var answer = $(this).val();
    	var question_seq = $(this).closest(".divItem").find(".question_seq").val();
    	var question_type = $("#q_questiontype_" + question_seq).val();
    	var bef_answer_leng = $(this).next();
    	
    	var flag = false;
    	
    	if(answer.length > 0){
    		 flag = true;
    	}else{
    		 flag = false;
    	}
    	if((Number(bef_answer_leng.val()) > 0) || (answer.length > 0)){
		   	 $.ajax({
		         url : "/controller/survey/answerInsert",
		         type: "POST",
		         data : {
		         	"answer" : answer,
		         	"survey_seq" : survey_seq,
		         	"question_seq" : question_seq,
	            	"flag"		: flag
		         },
		         success:function(data){
		        	 if(data == "insert"){
		        		 if(question_type != '자율'){
		        			 if(answer.length > 0){
		         				var barg = $('#bar').val();
			            		$('#bar').val(barg + 1);
		        			 }
		        		 }
		        	 }
		        	 else if(data == "delete"){
		        		 if(question_type != '자율'){
			            	if(answer.length == 0){
			        			var barg = $('#bar').val();
				            	$('#bar').val(barg - 1);
			            	}
		        		 }
		        	 }
					bef_answer_leng.val(answer.length);
		         }
	   		 });
    	}
    });
    
});

// 설문 종료(저장)
function finish() {
	 $.ajax({
         url : "/controller/survey/finish",
         type: "POST",
         data : {
         	"survey_seq" : survey_seq
         },
	 	success:function(data){
	 		if(data == "success"){
	 			location.href="/controller/survey/list";
	 		}else{
	 			
	 		}
	 	}
	});
}
</script>
</head>
<body>
<div>
<progress id="bar" value="${answerCnt}" max="${questionCnt}" style="width:600px; height:50px;"></progress><br>
</div><br>
	<div id="content_main">
		<c:forEach items="${questionlist}" var="questionlist">
			<div style="font-size:25px;" id="${questionlist.question_seq}" class="divItem">
				<input type="hidden" id="displayQuestionNumber_${questionlist.question_seq}" value="${questionlist.content }">
				<input type="hidden" id="q_seltype_${questionlist.question_seq}" value="${questionlist.seltype}">
				<input type="hidden" id="q_questiontype_${questionlist.question_seq}" value="${questionlist.question_type}">
				<span style="font-weight:bold;">${questionlist.title}</span><span> ${questionlist.content}</span><br>
				<input class="survey_seq" type="hidden" value="${questionlist.survey_seq}">
				<input class="question_seq" type="hidden" value="${questionlist.question_seq}">
				<c:choose>
					<c:when test="${questionlist.seltype != 3}">
						<c:forEach items="${answerlist}" var="answerlist">
							<c:if test="${answerlist.question_seq eq questionlist.question_seq }">
								<input type="checkbox" name="checkboxTest_${questionlist.question_seq }" class="checkboxClass" value="${answerlist.answer_seq}" 
									<c:forEach items="${fn:split(questionlist.answer,',')}" var="splitAnswer">
										<c:if test="${splitAnswer eq answerlist.answer_seq}">checked</c:if> 
									</c:forEach>>${answerlist.content}
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<textarea id="word" rows="" cols="" maxlength="250">${questionlist.answer}</textarea>
						<input type="hidden" class="bef_answer_leng" value='${fn:length(questionlist.answer)}'>
					</c:otherwise>
				</c:choose>
				
			</div>
			<br><br>
		</c:forEach>
		<div id="bottom">
			<ul class="pagination">
				<c:choose>
					<c:when test="${pageMaker.cri.page != pageMaker.startPage}">
						<li><a href="content${pageMaker.makeQuery(pageMaker.cri.page - 1)}&survey_seq=${questionlist[0].survey_seq}">이전</a></li>
					</c:when>
				</c:choose>
					
				<c:choose>
					<c:when test="${pageMaker.cri.page == pageMaker.endPage}"> 
						<li><a href="" onclick="finish()">완료</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="content${pageMaker.makeQuery(pageMaker.cri.page + 1)}&survey_seq=${questionlist[0].survey_seq}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</body>
</html>