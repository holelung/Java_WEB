package com.kh.swp.member.model.service;

import java.sql.Connection;

import com.kh.swp.member.model.dao.MemberDAO;
import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.member.util.JdbcUtil;

public class MemberService {

	
	public MemberDTO signin(MemberDTO member) {
		
		MemberDAO memberDao = new MemberDAO();
		
		
		return new MemberDAO().signin(member);
		
		
	}
}
