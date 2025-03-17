package com.kh.maybach.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.kh.maybach.board.model.dto.BoardDTO;
import com.kh.maybach.board.model.service.BoardService;
import com.kh.maybach.member.model.dto.MemberDTO;


@WebServlet("/update.board")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public BoardUpdateController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		int boardNo = (int) session.getAttribute("updateBoardNo");
		// 중간에 세션이 없어질 수도있겠지..?
		if(session.getAttribute("loginMember")==null) {
			session.setAttribute("message", "로그인 필요");
			response.sendRedirect("board?boardNo="+boardNo);
			return;
		}
       

		BoardDTO board = new BoardDTO();
		board.setBoardCategory(request.getParameter("category"));
		board.setBoardTitle(request.getParameter("title"));
		board.setBoardContent(request.getParameter("content"));
		board.setBoardNo(boardNo);

		
		int result = new BoardService().updateBoard(board);
		if(result != 1) {
			session.setAttribute("message", "수정 실패");
		}
		session.setAttribute("message", "수정 성공");
		response.sendRedirect("board?boardNo="+boardNo);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	


}
