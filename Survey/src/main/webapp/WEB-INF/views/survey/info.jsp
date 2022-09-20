<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 안내문</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

	var strDate1 = '${info.startdate}';
	var strDate2 = '${info.enddate}';

	var arr1 = strDate1.split('/');
	var arr2 = strDate2.split('/');
	var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
	var dat2 = new Date(arr2[0], arr2[1], arr2[2]);
	
	var diff = dat2 - dat1;
	var currDay = 24 * 60 * 60 * 1000;
	var day = parseInt(diff/currDay) + 1;

	$(document).ready(function(){
		$("#datecount").text(day);
		
		$("#content_start_btn").on("click", function(){
			location.href="/controller/survey/content?survey_seq=${info.survey_seq}";
		})
	})
	
</script>
</head>
<body style="justify-content: center; display: flex; font-size:30px;">
	<div id = "info_main" style="border: 1px solid gray;">
		<div id="info_top">
			<p>퓨전소프트 회원분들을 대상으로 만족도 조사를 진행합니다.</p>
			<p>응답하신 내용은 통계법 33조(비밀의 보호)에 의거 비밀이 보장 되며,</p>
			<p>서비스 개선을 위한 자료 외에 어떠한 목적으로도 사용되지 않음을</p>
			<p>약속드립니다.많은 참여 부탁드리며, 앞으로도 교육정책 및 교육과정</p>
			<p>정보를 보다 빠르게 활용하실 수 있도록 더욱 노력하겠습니다.</p>
		</div>
		<br>
		<div id="info_mid">
	        <ul>
	            <li>조사 주관   : ${info.host}</li>
	            <li>참여 대상   : ${info.target}</li>
	            <li>참여 기간   : ${info.startdate} ~ ${info.enddate}, 총 <span id="datecount"></span>일간</li>
	            <li>참여 방법   : 하단의 설문시작 버튼을 클릭하여 총 ${info.question_count}개의 문항에 답변을 마치면 응모완료</li> 
	            <li>당첨자 발표 : ${info.awarddate}, 퓨전소프트 공지사항 게시판</li>
	        </ul> 
        </div>
        <br>
		<div id="info_bottom">
			<p>* 유의사항</p>
			<p>- 당첨자 선정은 응답 내용의 성실성 등을 고려하여 선정됩니다.</p>
			<p>- 1인 1회 한하여 참여가능 합니다.</p>
		</div>
		<br>
		<div id="info_button" style="text-align:center;">
			<button type="button" id="content_start_btn" class="btn btn-primary">설문 시작</button>
		</div>
	</div>
</body>
</html>