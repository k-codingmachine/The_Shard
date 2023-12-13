package com.shard.service;

import java.util.List;

import com.shard.domain.ItemQnAReplyVO;
import com.shard.domain.ItemQnAVO;

public interface ItemQnAService {

		
		// 고객 문의 q=question Service
		List<ItemQnAVO> getQnAList(int itemNum);
		
		public int insertItemQuestion(ItemQnAVO vo); // 고객의 상품 문의를 생성
		public ItemQnAVO readItemQuestion(int itemQNum); // 고객의 문의를 itemQNum으로 읽어옴
		public int updateItemQuestion(ItemQnAVO vo); // 고객이 문의를 수정
		public int deleteItemQuestion(int itemQNum); // 고객이 itemQNum으로 문의을 삭제
		
		// 고객 문의 페이징
		List<ItemQnAVO> getQnAListWithPaging(int itemNum, int start, int amount);
		int getTotalQnACount(int itemNum);
		
		
		// 상품 답변 Service
		public int insertItemQnAReply(ItemQnAReplyVO vo); // 관리자가 답변을 생성
		public ItemQnAReplyVO readItemQnAReply(int itemQnaRNum); // 관리자의 답변을 itemAnum으로 읽어옴
		public int updateItemQnAReply(ItemQnAReplyVO vo); // 관리자가 답변을 수정
		public int deleteItemQnAReply(int itemQnaRNum); // 관리자가 itemANum으로 답변을 삭제
		
		List<ItemQnAReplyVO> getQnAReplyList(int itemQNum);

		
}
