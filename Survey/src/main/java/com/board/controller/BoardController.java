package com.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardDTO;
import com.board.domain.MemberDTO;
import com.board.domain.PageMaker;
import com.board.domain.ReplyDTO;
import com.board.domain.SearchCriteria;
import com.board.service.BoardService;
import com.board.service.LikeService;
import com.board.service.ReplyService;

@Controller
@RequestMapping(value = "board/*")
public class BoardController {

	@Inject
	private BoardService service;

	@Inject
	private ReplyService replyService;
	
	@Inject
	private LikeService likeservice;

	// 게시판 목록 화면
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, @ModelAttribute("scri") SearchCriteria scri, BoardDTO boardDTO) throws Exception {

		model.addAttribute("list", service.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		List<Map<String, Object>> fileList = service.selectFileList(boardDTO.getSeq());
		model.addAttribute("file", fileList);
		
		return "board/list";
	}
	
	// 제목,작성자 검색 목록
	@RequestMapping(value = "/alllist", method = RequestMethod.GET)
	public String alllist(Locale locale, Model model, @ModelAttribute("scri") SearchCriteria scri, BoardDTO boardDTO) throws Exception {
		if(scri.getSearchType().equals("w")) {
			model.addAttribute("filelist", service.filelist(scri));
		}else {
			model.addAttribute("alllist", service.alllist(scri));
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "search/list";
	}
	
	/*
	 * // 제목,작성자 검색 목록
	 * 
	 * @RequestMapping(value = "/filelist", method = RequestMethod.GET) public
	 * String file(Locale locale, Model model, @ModelAttribute("scri")
	 * SearchCriteria scri, BoardDTO boardDTO) throws Exception {
	 * 
	 * model.addAttribute("filelist", service.filelist(scri));
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setCri(scri);
	 * pageMaker.setTotalCount(service.listCount(scri));
	 * 
	 * model.addAttribute("pageMaker", pageMaker);
	 * 
	 * return "search/list"; }
	 */
	
	// 게시판 글 작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public String writeView() throws Exception {

		return "board/writeView";
	}

	// 게시판 글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public String write(BoardDTO boardDTO, MultipartHttpServletRequest bRequest) throws Exception {
		
		service.write(boardDTO, bRequest);

		return "insert";
	}
	
	@RequestMapping(value = "/answerwriteView", method = RequestMethod.GET)
	public String answerwriteView(Locale locale, Model model, @RequestParam("seq")int seq) throws Exception {
		
		model.addAttribute("seq", seq);
		
		return "board/answerwriteView";
	}

	// 게시판 글 작성
	@RequestMapping(value = "/answerwrite", method = RequestMethod.POST)
	public String answerwrite(Locale locale, Model model, BoardDTO boardDTO, @RequestParam("seq")int seq) throws Exception {
		
		boardDTO.setParentId(seq);
		service.answerwrite(boardDTO);
		
		return "board/answerwriteView";
	}

	// 게시판 글 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(Model model, BoardDTO boardDTO, MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
	
		model.addAttribute("read", service.read(boardDTO.getSeq()));
		int  seq = boardDTO.getSeq();
		model.addAttribute("seq", seq);

		List<ReplyDTO> replyList = replyService.readReply(boardDTO.getSeq());
		model.addAttribute("replyList", replyList);
		
		List<Map<String, Object>> fileList = service.selectFileList(boardDTO.getSeq());
		model.addAttribute("file", fileList);

		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		MemberDTO tempMemberDTO = (MemberDTO) session.getAttribute("member");
		memberDTO.setUserId(tempMemberDTO.getUserId());
		
		// 좋아요 확인
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", memberDTO.getUserId());
		map.put("board_seq", boardDTO.getSeq());
		int likeChk = likeservice.liketest(map);
		
		model.addAttribute("likeChk", likeChk);
		
		// 쿠키가 있을 경우
		if(cookies != null && cookies.length > 0)
		{
			for(int i=0; i<cookies.length; i++)
			{
				if(cookies[i].getName().equals("cookie_"+boardDTO.getSeq() + "_" + memberDTO.getUserId()) && cookies[i].getValue().equals(memberDTO.getUserId()))
				{
					viewCookie = cookies[i];
				}
			}
		}
		
		if(viewCookie == null) {
			Cookie newCookie = new Cookie("cookie_" +boardDTO.getSeq() + "_" + memberDTO.getUserId(), memberDTO.getUserId());
			response.addCookie(newCookie);
			service.boardHit(boardDTO.getSeq());
		}
		else {
			String value = viewCookie.getValue();
		}
		
		return "board/readView";
	}

	// 게시판 글 수정 화면
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(Model model, BoardDTO boardDTO) throws Exception {

		model.addAttribute("update", service.read(boardDTO.getSeq()));
		
		List<Map<String, Object>> fileList = service.selectFileList(boardDTO.getSeq());
		model.addAttribute("file", fileList);
		
		return "board/updateView";
	}

	// 게시판 글 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Model model, BoardDTO boardDTO,
						@RequestParam(value = "fileNoDel[]") String[] files,
						@RequestParam(value = "fileNameDel[]") String[] fileNames,
						MultipartHttpServletRequest bRequest) throws Exception {

		service.update(boardDTO, files, fileNames, bRequest);
		
		return "success";
	}

	// 게시판 글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam ("seq") int seq) throws Exception {
		
		service.delete(seq);

		return "delete";
	}

	// 댓글 작성
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(Model model, BoardDTO boardDTO, ReplyDTO dto, RedirectAttributes rttr)
			throws Exception {

		replyService.writeReply(dto);

		rttr.addAttribute("seq", dto.getSeq());

		model.addAttribute("read", service.read(boardDTO.getSeq()));
		List<ReplyDTO> replyList = replyService.readReply(boardDTO.getSeq());
		model.addAttribute("replyList", replyList);

		return "board/readView";
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/replyUpdateView", method = RequestMethod.GET)
	public String replyUpdateView(ReplyDTO dto, SearchCriteria scri, Model model) throws Exception {

		model.addAttribute("replyUpdate", replyService.selectReply(dto.getRseq()));
		model.addAttribute("scri", scri);

		return "board/replyUpdateView";
	}

	// 댓글 수정 POST
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(Model model, BoardDTO boardDTO,ReplyDTO dto, SearchCriteria scri, RedirectAttributes rttr) throws Exception {

		replyService.updateReply(dto);
		rttr.addAttribute("seq", dto.getSeq());

		model.addAttribute("read", service.read(boardDTO.getSeq()));

		List<ReplyDTO> replyList = replyService.readReply(boardDTO.getSeq());
		model.addAttribute("replyList", replyList);
		
		return "board/readView";
	}

	// 댓글 삭제 GET
	@RequestMapping(value = "/replyDeleteView", method = RequestMethod.GET)
	public String replyDeleteView(ReplyDTO dto, SearchCriteria scri, Model model) throws Exception {

		model.addAttribute("replyDelete", replyService.selectReply(dto.getRseq()));
		model.addAttribute("scri", scri);

		return "board/replyDeleteView";
	}

	// 댓글 삭제
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	public String replyDelete(Model model, BoardDTO boardDTO, ReplyDTO dto, SearchCriteria scri, RedirectAttributes rttr) throws Exception {

		replyService.deleteReply(dto);

		rttr.addAttribute("seq", dto.getSeq());
		
		model.addAttribute("read", service.read(boardDTO.getSeq()));

		List<ReplyDTO> replyList = replyService.readReply(boardDTO.getSeq());
		model.addAttribute("replyList", replyList);

		return "board/readView";
	}
	
	// 첨부 파일 다운로드
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response, @RequestParam("seq")int seqq, @RequestParam("filename2")String filename2) throws Exception{
		Map map2 = new HashMap();
		map2.put("SEQ", seqq);
		map2.put("ORG_FILE_NAME", filename2);
		
		Map<String, Object> resultMap = service.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\board\\img\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		service.downloadHit(map2);
	}
	
	// 좋아요 등록
	@RequestMapping(value = "/likeinsert")
	@ResponseBody
	public void likeinsert(HttpServletRequest req, Model model, BoardDTO boardDTO) throws Exception{

		HttpSession session = req.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		Map<String, Object> likemap = new HashMap<String, Object>();
		likemap.put("userid", memberDTO.getUserId());
		likemap.put("board_seq", boardDTO.getSeq());
	
		likeservice.likeinsert(likemap);
	}
	
	// 좋아요 취소
	@RequestMapping(value = "/likedelete")
	@ResponseBody
	public void likedelete(HttpServletRequest req, Model model, BoardDTO boardDTO) throws Exception{

		HttpSession session = req.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		Map<String, Object> likemap = new HashMap<String, Object>();
		likemap.put("userid", memberDTO.getUserId());
		likemap.put("board_seq", boardDTO.getSeq());
	
		likeservice.likedelete(likemap);
	}
	
	
}