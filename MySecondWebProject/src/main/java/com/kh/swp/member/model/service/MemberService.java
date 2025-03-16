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
		
		MemberDTO result = new MemberDAO().signIn(sqlSession, member);
		sqlSession.close();
		
		return result;
	}
	
	
	public int join(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		// 유효성 검증
		if(!member.getMemberId().matches("^[a-zA-Z0-9]{4,10}$")) {
			return 2;
		}
		if(!member.getMemberPw().matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$")) {
			return 3;
		}
		if(!member.getMemberName().matches("^[가-힣]{1,6}$")) {
			return 4;
		}
		if(!member.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			return 5;
		}
		
		if(new MemberDAO().checkId(sqlSession, member.getMemberId()) != 0) {
			return 0;
		}
		
		int result = new MemberDAO().join(sqlSession, member);
		
		sqlSession.close();
		return result;
	}
	
	public int updateInfo(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		
		if(!member.getMemberName().matches("^[가-힣]{1,6}$")) {
			return 2;
		}
		if(!member.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			return 3;
		}
		
		int result = new MemberDAO().updateInfo(sqlSession, member);
		sqlSession.close();
		return result;
	}
	
	public MemberDTO selectMember(String memberId) {
		SqlSession sqlSession = getSqlSession();
		MemberDTO result = new MemberDAO().selectMember(sqlSession,memberId);
		
		sqlSession.close();
		return result;
	}
	
	
	public int updatePwd(MemberDTO member, String changePwd) {
		SqlSession sqlSession = getSqlSession();
		
		
		
		if(new MemberDAO().checkPwd(sqlSession, member) != 1) {
			return 0;
		}
//		유효성 검사 테스트할때 귀찮아서 주석처리함
//		if(!changePwd.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$")) {
//			return 3;
//		}
		
		member.setMemberPw(changePwd);
		int result = new MemberDAO().updatePwd(sqlSession, member);
		
		sqlSession.close();
		
		return result;
	}
	
	public int deleteMember(MemberDTO member) {
		SqlSession sqlSession = getSqlSession();
		
//		유효성 검사 테스트할때 귀찮아서 주석처리함
//		if(!changePwd.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$")) {
//			return 3;
//		}
		
		int result = new MemberDAO().deleteMember(sqlSession, member);
		
		sqlSession.close();
		
		return result;
	}
	
}
