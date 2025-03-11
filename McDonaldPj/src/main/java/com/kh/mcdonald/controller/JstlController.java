package com.kh.mcdonald.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mcdonald.model.dto.UserDTO;
import com.kh.mcdonald.model.service.UserService;


@WebServlet("/jstl")
public class JstlController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService userService = new UserService();
	
    public JstlController() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setAttribute("point", 200);
		
		String[] colors = { "orange", "red"};
		request.setAttribute("colrs", colors);
		
		// TB_USER 가지고 할것
		
		List<UserDTO> list = userService.findAll();
		
		
		request.setAttribute("users", list);
		request.setAttribute("message", "조회 성공");
		
		
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/jstl/jstl.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
