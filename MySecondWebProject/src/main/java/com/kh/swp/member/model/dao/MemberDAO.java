package com.kh.swp.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.util.JdbcUtil;

public class MemberDAO {
	

	
	public MemberDTO signIn(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.selectOne("memberMapper.signIn", member);
	}

	
	public int checkId(SqlSession sqlSession, String memberId) {
		
		return sqlSession.selectOne("memberMapper.checkId", memberId);
	}
	
	public int join(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.insert("memberMapper.join", member);
	}
	
	public int updateInfo(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.update("memberMapper.updateInfo", member);
	}
	
	public MemberDTO selectMember(SqlSession sqlSession, String memberId) {
		
		return sqlSession.selectOne("memberMapper.selectMember", memberId);
	}
	
	
	public int checkPwd(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.selectOne("memberMapper.checkPwd", member);
	}
	
	public int updatePwd(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.update("memberMapper.updatePwd", member);
	}
	
	public int deleteMember(SqlSession sqlSession, MemberDTO member) {
		
		return sqlSession.delete("memberMapper.deleteMember", member);
		
	}
}


