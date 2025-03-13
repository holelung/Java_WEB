package com.kh.swp.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.swp.member.model.dto.MemberDTO;
import com.kh.swp.util.JdbcUtil;

public class MemberDAO {
	
	
	
	

	
	public MemberDTO signin(Connection conn, MemberDTO member) {
		
		MemberDTO signinMember = null;
		
		
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
