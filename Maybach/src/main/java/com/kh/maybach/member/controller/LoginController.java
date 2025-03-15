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


@WebServlet(name = "sign-in", urlPatterns = { "/sign-in" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 절차
		// 1) GET / POST => 요청방식이 POST라면 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청값이 있나 없나? => 있다면 값을 뽑아서 가공
		// request.getParameter("input요소의 name속성값");
		// 						 ㄴ 100%는 아님
		
		// 3) 값이 두개 이상일 경우 어딘가에 예쁘게 담기
		MemberDTO member = new MemberDTO();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		
		MemberDTO loginMember = new MemberService().login(member);
		
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		
		// 4) 응답 화면 만들기
		
		/*
		 * 로그인에 성공했다면, 로그인 한 회원의 정보를 로그아웃 요청이 들어오거나, 브라우저를 종료하기 전까지는
		 * 계속 사용할 수 있어야 하기 때문에,
		 * Session이라는 저장소에 값을 담아둘 것
		 */
		
		// Session 의 자료형 : HttpSession
		HttpSession session = request.getSession(); // 세션으로 만드는게 맞으나...
		session.setAttribute("loginMember", loginMember);
		//request.getRequestDispatcher("/").forward(request, response);
		
		// sendRedirect : Client에게 재 요청할 URL을 알려주어서
		// Client가 다시 요청을 보내게 만드는 방법
		
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
