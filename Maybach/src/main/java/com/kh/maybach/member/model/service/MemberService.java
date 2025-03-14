package com.kh.maybach.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.maybach.common.Template.getSqlSession;
import com.kh.maybach.member.model.dto.MemberDTO;

public class MemberService {

	
	public MemberDTO login(MemberDTO member) {
		
		SqlSession sqlSession = getSqlSession();
		
		
		return null;
	}
}