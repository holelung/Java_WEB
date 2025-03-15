package com.kh.swp.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.member.model.service.MemberService;


@WebServlet("/update-info")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateInfoController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String date = request.getParameter("enrollDate");
		
		java.util.Date parseDate = null;
		try {
			parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date enrollDate = new Date(parseDate.getTime());
		
		HttpSession session = request.getSession();
		
		MemberDTO member = new MemberDTO(memberId, null, memberName, email, enrollDate);
		
		int result = new MemberService().updateInfo(member);
		
		
		switch(result) {
		case 1:
			session.setAttribute("updateMessage", "정보 수정 성공");
			session.setAttribute("signInMember", member);
			break;
		case 2:
			session.setAttribute("updateMessage", "수정한 이름이 양식에 맞지 않습니다(한글 최대 5글자)");
			break;
		case 3:
			session.setAttribute("updateMessage", "수정한 이메일이 양식에 맞지 않습니다.");
		default:
			session.setAttribute("updateMessage", "정보 업데이트 실패");
		}
		
		response.sendRedirect("my-page");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
