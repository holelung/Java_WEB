package com.kh.burgerking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.burgerking.model.dto.UserDTO;


@WebServlet("/sign-up.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SignUpController() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("크아아아");
		
		/*
		 *  컨트롤러
		 *  1. request 객체로 부터 값을 뽑아서 DTO로 가공
		 *  
		 *  2. 요청 처리 -> 패스
		 *  
		 *  3. VIEW 처리
		 */
		
		// Post 방식은 인코딩 방식이 ISO-8859-1로 바뀌기 때문에
		// 값을 뽑기 전에 미리 UTF-8으로 변경해주삼
		request.setCharacterEncoding("UTF-8");
		
		//1. 값 뽑기
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String userPw = request.getParameter("userPw");
		System.out.println(userPw);
		String userName = request.getParameter("userName");
		System.out.println(userName);
		
		UserDTO user = new UserDTO(userId, userPw, userName);
		// service.insertMember(user) ~!!~
		// 잘갔다왔다고 가정
		// service -> dao -> db
		
		
		// VIEW 처리
		
		// presentation 로직 분리
		
		// JSP를 이용해서 응답 데이터 만들기
		
		// JSP : JAVA 기반의 서버 사이드 스크립트 언어
		// ASP, PHP
		
		// ---------------------------------------------		
		// 응답화면(JSP)에서 필요한 데이터를 넘겨줄 것
		
		// Attribure => 키 : 벨류 세트로 묶어서 값을 담을 수 있음
		request.setAttribute("user", user);
		request.setAttribute("message", "요청 처리에 성공했습니다.");
		
	
		
		// ---------------------------------------------
		
		// 응답 페이지를 JSP에게 배정
		
		// RequestDispatcher
		RequestDispatcher view = request.getRequestDispatcher("/views/response_page.jsp");
		
		// request, response 전달
		view.forward(request, response);
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("호출,,,,,,1,,,,2,,,,,3,,,,,4,,,,,,5");
		doGet(request, response);
	}

}
