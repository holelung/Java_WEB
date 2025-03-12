package com.kh.swp.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.member.util.JdbcUtil;

public class MemberDAO {
	
	
	static {
		try {
			// FullClassName = 패키지 경로부터 클래스 Name 까지 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	
	public MemberDTO signin(MemberDTO member) {
		
		MemberDTO signinMember = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
				SELECT
					MEMBER_ID, MEMBER_PW, MEMBER_NAME, EMAIL, ENROLL_DATE
				FROM
					KH_MEMBER
				WHERE
					MEMBER_ID = ?
				AND
					MEMBER_PW = ?
				""";
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE","KH19_JJH","KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				signinMember = new MemberDTO(
									rset.getString("MEMBER_ID"),
									rset.getString("MEMBER_PW"),
									rset.getString("MEMBER_NAME"),
									rset.getString("EMAIL"),
									rset.getDate("ENROLL_DATE")
								);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.dqlClose(rset, pstmt, conn);
		}
		
		return signinMember;
	}

}
