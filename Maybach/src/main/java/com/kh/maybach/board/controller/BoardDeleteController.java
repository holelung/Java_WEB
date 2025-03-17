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


@WebServlet("/delete.board")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public BoardDeleteController() {
        super();
  
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 자 게시글을 삭제해 볼까요
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		HttpSession session = request.getSession();
		// 로그인 안했으면 꺼져!
		if(session.getAttribute("loginMember")==null) {
			session.setAttribute("message", "ㅋㅋ로그인이나 하삼");
			response.sendRedirect("board?boardNo="+boardNo);
			return;
		}
		
		String memberId = ((MemberDTO)session.getAttribute("loginMember")).getMemberId();
		
		BoardDTO board = new BoardDTO();
		board.setBoardNo(boardNo);
		board.setBoardWriter(memberId);
		// 삭제하러 드가자!
		int result = new BoardService().deleteBoard(board);
		
		if(result == 1) {
			session.setAttribute("message", "글 삭제 성공");
		}else if(result == 942){
			session.setAttribute("message", "이거 너가 작성한 글 아님..;");
		}else {
			session.setAttribute("message", "몰라 실패!");
		}
		
		response.sendRedirect("boards?page=1");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
