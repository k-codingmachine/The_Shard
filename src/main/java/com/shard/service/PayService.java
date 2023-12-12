package com.shard.service;

import java.util.Map;

import com.shard.domain.PayVO;

public interface PayService {

	public void payInsert(PayVO pvo);
	public void deliveryComplete(int usePoint, int orderId, int usedpoint, String email, int memNum);
}
