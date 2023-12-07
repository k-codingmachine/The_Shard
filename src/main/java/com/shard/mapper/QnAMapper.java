package com.shard.mapper;

import java.util.List;

import com.shard.domain.PageVO;
import com.shard.domain.QnAVO;

public interface QnAMapper {
	public List<QnAVO> qnaList(PageVO vo);

	public int qnaInsert(QnAVO vo);

	public int delete(String email);

	public int totalCount();
	
	public String getReplyPwd(int replyNum);
	
	public List<QnAVO> getQnAList(String email);
	
	public QnAVO getQnA(int replyNum);
}