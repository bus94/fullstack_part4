package com.ss.mvc.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ss.mvc.board.dto.Board;
import com.ss.mvc.board.dto.Reply;
import com.ss.mvc.board.util.PageInfo;

@Mapper
public interface BoardMapper {
	int selectBoardCount(Map<String, String> searchMap);
	
	// Mysql Page 기능활용
	List<Board> selectBoardList1(Map<String, String> searchMap);

	Board selectBoardByNo(int no);

	// 댓글을 추가하는 메서드
	int insertReply(Reply reply);

	// 댓글을 삭제하는 메서드
	int deleteReply(int replyNo);
}
