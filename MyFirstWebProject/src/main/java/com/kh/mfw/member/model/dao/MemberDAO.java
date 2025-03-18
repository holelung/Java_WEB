package com.kh.mfw.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {

	static {
		try {
			// FullClassName = 패키지 경로부터 클래스 Name 까지 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Login
	 * @param member ID, PW 필드가 Setting 된 DTO
	 * @return
	 */
	public MemberDTO login(MemberDTO member) {
		String sql= """
				SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, EMAIL, ENROLL_DATE 
				FROM KH_MEMBER 
				WHERE MEMBER_ID=? AND MEMBER_PW=?
				""";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO result = null;
		
		try{
			conn = DriverManager.getConnection("","KH19_JJH","KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = new MemberDTO();
				result.setMemberId(rset.getString("MEMBER_ID"));
				result.setMemberPw(rset.getString("MEMBER_PW"));
				result.setMemberName(rset.getString("MEMBER_NAME"));
				result.setEmail(rset.getString("EMAIL"));
				result.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null) {
					rset.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}
	
	
	public int checkId(String memberId) {
		String sql = """
				SELECT 
					COUNT(*)
				FROM
					KH_MEMBER
				WHERE
					MEMBER_ID = ?
				""";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset  = null;
		int result = 1;
		try {
			conn = DriverManager.getConnection("","KH19_JJH","KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
// 			COUNT(*)로 SELECT한 경우			
			rset.next();
			result = rset.getInt("COUNT(*)");
			
			// MEMBER_ID 로 조회한 경우
			// next()의 결과가 true/false 인지만확인하면됨
			// 왜? 결과가 행이 0개거나 1개거나 이니깐
//			return rset.next(); 
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null) {
					rset.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	public int signUp(MemberDTO member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
				INSERT INTO
					KH_MEMBER
				VALUES (
						?,
						?,
						?,
						?,
						DEFAULT
					)
				""";
		try{
			conn = DriverManager.getConnection("","KH19_JJH","KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getEmail());

			return pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
