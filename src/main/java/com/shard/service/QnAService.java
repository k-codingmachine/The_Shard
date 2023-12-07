package com.shard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shard.domain.PageVO;
import com.shard.domain.QnAVO;

public interface QnAService {
	public List<QnAVO> qnaList(PageVO vo);
	
	public int qnaInsert(QnAVO vo, MultipartFile file);
	
	public int qnaInsert(QnAVO vo);
	
	public int delete(String email);
	
	public List<QnAVO> getQnAList(String email);
	
	public int totalCount();
	
	public int getReplyCheck(int replyNum, String replyPwd);
	
	public QnAVO getQnA(int replyNum);
}