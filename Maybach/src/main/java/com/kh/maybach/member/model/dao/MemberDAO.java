package com.kh.maybach.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.maybach.member.model.dto.MemberDTO;

public class MemberDAO {
	
	public MemberDTO login(SqlSession sqlSession, MemberDTO member) {
		
		// SqlSession이 제공하는 메소드를 통해 SQL문을 찾아서 실행하고 결과 받기 가능
		//sqlSession.sql문 종류에 맞는 메소드("mapper파일의 namespace.SQL문의 id 속성값);
	
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
	
	public int signUp(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.insert("memberMapper.signUp", member);
	}
	
	
	public boolean checkId(SqlSession sqlSession, String memberId) {
	
		return (Integer)sqlSession.selectOne("memberMapper.checkId", memberId) > 0 ? true : false;
	}
}