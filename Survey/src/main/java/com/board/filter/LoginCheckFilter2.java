package com.board.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import com.board.domain.MemberDTO;
import com.board.domain.MenuDTO;
import com.board.service.MenuService;

@Service("LoginCheckFilter2")
public class LoginCheckFilter2 implements Filter {

	@Inject
	private MenuService menuservice;
	
	// 기본 접근 가능 URI
	private static final String[] unCheckList = { "/"
	                                             ,"/resources/*"
	                                             ,"/member/loginView"
	                                             ,"/member/login"
	                                             ,"/member/logout"
	                                             ,"/member/register"
	                                             ,"/member/idchk"
	                                             ,"/board/readView"
	                                             ,"/board/updateView"
	                                             ,"/board/update"
	                                             ,"/board/writeView"
	                                             ,"/board/write"
	                                             ,"/board/delete"
	                                             ,"/board/fileDown"
	                                             ,"/board/answerwriteView"
	                                             ,"/board/answerwrite"
	                                             ,"/board/replyWrite"
	                                             ,"/board/replyUpdateView"
	                                             ,"/board/replyUpdate"
	                                             ,"/board/replyDelete"
	                                             ,"/board/replyDeleteView"
	                                             ,"/board/likeinsert"
	                                             ,"/board/likedelete"
	                                             ,"/survey/info"
	                                             ,"/survey/content"
	                                             ,"/survey/answerInsert"
	                                             ,"/survey/finish"
	                                             ,"/menu/readView"
	                                             ,"/menu/writeView"
	                                             ,"/menu/write"
	                                             ,"/menu/delete"
	                                             ,"/board/alllist"
	                                             ,"/menu/menucount"
	                                             ,"/menu/totallist"
	                                             };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		System.out.println("@@@@@ DO FILTER @@@@@");

		
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    // URI 자르기
	    String requestURI = httpRequest.getRequestURI(); // /insert.do
	    String requestContextPath = httpRequest.getContextPath();
	    String finalURI = requestURI.substring(requestContextPath.length());
	    
	    System.out.println("requestURI : " + requestURI);
	    System.out.println("requestContextPath : " + requestContextPath);
	    System.out.println("finalURI : " + finalURI);
	    
	    HttpSession session = httpRequest.getSession(false);
	    
	    String auth = "";
	    
	    MemberDTO memberDTO = null;
	    
	    if(session != null) {
	    	memberDTO = (MemberDTO) session.getAttribute("member");	    	
	    }else {
	    	System.out.println("세션 값 없음.");
	    }
	    if(memberDTO == null) {
	    	System.out.println("세션 값 없음.");
	    	auth = "비회원";
	    }else {
	    	memberDTO = (MemberDTO) session.getAttribute("member");
	    	
	    	try {
	        	auth = memberDTO.getAuth();
	        	
	        	 if (memberDTO.getAuth() == "U") {
	 	            auth = "U";
	 	        } else if (memberDTO.getAuth() == "A") {
	 	            auth = "A";
	 	        }
	 	        
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("최종 권한 : " + auth);
	    
	    // 회원의 권한에 해당하는 menuList 가져오기
	    List<MenuDTO> menuList = menuservice.menuaccesslist(auth);
	    
	    // 권한 초과 검사
	    try {
	        System.out.println("필터 실행" + finalURI);// 접근 URI가 권한을 넘어설 시 URI에 접근하게

	        if (isAuthCheckPath(finalURI)) { // 고정 uri가 매칭안된다면
	            if (isAuthCheckPath(menuList, finalURI)) { // 그리고 권한을 넘어선다면
	                System.out.println("권한 초과 url 접근");
	                System.out.println("finalURI : " + finalURI);
	                if (auth.equals("비회원")) {
	                    System.out.println("비회원 사용자 권한 초과"+ finalURI);
	                    System.out.println("로그인 페이지로 이동 "+finalURI);
	                    httpResponse.setContentType("text/html; charset=EUC-KR");
	                    PrintWriter out = httpResponse.getWriter();
	                    out.println("<script>");
	                    out.println("window.alert('비회원은 이용할 수 없습니다. 로그인 해주세요')");
	                    out.println("window.location.href='/controller/member/loginView?redirectURL=" + finalURI +"'");
	                    out.println("</script>");
	                    return;
	                } else {
	                    System.out.println("회원 사용자 권한 초과");
	                    System.out.println("이전페이지로 이동");
	                    httpResponse.setContentType("text/html; charset=EUC-KR");
	                    PrintWriter out = httpResponse.getWriter();
	                    String oldUrl =httpRequest.getHeader("Referer");
	                    
	                    if(oldUrl == null) {
	                        oldUrl = "home";
	                        out.println("<script>");
	                        out.println("window.alert('잘못된 접근입니다.')");
	                        out.println("window.location.href='"+oldUrl+"'");
	                        out.println("</script>");
	                    } else {
	                        out.println("<script>");
	                        out.println("window.alert('이용권한이 없습니다.')");
	                        out.println("window.location.href='"+oldUrl+"'");
	                        out.println("</script>");
	                    }
	                    return;
	                }
	            }
	        }

	        chain.doFilter(request, response);
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        System.out.println("필터 종료"+ finalURI);
	    }

	}
	
	//URI체크 메소드
	private boolean isAuthCheckPath(String finalURI) {
	        System.out.println("고정 uri체크" + !PatternMatchUtils.simpleMatch(unCheckList, finalURI));
	        return !PatternMatchUtils.simpleMatch(unCheckList, finalURI);
	    }

	private boolean isAuthCheckPath(List<MenuDTO> list, String finalURI) {
	    System.out.println("list:" + list);
	    // 메뉴 리스트의 URI들을 String 배열로 변환 후 접근URI와 비교
	    List<String> authURIList = new ArrayList<String>();

	    int arrListSize = list.size();
	    
	    for (MenuDTO dto : list) {
	        authURIList.add(dto.getMenu_link());
	    }
	    System.out.println("authURIList : " + authURIList.size());

	    String[] checkList = authURIList.toArray(new String[arrListSize]);
	    
	    for (String checkLis1t : checkList) {
	        System.out.println("checkLis1t" + checkLis1t);
	    }
	    
	    System.out.println("simpleMatch:" + !PatternMatchUtils.simpleMatch(checkList, finalURI));
	    return !PatternMatchUtils.simpleMatch(checkList, finalURI);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	    // TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
	    // TODO Auto-generated method stub
	}
}