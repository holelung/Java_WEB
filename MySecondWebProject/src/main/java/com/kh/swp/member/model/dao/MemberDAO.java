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
		
		return sqlSession.selectOne("signIn", member);
	}

}
