package com.shard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shard.domain.PayVO;
import com.shard.mapper.PayMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class PayServiceImpl implements PayService{
	@Autowired
	private PayMapper mapper;

	@Override
	public void payInsert(PayVO pvo) {
		mapper.payInsert(pvo);
	}

	@Override
	public void deliveryComplete(int usePoint, int orderId, int usedpoint, String email, int memNum) {
		mapper.ordersUpdate(usePoint, orderId);
		mapper.customerPointUpdate(usedpoint, email);
		mapper.getTotalAmountForLast3Months(email);
		mapper.membershipSUpdate(memNum);
	}

	
	
}
