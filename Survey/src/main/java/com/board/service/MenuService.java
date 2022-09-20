package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.domain.MenuDTO;
import com.board.domain.SearchCriteria;

public interface MenuService {

	// 메뉴 목록 조회
	public List<MenuDTO> list(SearchCriteria scri) throws Exception;
	
	// 메뉴 목록 총합
	public int listCount(SearchCriteria scri) throws Exception;
	
	// 메뉴 상세 조회
	public MenuDTO read(int menu_seq) throws Exception;
	
	// 메뉴 등록
	public void write(MenuDTO menuDTO) throws Exception;
	
	public void delete(int menu_seq) throws Exception;
	
	public List<MenuDTO> menuauthlist();
	
	public List<MenuDTO> menuaccesslist(String auth);
	
	public void menucount(Map map) throws Exception;
	
	public List<Map> totalyear() throws Exception;
	
	public List<Map> totalmonth() throws Exception;
	
	public List<Map> totalday() throws Exception;
	
	public List<Map> totalhour() throws Exception;
}
