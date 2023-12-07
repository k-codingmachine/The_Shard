package com.shard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shard.domain.ItemQnAReplyVO;
import com.shard.domain.ItemQnAVO;
import com.shard.mapper.ItemQnAMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ItemQnAServiceImpl implements ItemQnAService{

	private final ItemQnAMapper itemQnAMapper;

	@Override // 고객 문의를 List에 담음
	public List<ItemQnAVO> getQnAList(int itemNum) {
		return itemQnAMapper.getQnAList(itemNum);
	}

	@Override // 관리자 답변을 List에 담음
	public List<ItemQnAReplyVO> getQnAReplyList(int itemQNum) {
		return itemQnAMapper.getQnAReplyList(itemQNum);
	}
	
// 문의 CRUD
	
	// 상품문의 생성
	@Override
	public int insertItemQuestion(ItemQnAVO vo) {
		return itemQnAMapper.insertItemQuestion(vo);
	}
	
	// 상품 문의를 단건 읽어옴
	@Override
	public ItemQnAVO readItemQuestion(int itemNum) {
		return itemQnAMapper.readItemQuestion(itemNum);
	}

	// 상품 문의 수정
	@Override
	public int updateItemQuestion(ItemQnAVO vo) {
		return itemQnAMapper.updateItemQuestion(vo);
	}

	// 상품 문의 삭제(itemQNum값)
	@Override
	public int deleteItemQuestion(int itemQNum) {
		return itemQnAMapper.deleteItemQuestion(itemQNum);
	}
	
	@Override
	public List<ItemQnAVO> getQnAListWithPaging(int itemNum, int start, int amount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getTotalQnACount(int itemNum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
// 문의 CRUD
	
	
// 답변 CRUD
	
	// 답변 생성
	@Override
	public int insertItemQnAReply(ItemQnAReplyVO vo) {
		return itemQnAMapper.insertItemQnAReply(vo);
	}
	
	// 상품 답변을 단건 읽어옴
	@Override
	public ItemQnAReplyVO readItemQnAReply(int itemQnaRNum) {
		return itemQnAMapper.readItemQnAReply(itemQnaRNum);

	}
	// 상품 답변 수정
	@Override
	public int updateItemQnAReply(ItemQnAReplyVO vo) {
		return itemQnAMapper.updateItemQnAReply(vo);
	}

	// 상품 답변 삭제
	@Override
	public int deleteItemQnAReply(int itemQnaRNum) {
		return itemQnAMapper.deleteItemQnAReply(itemQnaRNum);
	}


// 답변 CRUD

}
