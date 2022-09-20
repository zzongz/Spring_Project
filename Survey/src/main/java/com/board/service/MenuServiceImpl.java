package com.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.MenuDAO;
import com.board.domain.MenuDTO;
import com.board.domain.SearchCriteria;

@Service
public class MenuServiceImpl implements MenuService {

	@Inject
	private MenuDAO dao;
	
	// 메뉴 목록 조회
	@Override
	public List<MenuDTO> list(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(scri);
	}

	// 메뉴 목록 총합
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listCount(scri);
	}

	@Override
	public MenuDTO read(int menu_seq) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(menu_seq);
	}

	@Override
	public void write(MenuDTO menuDTO) throws Exception {
		// TODO Auto-generated method stub
		dao.write(menuDTO);
	}

	@Override
	public void delete(int menu_seq) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(menu_seq);
	}

	@Override
	public List<MenuDTO> menuauthlist(){
		// TODO Auto-generated method stub
		return dao.menuauthlist();
	}

	@Override
	public List<MenuDTO> menuaccesslist(String auth) {
		return dao.menuaccesslist(auth);
	}

	@Override
	public void menucount(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.menucount(map);
	}

	@Override
	public List<Map> totalyear() throws Exception {
		// TODO Auto-generated method stub
		return dao.totalyear();
	}

	@Override
	public List<Map> totalmonth() throws Exception {
		// TODO Auto-generated method stub
		return dao.totalmonth();
	}

	@Override
	public List<Map> totalday() throws Exception {
		// TODO Auto-generated method stub
		return dao.totalday();
	}

	@Override
	public List<Map> totalhour() throws Exception {
		// TODO Auto-generated method stub
		return dao.totalhour();
	}

}
