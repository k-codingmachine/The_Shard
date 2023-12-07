package com.shard.mapper;

import org.apache.ibatis.annotations.Param;

import com.shard.domain.ItemVO;
import com.shard.domain.WishListVO;

public interface ItemMapper {

// insert, update 의 기능은 관리자에서 구현한다고 영수가 말해줌!

//	단건 데이터 조회
	public ItemVO getItem(int itemNum);

	public int itemInsert(ItemVO vo);

	public void itemDelete(int itemNum);

	public int itemUpdate(ItemVO vo);
	
	public int wishListSelect(@Param("itemNum")int itemNum, @Param("email")String email);
	
	public void wishListInsert(@Param("itemNum")int itemNum, @Param("email")String email);
	
	public void wishListDelete(@Param("itemNum")int itemNum, @Param("email")String email);
}
