package com.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.Criteria;
import com.board.domain.MemberDTO;
import com.board.domain.PageMaker;
import com.board.domain.SurveyAnswerDTO;
import com.board.domain.SurveyListDTO;
import com.board.domain.SurveyQuestionDTO;
import com.board.domain.UserAnswerDTO;
import com.board.service.SurveyAnswerService;
import com.board.service.SurveyFinishService;
import com.board.service.SurveyListService;
import com.board.service.SurveyQuestionService;
import com.board.service.UserAnswerService;

@Controller
@RequestMapping(value = "survey/*")
public class SurveyController {

	@Inject
	private SurveyListService service;
	@Inject
	private SurveyQuestionService questionservice;
	@Inject
	private SurveyAnswerService answerservice;
	@Inject
	private UserAnswerService useranswerservice;
	@Inject
	private SurveyFinishService surveyfinishservice;

	// 설문 목록 출력
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, SurveyListDTO surveylistDTO, @RequestParam(value="error", defaultValue = "1") String error) throws Exception {
		
		model.addAttribute("list", service.list());
		model.addAttribute("error", error);

		return "survey/list";
	}
	
	// 설문 안내문 출력
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Model model, SurveyListDTO surveylistDTO, HttpServletRequest req) throws Exception{
		
		HttpSession session = req.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		Map<String, Object> checkmap = new HashMap<String, Object>();
		checkmap.put("userid", memberDTO.getUserId());
		checkmap.put("survey_seq", surveylistDTO.getSurvey_seq());
		
		if(useranswerservice.checkCnt(checkmap) <= 0) {
			model.addAttribute("info", service.info(surveylistDTO.getSurvey_seq()));
			
			return "survey/info";
		}else {
			
			model.addAttribute("error", "no");
			
			return "redirect:/survey/list";
		}
			
	}
	
	// 설문지 문항 출력
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(Model model, SurveyListDTO surveylistDTO, Criteria cri, HttpServletRequest req) throws Exception{
		
		HttpSession session = req.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		Map<String, Object> conmap = new HashMap<String, Object>();
		conmap.put("survey_seq", surveylistDTO.getSurvey_seq());
		conmap.put("cri", cri);
		conmap.put("userid", memberDTO.getUserId());
		
		Map<String, Object> cntmap = new HashMap<String, Object>();
		cntmap.put("survey_seq", surveylistDTO.getSurvey_seq());
		cntmap.put("userid", memberDTO.getUserId());
		
		Map<String, Object> finishmap = new HashMap<String, Object>();
		finishmap.put("userid", memberDTO.getUserId());
		finishmap.put("survey_seq", surveylistDTO.getSurvey_seq());
		
		model.addAttribute("questionCnt", questionservice.questionCnt(cntmap));
		model.addAttribute("answerCnt", useranswerservice.answerCnt(cntmap));
		model.addAttribute("questionlist", questionservice.list(conmap));
		model.addAttribute("answerlist", answerservice.list(surveylistDTO.getSurvey_seq()));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(questionservice.listCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "survey/content";
	}
	
	// 사용자 답변 저장
	@RequestMapping(value = "/answerInsert", method = RequestMethod.POST)
	@ResponseBody
	public String answerInsert(Model model, SurveyAnswerDTO surveyanswerDTO, HttpServletRequest req, UserAnswerDTO useranswerDTO, SurveyQuestionDTO surveyquestionDTO) throws Exception{
		
		HttpSession session = req.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
			
		Map<String, Object> answermap = new HashMap<String, Object>();
		answermap.put("userid", memberDTO.getUserId());
		answermap.put("survey_seq", surveyanswerDTO.getSurvey_seq());
		answermap.put("question_seq", surveyanswerDTO.getQuestion_seq());
		answermap.put("answer", useranswerDTO.getAnswer());
		answermap.put("seltype", surveyquestionDTO.getSeltype());
		
		int list1 = useranswerservice.list(answermap);
		
		try {
			if(surveyanswerDTO.isFlag() == false) {
				useranswerservice.delete(answermap);
				return "delete";
			} else {
				if(list1 > 0) {
					useranswerservice.update(answermap);
					return "update";
				}
				else {
					useranswerservice.write(answermap);
					return "insert";
				}
			}
		}catch(Exception e) {
				return "fail";
		}
	}
	
	// 설문 종료
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	@ResponseBody
	public String finish(Model model, SurveyListDTO surveylistDTO, Criteria cri, HttpServletRequest req) throws Exception{
		
		HttpSession session = req.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		Map<String, Object> finishmap = new HashMap<String, Object>();
		finishmap.put("userid", memberDTO.getUserId());
		finishmap.put("survey_seq", surveylistDTO.getSurvey_seq());
		
		try {
			if(true) {
				surveyfinishservice.write(finishmap);
				return "success";
			}
			else {
				return "fail";
			}
		}catch(Exception e) {
				return "fail";
		}
	}
}