package com.kh.swp.member.model.service;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;

import static com.kh.swp.common.Template.getSqlSession;
import com.kh.swp.member.model.dao.MemberDAO;
import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.util.JdbcUtil;

public class MemberService {

	
	public MemberDTO signin(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		
		// 유효성 검증
		// 1) ID = 영어, 숫자를 포함한 4-10길이의 문자열 받았는지
		// 2) PWD = 영어, 숫자, 특수문자를 포함한 최소 8길이의 문자열
		if(!member.getMemberId().matches("^[a-zA-Z0-9]{4,10}$")) {
			return null;
		}
//		if(!member.getMemberPw().matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$")) {
//			return null;
//		}
		// 제약조건 지켜서 만든 유저정보가 없어서 패스..
		
		
		return new MemberDAO().signIn(sqlSession, member);
		
	}
}
