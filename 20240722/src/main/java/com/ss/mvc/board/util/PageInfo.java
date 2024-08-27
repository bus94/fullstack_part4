package com.ss.mvc.board.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 게시글을 몇 개씩 페이징 처리할 것인지 저장하는 클래스
public class PageInfo {
	// 현재 페이지
	private int currentPage;
	
	// 한 페이지에 보여질 페이지 수
	private int pageLimit;
	
	// 총 게시글의 수
	private int listCount;
	
	// 한 페이지에 보여질 게시글 수
	private int listLimit;
	
	private int startPage;
	
	private int endPage;
	
	// 페이징 된 페이지 중 시작 페이지
	/*
	 *  1  2  3  4  5  6  7  8  9 10
	 * 11 12 13 14 15 16 17 18 19 20
	 * 
	 * 만약 n 값 기준으로 n = 0이면 첫 번째(1~10) , n = 1이면 두 번째(11~20)
	 * (여기에서 10은 pageLimit인 것)
	 * 첫 번째 페이지 기준 startPage = (n * 10) + 1 , endPage = (n + 1) * 10
	 * 
	 */
	public int getStartPage() {
		// 1 11 21 31 ... → (n >= 0)조건에서 (n * 10) + 1 
		// n = (currentPage - 1) / pageLimit
		return this.pageLimit * ((this.currentPage - 1) / (this.pageLimit)) + 1;
	}
	
	// 페이징 된 페이지 중 마지막 페이지
	public int getEndPage() {
		// 10, 20, 30, 40, ...
		// 1(시작 페이지) + 10(pageLimit) = 11 나오므로 -1을 해준다.
		int endPage = getStartPage() + this.pageLimit - 1;
		return endPage > getMaxPage() ? getMaxPage() : endPage;
	}
	
	// 전체 페이지 중 가장 마지막 페이지
	public int getMaxPage() {
		return (int) Math.ceil((double) this.listCount/this.listLimit);
	}
	
	// 이전 페이지
	public int getPrevPage() {
		int prevPage = this.currentPage - 1;
		
		// 이전 페이지로 갈 때
		// 1 페이지 일때 이전을 누르면 0이 없다
		return prevPage < 1 ? 1 : prevPage;
	}
	
	// 다음 페이지
	public int getNextPage() {
		int nextPage = this.currentPage + 1;
		
		return nextPage > getMaxPage() ? getMaxPage() : nextPage;
	}

	@Builder
	public PageInfo(int currentPage, int pageLimit, int listCount, int listLimit) {
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.listCount = listCount;
		this.listLimit = listLimit;
	}
	
	// 가장 첫 번째 페이지로 이동
	// 100   1~10  11~20  21~30
	
	// 가장 마지막으로 이동하는 페이지 함수
}
