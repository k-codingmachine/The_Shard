package com.shard.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

// 아이템문의(itemQnA)에 대한 대답
public class ItemQnAReplyVO {

	private int itemQnaRNum; // 관리자 답변글 번호
	
	private int itemQNum; // 고객 문의글 번호
	
	private String itemAnsContent; // 관리자 답변글 내용
	
	private Timestamp itemAnsRegDate; // 관리자 답변글 날짜, auto
	
	private String email; // 고객 이메일 
}
