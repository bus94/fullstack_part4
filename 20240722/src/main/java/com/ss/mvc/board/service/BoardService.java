package com.ss.mvc.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.mvc.board.dto.Board;
import com.ss.mvc.board.dto.Reply;
import com.ss.mvc.board.mapper.BoardMapper;
import com.ss.mvc.board.util.PageInfo;

@Service
public class BoardService {
	@Autowired
	private BoardMapper mapper;
	
	public int getBoardCount(Map<String, String> searchMap) {
		return mapper.selectBoardCount(searchMap);
	}

	// 전체 게시글 중에 첫번째 페이지에서 10개의 게시글을 반환하는 메서드
	public List<Board> getBoardList(PageInfo info, Map<String, String> searchMap) {
		// mapper 호출 시 parameterType 주의하기
		// → 하나의 타입만 xml로 가지고 간다.
		return mapper.selectBoardList1(searchMap);
	}

	public Board findByNo(int no) {
		return mapper.selectBoardByNo(no);
	}

	public int saveReply(Reply reply) {
		return mapper.insertReply(reply);
	}

	public int deleteReply(int replyNo) {
		return mapper.deleteReply(replyNo);
	}


}
