package com.shard.mapper;

import org.apache.ibatis.annotations.Param;

import com.shard.domain.PayVO;

public interface PayMapper {
	public void payInsert(PayVO pvo);
	//배송완료시
	public void ordersUpdate(@Param("usePoint")int usePoint, @Param("orderId")int orderId);
	public void customerPointUpdate(@Param("usedpoint")int usedpoint, @Param("email")String email);
	public int getTotalAmountForLast3Months(String email);
	public void membershipSUpdate(int memNum);
	
}