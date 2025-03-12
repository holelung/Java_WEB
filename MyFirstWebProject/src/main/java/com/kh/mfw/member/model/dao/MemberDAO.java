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
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE","KH19_JJH","KH1234");
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
	
}
