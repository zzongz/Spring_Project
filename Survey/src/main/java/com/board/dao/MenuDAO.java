package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.MenuDTO;
import com.board.domain.SearchCriteria;
import com.board.domain.TotalDTO;

public interface MenuDAO {

	public List<MenuDTO> list(SearchCriteria scri) throws Exception;
	
	public int listCount(SearchCriteria scri) throws Exception;
	
	public MenuDTO read(int menu_seq) throws Exception;
	
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
