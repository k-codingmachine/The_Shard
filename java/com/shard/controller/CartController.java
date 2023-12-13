package com.shard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shard.domain.ItemVO;
import com.shard.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@SessionAttributes({"user", "token"})
@RequestMapping("/order/*")
@Log4j
@RequiredArgsConstructor
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cart")
	public void cart(/* String email, List<Integer> itemNum, int cnt, String size, */ Model model) {
		model.addAttribute("email", "email10@gmail.com");
		
		int cartNum = cartService.getCartnum("email10@gmail.com");
		log.info("cartNum >>" + cartNum);
		
		cartService.initCart("email10@gmail.com", 1, "S");
		log.info("cart 들어옴");
				
		//cartService.detailCartItems(mapper.getCartnum(email));
		log.info("detailCartItems view >>" + cartService.detailCartItems(cartNum));
		model.addAttribute("cartNum", cartNum);
		
		List<ItemVO> ivo= cartService.detailCartItems(cartNum);
		model.addAttribute("itemList", ivo);
		
		int memNum = cartService.getMembership("email10@gmail.com");
		log.info("getMembership view >>" + cartService.getMembership("email10@gmail.com"));
		
		int pointRate = cartService.getPointRate(memNum);
		int deliveryCharge = cartService.deliveryCharge(memNum);
		log.info("getPointRate view >>" + pointRate);
		log.info("deliveryCharge view >>" + deliveryCharge);
		model.addAttribute("deliveryCharge", deliveryCharge);
		model.addAttribute("pointRate", pointRate);
		
		List<Integer> extractCartItemCnts = cartService.extractCartItemCnts(cartNum);
		List<Integer> sales = cartService.extractSales(ivo);
		log.info("extractCartItemCnts >>"+ extractCartItemCnts);
		log.info("sales >>" + sales);
		model.addAttribute("extractCartItemCnts", extractCartItemCnts);
		
		//인덱스의 범위를 생성하고, 각 인덱스에 대해 두 리스트를 곱하여 새로운 리스트를 생성
		List<Integer> totalPriceList = cartService.totalPriceList(sales, extractCartItemCnts);
		log.info("totalPriceList" + totalPriceList);
		model.addAttribute("totalPriceList", totalPriceList);
		
		int totalPrice = cartService.calculateTotalPrice(sales, extractCartItemCnts);
		log.info("totalPrice >>" + totalPrice);
		model.addAttribute("totalPrice", totalPrice);
		
		int TotalPriceWithShipping = deliveryCharge + totalPrice;
		log.info(TotalPriceWithShipping);
		model.addAttribute("TotalPriceWithShipping", TotalPriceWithShipping);
		
		List<Integer> itemRewardPoints = cartService.itemRewardPoints(sales, pointRate, extractCartItemCnts);
		log.info("itemRewardPoints >>" + itemRewardPoints);
		
		model.addAttribute("itemRewardPoints", itemRewardPoints);
	}
	
	@GetMapping("/cart_empty")
	public String cartEmpty() {
		return "/order/cart";
	}
	
	@PostMapping("/chooseDetailCartDelete")
    @ResponseBody
    public void chooseDetailCartDelete(@RequestBody List<Integer> selectedItems) {
		 // 리스트의 마지막 원소를 가져오기
        int cartNum = selectedItems.get(selectedItems.size() - 1);

        // 리스트의 마지막 원소 제거 (선택된 항목으로 사용하지 않음)
        List<Integer> itemNumList = selectedItems.subList(0, selectedItems.size() - 1);
        log.info(itemNumList);
	    cartService.chooseDetailCartDelete(itemNumList, cartNum);

	   }

	@PostMapping("/allDetailCartDelete")
	public void allDetailCartDelete(@RequestParam("cartNum") int cartNum) {
		
	        cartService.allDetailCartDelete(cartNum);
	}
	
	@PostMapping("/updateExpectedPlusAmount")
	public void updateExpectedAmount( @RequestParam("itemNum") int itemNum) {
		
		    cartService.detailCartCntPlusUpdate(itemNum);
	}
	@PostMapping("/updateExpectedMinusAmount")
	public void updateExpectedMinusAmount( @RequestParam("itemNum") int itemNum) {
		
			cartService.detailCartCntMinusUpdate(itemNum);
	}
	
}