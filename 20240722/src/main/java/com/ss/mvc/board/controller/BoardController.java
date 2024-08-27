package com.ss.mvc.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ss.mvc.board.dto.Board;
import com.ss.mvc.board.dto.Reply;
import com.ss.mvc.board.service.BoardService;
import com.ss.mvc.board.util.PageInfo;
import com.ss.mvc.member.dto.Member;

// 요청 url의 상위 url을 모두 처리할 때 사용
@RequestMapping("/board")
@Controller
public class BoardController {
	// BoardService 객체를 가져와야된다.
	@Autowired
	private BoardService service;

	// Get 전송 방식만 처리하는 메서드를 생성할 수 있다.
	@GetMapping("/list")
	public String list(Model model, @RequestParam Map<String, String> param) {
		System.out.println("BoardController의 list()");
		System.out.println("리스트 요청: " + param);

		// 탐색할 맵을 선언
		Map<String, String> searchMap = new HashMap<String, String>();

		String searchType = param.get("searchType");
		String searchValue = param.get("searchValue");
		System.out.println("searchType: " + searchType);
		System.out.println("searchValue: " + searchValue);

		searchMap.put(searchType, searchValue);
		System.out.println("searchMap: " + searchMap);

		// 만약에 처음 게시판을 실행했을 때는 현재 페이지가 없을 수 있다. - nullpointerException 발생할 수 있음
		// → 무조건 page는 1부터 시작할 수 있도록 설정하기
		int page = 1;

		// 1. boardService에서 10개의 데이터를 반환받아서
		// 2. model에 담아서 view페이지로 전송
		// 현재 밑의 코드는 전체
		// 검색한 내용을 기준으로 가져와야한다.
		int boardCount = service.getBoardCount(searchMap);
		System.out.println("boardCount: " + boardCount);

		// 3. 현재 페이지를 기준으로 버튼을 클릭했을 때 페이징 처리하는 것
		PageInfo pageInfo = new PageInfo(page, 10, boardCount, 10);

		List<Board> list = service.getBoardList(pageInfo, searchMap);

		model.addAttribute("list", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("param", param); // 라디오 버튼 check가 사라지기 때문에

		return "board/list";
	}

	// @RequestMapping("/view") 로 써도 된다.
	@GetMapping("/view")
	public String view(Model model, int no) {
		// 1. boardService를 이용해서 먼저 글 번호 있는지 확인하는 메서드
		// 2. mapper 인터페이스에 글 번호가 있는지 메서드 확인
		// 3. xml 파일에 sql 실행문 작성
		// 4. 만약 게시글이 없다면 Controller에서 error 페이지로 가기
		// 5. 4번 이후 글번호가 있으면 게시글 리턴 받아서 view.jsp로 보내기
		System.out.println("BoardController의 view() 실행");
		System.out.println("no: " + no);

		Board board = service.findByNo(no);
		System.out.println("board: " + board);

		if (board == null) {
			return "common/error";
		}
		
		/*if(board.getReplies().get(0).getContent() == null) {
			board.setReplies(null);
		}*/
		model.addAttribute("board", board);
		if(board.getReplies() != null) {
			model.addAttribute("replyList", board.getReplies());
		}
		
		return "board/view";
	}
	
	@RequestMapping("/reply")
	public String writeReply(Model model, @ModelAttribute Reply reply, // 이전 페이지에서 reply 객체 정보 받아 저장하면서 Model에도 올려줌
			@SessionAttribute(name="loginMember", required = false) Member loginMember ) { // required = false가 없으면 loginMember가 null이 되면 해당 메서드가 실행하지 않기 때문
		
		// 1. boardService 클래스를 이용해서 댓글을 추가하는 명령문
		System.out.println("Reply 추가 전: " + reply);
		reply.setWriterNo(loginMember.getNo());
		
		int result = service.saveReply(reply);
		System.out.println("댓글 추가 후: " + result);
		
		// 작성자의 번호(writerNo)가 없으면 조인해서 댓글을 가져올 때 예외가 발생할 수 있다.
		System.out.println("Reply 추가 후: " + reply);
		
		if(result > 0) {
			model.addAttribute("msg", "댓글이 등록되었습니다.");
		} else {
			model.addAttribute("msg", "댓글이 등록되지않았습니다.");
		}
		
		// model에 다음 페이지로 갈 페이지명 저장하기
		model.addAttribute("location", "/board/view?no=" + reply.getBoardNo());
		
		return "common/msg";
	}
	
	// 1. replyDel 
	//	 	url 매핑, 매개변수로 replyNo, boardNo
	// 2. deleteReply 메서드명
	// 3. 서비스에서 리플을 삭제하는 메서드
	// 4. mapper 인터페이스에 메서드 작성
	// 5. xml 태그에 sql 구문을 작성하고 실행
	// 6. int 값으로 리턴 받아서
	// 7. 모델에 데이터를 저장하고 페이지가
	// 8. jsp로 이동했을 전송한 모델안에 필요한 변수값을 꺼내서 사용함.
	// 9. msg.jsp로 이동
	// 10. alert창 띄우고 페이지 이동 - 상세페이지로 이동하기
	@RequestMapping("/replyDel")
	public String deleteReply(Model model, int replyNo, int boardNo) {
		System.out.println("BoardController의 deleteReply() 실행");
		System.out.println("replyNo: " + replyNo);
		System.out.println("boardNo: " + boardNo);
		
		int result = service.deleteReply(replyNo);
		System.out.println("댓글 삭제 후: " + result);
		
		if(result > 0) {
			model.addAttribute("msg", "댓글이 삭제되었습니다.");
		} else {
			model.addAttribute("msg", "댓글이 삭제되지 않았습니다.");
		}
		
		model.addAttribute("location", "/board/view?no=" + boardNo);
		
		return "common/msg";
	}
	
}
