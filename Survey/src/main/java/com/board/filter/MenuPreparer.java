package com.board.filter;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Controller;

import com.board.domain.MenuDTO;
import com.board.service.MenuService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MenuPreparer implements ViewPreparer {
 
	@Inject
	private MenuService menuservice;
	
    @Override
    public void execute(Request request, AttributeContext attributeContext) throws PreparerException {
    	
    	List<MenuDTO> menuList = menuservice.menuauthlist();
    	for(MenuDTO menu : menuList) {
    		System.out.println(menu.getMenu_name());
    	}
        attributeContext.putAttribute("menuList", new Attribute(menuList), true);
    }
 
}
