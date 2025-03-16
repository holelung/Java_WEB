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


@WebServlet("/delete-member")
public class DeleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteMemberController() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		String memberPw = request.getParameter("memberPw");
		String memberId = request.getParameter("memberId");
		
		MemberDTO member = new MemberDTO();
		
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		int result = new MemberService().deleteMember(member);
		
		HttpSession session = request.getSession();
		if(result != 1) {
			session.setAttribute("updateMessage", "회원 탈퇴에 실패하였습니다.");
		}else {
			session.setAttribute("updateMessage","회원 탈퇴 성공!");
			session.removeAttribute("signInMember");
		}
		String path = request.getContextPath();
		
		response.sendRedirect( result  != 1 ? path+"/my-page" : path );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
