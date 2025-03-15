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


@WebServlet("/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JoinController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		
		MemberDTO member = new MemberDTO(
					request.getParameter("memberId"), 
					request.getParameter("memberPw"), 
					request.getParameter("memberName"),
					request.getParameter("email"),
					null
				);
		
		
		int result = new MemberService().join(member);
		
	
		HttpSession session = request.getSession();
		
		switch(result) {
		case 1: 
			session.setAttribute("joinMessage", "회원가입 성공!");
			break;
		case 2: 
			session.setAttribute("joinMessage", "아이디는 영어, 숫자를 조합하여 최소 4글자 최대 10글자로 입력하세요.");
			break;
		case 3: 
			session.setAttribute("joinMessage", "비밀번호는 특수문자,영어,숫자를 조합하여 8글자 이상 입력하세요.");
			break;
		case 4: 
			session.setAttribute("joinMessage", "이름은 한글 5글자만 가능합니다.");
			break;
		case 5: 
			session.setAttribute("joinMessage", "이메일 양식에 맞추어서 작성해주세요");
			break;
		default: 
			session.setAttribute("joinMessage", "회원가입 실패(아이디 중복)");
		}
		
		response.sendRedirect(result == 1 ? path : path + "/sign-up");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
