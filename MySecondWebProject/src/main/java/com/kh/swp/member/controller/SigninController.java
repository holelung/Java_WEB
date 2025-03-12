package com.kh.swp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.member.model.service.MemberService;


@WebServlet("/sign-in")
public class SigninController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SigninController() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO member = new MemberDTO();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		
		MemberDTO signinMember = new MemberService().signin(member);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("signinMember", signinMember);
		
		response.sendRedirect(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
