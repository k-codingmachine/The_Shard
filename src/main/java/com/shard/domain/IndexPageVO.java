package com.shard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexPageVO {
	private int  total, start;

	private int amount = 20;
	

	public IndexPageVO(int pageNum, int total) {

	}
}