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
import com.kh.maybach.member.model.service.MemberService;


@WebServlet("/update.form")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardUpdateFormController() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt( request.getParameter("boardNo"));
		
		HttpSession session = request.getSession();
		
		if(boardNo < 1) {
			session.setAttribute("message", "이노옴!");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		if(session.getAttribute("loginMember") == null) {
			session.setAttribute("message", "로그인 하세요");
			response.sendRedirect(request.getContextPath());
			return;
		}
		String memberId = ((MemberDTO)session.getAttribute("loginMember")).getMemberId();		
		
		BoardDTO board = new BoardDTO();
		board.setBoardNo(boardNo);
		board.setBoardWriter(memberId);
		
		BoardDTO result = new BoardService().getUpdateForm(board);
		if(result == null){
			session.setAttribute("message", "수정할 권한이 없습니다.");
			response.sendRedirect("board?boardNo="+boardNo);
			return;
		}
		request.setAttribute("board", result);
		session.setAttribute("updateBoardNo", result.getBoardNo());
		
		request.getRequestDispatcher("WEB-INF/views/board/update_form.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
