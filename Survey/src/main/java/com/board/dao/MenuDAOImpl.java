package com.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.MenuDTO;
import com.board.domain.SearchCriteria;
import com.board.domain.TotalDTO;

@Repository
public class MenuDAOImpl implements MenuDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MenuDTO> list(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.list", scri);
	}

	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("menuMapper.listCount", scri);
	}

	@Override
	public MenuDTO read(int menu_seq) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("menuMapper.read", menu_seq);
	}

	@Override
	public void write(MenuDTO menuDTO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("menuMapper.insert", menuDTO);
	}

	@Override
	public void delete(int menu_seq) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("menuMapper.delete", menu_seq);
	}

	@Override
	public List<MenuDTO> menuauthlist(){
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.menuauthlist");
	}

	@Override
	public List<MenuDTO> menuaccesslist(String auth) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.menuaccesslist", auth);
	}

	@Override
	public void menucount(Map map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("menuMapper.menucount", map);
	}

	@Override
	public List<Map> totalyear() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.totalyear");
	}

	@Override
	public List<Map> totalmonth() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.totalmonth");
	}

	@Override
	public List<Map> totalday() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.totalday");
	}

	@Override
	public List<Map> totalhour() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("menuMapper.totalhour");
	}



}
