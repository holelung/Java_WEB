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


@WebServlet("/update-pwd")
public class UpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdatePwdController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userPwd = request.getParameter("userPwd");
		String changePwd = request.getParameter("changePwd");
		HttpSession session = request.getSession();
		
		MemberDTO member = (MemberDTO) session.getAttribute("signInMember");
		member.setMemberPw(userPwd);
		
		int result = new MemberService().updatePwd(member, changePwd);
		
		switch(result) {
		case 1: 
			session.setAttribute("updateMessage", "비밀번호 변경성공");
			break;
		case 2:
			session.setAttribute("updateMessage", "비밀번호 입력 양식에 안맞음");
			break;
		default:
			session.setAttribute("updateMessage", "비밀번호 변경 실패");
		}
		
		response.sendRedirect("my-page");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
