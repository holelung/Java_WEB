package com.kh.maybach.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.maybach.common.Template.getSqlSession;

import com.kh.maybach.member.model.dao.MemberDAO;
import com.kh.maybach.member.model.dto.MemberDTO;

public class MemberService {

	
	public MemberDTO login(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();

		// 유효성 검증...해야하는데..pass
		
		MemberDTO loginMember = new MemberDAO().login(sqlSession, member);
		// 조회결과가 없으면 null / 있으면 주소값이 반환된다.
		
		sqlSession.close(); // 자원반납 필수
		
		return loginMember;
	}
	
	public int signUp(MemberDTO member) {
		// 3차 유효성 검증 Java
		// 4차 데이터 무결성을 위한 제약조건 (DBMS)

		// id 중복검사
		SqlSession sqlSession = getSqlSession();
		
		if(new MemberDAO().checkId(sqlSession, member.getMemberId())) {
			sqlSession.close();
			return 0;
		}
		int result = new MemberDAO().signUp(sqlSession, member);
		
		sqlSession.commit();
		sqlSession.close();
		
		return result;
		
	}
}