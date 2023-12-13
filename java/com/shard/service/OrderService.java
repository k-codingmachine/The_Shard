package com.shard.service;

import java.util.List;

import com.shard.domain.CouponIssuanceVO;
import com.shard.domain.CouponVO;
import com.shard.domain.DeliverAddrVO;
import com.shard.domain.DetailOrderVO;
import com.shard.domain.ItemVO;
import com.shard.domain.OrdersVO;
import com.shard.domain.ShardMemberVO;

public interface OrderService{
	//필요한 데이터 가져오기
	public ShardMemberVO getCustomer(String email);
	public List<Integer> getDetailOrder(int orderId);
	public List<ItemVO> getDetailOrderItems(List<Integer> itemNumList);
	public List<CouponIssuanceVO> getCouponIssuance(String email);
	public int couponCount(String email);
	public List<CouponVO> couponList();
	public List<Integer> extractCouponNums(List<CouponIssuanceVO> cvoList);
	public List<CouponVO> getCoupon(List<Integer> couponNumList);
	public DeliverAddrVO getDefaultAddress(String email);
	public List<DeliverAddrVO> getUserAddress(String email);
	//public List<CouponVO> getCoupon(List<Integer> couponNumList);
	//배송지 추가/변경
	public void addDeliverAddr(DeliverAddrVO dvo, String email);
	public void deliverAddrUpdate(DeliverAddrVO dvo, int addrNum, String email);
	//계산
	public int calculateDiscountedAmount();
	//DetailOrderVO에서 cartItemCnt추출해서 List애 담음
	public List<Integer> extractCartItemCnts(List<DetailOrderVO> dvo);
	//List[ item.itemNum의 sale * detailcart.cartNum/email 의 cartItemCnt ]를 SUM 
//	public int calculateTotalPrice(List<Integer> sales, List<Integer> cnt);
	//주문완료
	public int orderComplete(OrdersVO ovo, int cartNum, List<Integer> itemNum, String email);
	public void orderComplete2(DetailOrderVO dovo,int orderId);
	public void orderComplete3(int orderId, int issueNum, int usePoint);
	//주문취소
	public void orderCancle(int orderId);
	//List로 변경
	public List<ItemVO> flattenItemList(ItemVO itemVO);
	//전체 적립금액
	public int totalRewardPoints(List<Integer> itemRewardPoints);
//	//최저금액 조건을 만족한 쿠폰정보
//	public List<Integer> extractCouponNums(List<CouponVO> couponList, int totalPrice);
//	//최저금액 조건을 만족한 발급된쿠폰
//	public List<CouponIssuanceVO> filterValidCoupons(List<CouponIssuanceVO> cvoList, List<Integer> validCouponNums);
	//사용한 쿠폰의 순서대로 쿠폰정보 뽑아오기
	public List<CouponVO> matchedCouponInfoList(List<Integer> couponNums, List<CouponVO> couponInfo, List<CouponIssuanceVO> cvoList);
	//할인율, 할인금액 중 0이 아닌 숫자 추출
	public List<Integer> extractedValues(List<CouponVO> couponInfo);
	//적립금, 쿠폰으로 할인된 금액 총 금액 산출
	public List<Integer> couponDiscountAmount(int selectCouponDiscountRate, int totalPrice, int point);
	//쿠폰 할인 구분
	public List<String> couponDiscountAmount(List<CouponVO> extractedValues);
	
	public int cartItemCount(String email);
}
