package com.kh.burgerking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //서블릿 올리자마자 init 호출
    public OrderServlet() {
        super();
    }

	// servlet 메서드 호출 순서 init -> service -> do~
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// System.out.println("/bk/order doGet 요청이 들어옴!");
		/*
		 * VIEW 에서 GET 방식으로 요청시 doGet()가 호출됨
		 * 
		 * 두 개의 매개변수가 존재함
		 * 
		 * 첫 번째 매개변수 HttpServletRequest => 요청 시 전달된 내용들이 담김
		 * -> 요청 전송방식(GET), 사용자의 IP주소, 어떤 URL을 통해서 왔는지, 사용자가 입력한 값 등...
		 * 
		 * 두 번째 매개변수 HttpServletResponse => 요청 처리 후 응답할 때 사용
		 */
		
		//String qs = request.getQueryString();
//		System.out.println(qs);
	
		String menu = request.getParameter("menus");
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		
		// 잘다녀왔다고 가정
		int totalPrice = num * 14000;
		/*
		 * [제품명]의 총 가격은 [금액]원 입니다. 
		 */
		
		// 1 단계 ) 응답데이터 형식 지정 -> 문서형태의 HTML / 인코딩 방식 UTF-8
		response.setContentType("text/html; charset=UTF-8");
		
		// 2 단계 ) 출력 스트림 생성
		PrintWriter writer = response.getWriter();
		
		// 3 단계 ) 스트림을 통해 HTML 출력
//		writer.println("<html>");
//		writer.println("<head>");
//		writer.println("<title>ㅎㅎ</title>");
//		writer.println("</head>");
//		writer.printf("Get 응답!\n"
//				+ "%s의 총가격은 %d입니다.", menu, totalPrice);
//		writer.println("</html>");
		
		try {
			String data = String.format("%s의 총 가격은 %d입니다.", menu, totalPrice);
			writer.print(data);
			
		}finally {
			writer.close();
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("/bk/order doPost 메소드 호출됨!");
		
	}

}
