package com.shard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shard.service.OrderService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/order/*")
@Log4j
public class PayController {

	@Autowired
	private OrderService orderService;

//	@Autowired
//	private PayService payService;


//	// 검증완료후 결제완료시 작업
//	@PostMapping("/payComplete")
//	public String payComplete(DeliverAddrVO davo, @RequestParam String email, OrdersVO ovo, @RequestParam int cartNum,
//			@RequestParam List<Integer> itemNum, int usePoint, int issueNum) {
//		System.out.println(davo);
//		System.out.println(email);
//		System.out.println(ovo);
//		System.out.println(cartNum);
//		System.out.println(itemNum);
//
//		// 배송지 저장 클릭되어 있으면 저장
////		orderService.addDeliverAddr(davo, email);
//
////		orderService.orderComplete(ovo, dovo, cartNum, itemNum, ovo.getOrderId(), usePoint, email, issueNum);
//
//		return "order/orderComplete";
//	}
	
	// 검증완료후 결제완료시 작업
	@PostMapping("/payComplete")
	public String payComplete( @RequestParam int postcode , 
								@RequestParam String roadAddress,
								@RequestParam String detailAddr,
								@RequestParam String email, 
								@RequestParam int cartNum,
								@RequestParam String itemNum,
								@RequestParam(defaultValue = "0") int usePoint) {
		System.out.println(email);
		System.out.println(cartNum);
		System.out.println(postcode);
		System.out.println(roadAddress);
		System.out.println(detailAddr);
		System.out.println(itemNum);
		System.out.println(usePoint);
		
		
		// 배송지 저장 클릭되어 있으면 저장
//		orderService.addDeliverAddr(davo, email);
		
//		orderService.orderComplete(ovo, dovo, cartNum, itemNum, ovo.getOrderId(), usePoint, email, issueNum);
		
		return "order/orderComplete";
	}

	@PostMapping("/payFail")
	public void payFale(String ID) {
	}

}