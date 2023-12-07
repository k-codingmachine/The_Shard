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

// 아이템에 관한 문의
public class ItemQnAVO{
	
	private int itemQNum; // 질문 번호, auto
	private String itemQTitle;
	private String itemQContent;
	private Timestamp itemQRagDate; // 질문 등록일, 자동 등록
	private int itemQComplete; //답변 완료 미완료 표시
	private int itemNum;
	private String email;

	


}


