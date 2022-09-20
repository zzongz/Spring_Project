package com.board.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.MemberDTO;
import com.board.service.MemberService;

@Controller
@RequestMapping(value = "member/*")
public class MemberController {
	
	@Inject
	MemberService service;
	
	// 회원가입 GET
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception{
		
	}
	
	// 회원가입 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberDTO memberDTO) throws Exception{
		
		int result = service.idchk(memberDTO);
		
		try {
			if(result == 1) {
				return "/member/register";
			}else if(result == 0) {
				service.register(memberDTO);
			}
		}catch(Exception e) {
			throw new RuntimeException();
		}
		
		return "redirect:/member/loginView";
	}
	
	//로그인 화면
	@RequestMapping(value = "/loginView", method = RequestMethod.GET)
	public String loginview(Locale locale, Model model) {
		
		return "home";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO dto, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		
		HttpSession session = req.getSession();
		MemberDTO login = service.login(dto);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {
			session.setAttribute("member", login);
			MemberDTO aa = (MemberDTO) session.getAttribute("member");
			System.out.println("login controller : " + aa.getAuth());
		}
		System.out.println(login.toString());
		return "redirect:/member/loginView";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/member/loginView";
	}
	
	// 아이디 중복 체크
	@RequestMapping(value = "/idchk", method = RequestMethod.POST)
	@ResponseBody
	public int idchk(MemberDTO memberDTO) throws Exception{
		
		int result = service.idchk(memberDTO);
		return result;
	}
	
}