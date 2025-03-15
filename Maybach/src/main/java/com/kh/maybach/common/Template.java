package com.kh.maybach.common;



import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	// MyBatis
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		try {
			InputStream stream = Resources.getResourceAsStream("/mybatis-confing.xml");
			
			// 1단계 : SqlSessionFactoryBuilder 객체 생성
			//        그냥 생성자 호출하믄됨
			// 2단계 : SqlSessionFactory 객체 생성
			// 		  build(입력스트림) 스트림으로부터 환경설정파일의 값을 읽어오면서 SqlSessionFactory객체생성
			// 3단계 : SqlSession 객체 생성
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return sqlSession;
	}

}
