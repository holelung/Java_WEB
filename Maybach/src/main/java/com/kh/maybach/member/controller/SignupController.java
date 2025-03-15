package com.kh.maybach.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.maybach.member.model.dto.MemberDTO;
import com.kh.maybach.member.model.service.MemberService;

// 요청처리
@WebServlet("/sign-up")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignupController() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		
		// null 들어가는게 솔직히 바람직하진 않긴함!
		MemberDTO member = new MemberDTO(memberId, memberPw, memberName, email, null);
		
		// 요청처리 -> 사용자가 입력한 값들을 저 멀리 있는 DB 서버의 KH_MEMBER 테이블에 한 행 INSERT
		int result = new MemberService().signUp(member);
		System.out.println(result);
		
		String path = request.getContextPath();
		
//		if(result != 0) {
//			// 실패했을 경우 => 회원가입 페이지로 이동
//			path += "/join";
//			response.sendRedirect(request.getContextPath() + "/join");
//		}else {
//			// 성공했을 경우 => 웰컴페이지로 이동
//			response.sendRedirect(request.getContextPath());
//		}
		// 실패 메시지 보내기 위한방법으로 사용할 수 없음
//		request.setAttribute("message", "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요");
		if(result == 0) {
			request.getSession().setAttribute("message", "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요");
		}
		
		response.sendRedirect(result != 0 ? path : path + "/join");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
