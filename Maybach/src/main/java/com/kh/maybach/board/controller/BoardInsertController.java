package com.kh.maybach.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.maybach.board.model.dto.BoardDTO;
import com.kh.maybach.board.model.service.BoardService;
import com.kh.maybach.member.model.dto.MemberDTO;


@WebServlet("/insert.board")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardInsertController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		// 로그인 검증
		if(session.getAttribute("loginMember") == null) {
			session.setAttribute("message", "로그인 해주세요!");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String memberId = ((MemberDTO)session.getAttribute("loginMember")).getMemberId();
		
		BoardDTO board = new BoardDTO();
		board.setBoardCategory(request.getParameter("category"));
		board.setBoardTitle(request.getParameter("title"));
		board.setBoardContent(request.getParameter("content"));
		board.setBoardWriter(memberId);
		
		new BoardService().insertBoard(board);
		session.setAttribute("message", "게시글 작성에 성공했습니다!");
		response.sendRedirect(request.getContextPath() + "/boards?page=1");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
