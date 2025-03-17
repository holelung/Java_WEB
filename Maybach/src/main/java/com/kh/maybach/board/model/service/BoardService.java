package com.kh.maybach.board.model.service;

import com.kh.maybach.board.model.dao.BoardDAO;
import com.kh.maybach.board.model.dto.BoardDTO;
import static com.kh.maybach.common.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class BoardService {
	
	private BoardDAO boardDao  = new BoardDAO();
	
	public void insertBoard(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();
		
		// 유효성 검증 => 햇다고 침
		boardDao.insertBoard(sqlSession, board);
		
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	public Map<String, Object> selectBoards(int page) {
		
		SqlSession sqlSession = getSqlSession();
		
		// 게시글 총 개수
		int boardCount = boardDao.selectBoardCount(sqlSession);
		// page == 요청 페이지 번호
		
		int boardLimit = 3; // 한페이지에 보여줄 게시글 개수
		int btnLimit = 3;  //  한페이지에 보여줄 버튼 개수
		
		// 마지막 페이지
		int maxPage = Math.ceilDiv(boardCount, boardLimit);
		
		// startBtn : 페이지 하단에 보여질 버튼 중 시작값
		// page : 한페이지에 몇개의 페이지 버튼을 보일 것인지
		/*
		 * 	5개일 경우 5n+1
		 *  start : 1, 6, 11 ...
		 *  
		 *  (page-1)/5
		 */
		int startBtn = (page-1) / btnLimit * btnLimit + 1;
		/*
		 * endBtn( btn개수가5개)
		 * startBtn : 1 => endBtn : 5
		 */
		int endBtn = startBtn+(btnLimit -1);
		if(endBtn > maxPage ) endBtn = maxPage;
		
		/*
		 * MyBatis RowBounds 사용하기
		 * 
		 * offset : 전체 결과에서 몇번 건너 뛸 것인가 (page 번호)
		 *  limit : 몇개씩 건너뛸 것인가 (boardLimit)
		 *  
		 *  page1 : (board)1 - 3
		 *  page2 : (board)4 - 6
		 */
		RowBounds rowBounds = new RowBounds((page-1)*boardLimit , boardLimit);
	
		
		// 나중일 -> 페이징 처리 이후
		List<BoardDTO> boards = boardDao.selectBoards(sqlSession, rowBounds);
		
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boards", boards);
		map.put("boardCount", boardCount);
		map.put("page", page);
		map.put("boardLimit", boardLimit);
		map.put("btnLimit", btnLimit);
		map.put("maxPage", maxPage);
		map.put("startBtn", startBtn);
		map.put("endBtn", endBtn);
		
		return map;
	}

	public BoardDTO findByBoardNo(int boardNo) {
		
		SqlSession sqlSession = getSqlSession();
		
		// 최대 두 번 가야함
		// 조회수 증가 로직 한번 		==> UPDATE / COMMIT
		// 게시글 정보 조회 로직 한 번	==> SELECT
		
		// UPDATE
		int updateResult = boardDao.increaseCount(sqlSession, boardNo);
		// boardDao.셀렉트();
		if(updateResult == 0) {
			return null;
		}
		
		BoardDTO board = boardDao.findByBoardNo(sqlSession, boardNo);
		if(board != null) {
			sqlSession.commit();
		}
		sqlSession.close();
		return board;
	}
	
	public BoardDTO getUpdateForm(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();
		
		BoardDTO result = boardDao.getUpdateForm(sqlSession, board);

		sqlSession.close();
		
		return result;
	}
	
	public int updateBoard(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();
		
		int result = boardDao.updateBoard(sqlSession, board);
		
		if(result != 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
	}
		
	public int deleteBoard(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();
		
		int check = boardDao.checkBoard(sqlSession, board);
		if(check == 0) {
			return 942;
		}
		
		
		int result = boardDao.deleteBoard(sqlSession, board);
		if(result != 0) {
			sqlSession.commit();
		}	
		sqlSession.close();
		return result;
	}
	

	
}
