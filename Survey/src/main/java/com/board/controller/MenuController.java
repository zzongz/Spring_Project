package com.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.MemberDTO;
import com.board.domain.MenuDTO;
import com.board.domain.PageMaker;
import com.board.domain.SearchCriteria;
import com.board.domain.TotalDTO;
import com.board.service.MenuService;

@Controller
@RequestMapping(value = "menu/*")
public class MenuController {

	@Inject
	private MenuService menuservice;

	// 메뉴관리 목록 화면
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, @ModelAttribute("scri") SearchCriteria scri, MenuDTO menuDTO) throws Exception {

		model.addAttribute("list", menuservice.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(menuservice.listCount(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/list";
	}
	
	// 메뉴 상세 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(Model model, MenuDTO menuDTO, MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		model.addAttribute("read", menuservice.read(menuDTO.getMenu_seq()));
		model.addAttribute("menu_seq", menuDTO.getMenu_seq());
	
		return "admin/readView";
	}
	
	// 메뉴관리 등록 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public String writeView() throws Exception {

		return "admin/writeView";
	}

	// 메뉴관리 등록
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(MenuDTO menuDTO) throws Exception {
		
		menuservice.write(menuDTO);

		return "admin/writeView";
	}

	// 메뉴 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(MenuDTO menuDTO) throws Exception {

		menuservice.delete(menuDTO.getMenu_seq());

		return "admin/readView";
	}
	
	// 메뉴 조회 등록
	@RequestMapping(value = "/menucount", method = RequestMethod.POST)
	@ResponseBody
	public void menucount(Model model, MenuDTO menuDTO) throws Exception {
		
		Map<String, Object> menumap = new HashMap<String, Object>();
		menumap.put("menu_seq", menuDTO.getMenu_seq());
		menumap.put("menu_name", menuDTO.getMenu_name());
		
		menuservice.menucount(menumap);
	}
	
	// 메뉴 삭제
	@RequestMapping(value = "/totallist", method = RequestMethod.GET)
	public String totallist(MenuDTO menuDTO) throws Exception {

		return "admin/totallist";
	}
	
	@RequestMapping(value = "/totallist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> totallist(Model model, MenuDTO menuDTO) throws Exception {
		
		List totalList = null;
		
		if("year".equals(menuDTO.getType())) {
			totalList = menuservice.totalyear();
		} else if("month".equals(menuDTO.getType())) {
			totalList = menuservice.totalmonth();
		} else if("day".equals(menuDTO.getType())) {
			totalList = menuservice.totalday();
		} else if("hour".equals(menuDTO.getType())) {
			totalList = menuservice.totalhour();
		}
		
		List keyList = null;
		keyList = new ArrayList(((Map<String, Object>) totalList.get(0)).keySet()); 
			
		Map<String, Object> totalMapList = new HashMap<String, Object>();
		totalMapList.put("keyList", keyList);
		totalMapList.put("totalList", totalList);
			
		return totalMapList;
	}
}