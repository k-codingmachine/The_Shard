package com.shard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shard.domain.ItemReplyVO;
import com.shard.domain.PageVO;
import com.shard.domain.QnAVO;
import com.shard.service.ItemReplyService;
import com.shard.service.ItemService;
import com.shard.service.QnAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/item/*")
@Log4j
@RequiredArgsConstructor
public class ItemInfoController {

	private final ItemService itemService;
	private final ItemReplyService replyService;

	/* 영수 코드를 사용하여 이름 통일이 안 되어있음 */
	private final QnAService service;

	// 상품 정보 페이지
	@GetMapping("/itemInfo")
	public String itemInfo(Model model, @RequestParam("itemNum") int itemNum, @RequestParam("pageNum") int pageNum) {

		// 고객 상품 리뷰 `리스트`
		List<ItemReplyVO> getReplyList = replyService.getReplyList(itemNum);
		model.addAttribute("getReplyList", getReplyList);

		// 고객 상품 리뷰 `페이징`
		PageVO vo = new PageVO(pageNum, replyService.totalCount(itemNum));
		model.addAttribute("itemInfo", itemService.getItem(itemNum));
		model.addAttribute("list", replyService.replyList(vo));
		model.addAttribute("page", vo);
		model.addAttribute("itemNum", itemNum);

		return "item/itemInfo"; // 이건 제발 주석처리 하지 말자
	}

	@GetMapping("/insertReply")
	public void insert() {
	}

	// 고객 상품 리뷰 추가 기능
	@PostMapping("/insertReply")
//	@PreAuthorize("isAuthenticated()")
	public String insertReply(ItemReplyVO vo, @RequestParam("img") MultipartFile file,
			@RequestParam("pageNum") int pageNum, // 리뷰의 img1 이미지 추가
			RedirectAttributes rttr) {

		replyService.insertReply(vo);
		rttr.addFlashAttribute("message", "리뷰가 성공적으로 작성되었습니다!");

		System.out.println(file);
		int result = replyService.insertReply(vo, file);

		if (result == -1) { // 파일 크기가 5MB가 넘었을경우
			rttr.addFlashAttribute("file", "up");
		} else if (result == -2) { // 사진 파일이 아닐 경우
			System.out.println("사진 파일 아님");
			rttr.addFlashAttribute("file", "noImg");
		} else if (result == -3) { // 파일 업로드 중 에러가 발생한 경우
			System.out.println("사진업로드 중 에러 발생");
		}
		log.info("페이지 번호 넘어가냐? >>> " + pageNum);
		return "redirect:/item/itemInfo?itemNum=" + vo.getItemNum() + "&pageNum=" + pageNum;
	}

	@GetMapping("/list")
	public void list(@RequestParam("pageNum") int pageNum, Model model, @ModelAttribute("file") String file,
			@ModelAttribute("result") String result) {
		PageVO vo = new PageVO(pageNum, service.totalCount());
		model.addAttribute("page", vo);
		model.addAttribute("list", service.qnaList(vo));
//		model.addAttribute("enswer", enswerService.enswerList());
		model.addAttribute("file", file);
		model.addAttribute("result", result);
	}

	/*
	 * QnA영역
	 * 
	 * @GetMapping("/insertQna") public String insertQnA(@RequestParam("itemNum")
	 * int itemNum, Model model) { String itemName =
	 * itemService.getItemNameByItemNum(itemNum); // 아이템 서비스를 이용하여 상품 이름 가져오기 (가정)
	 * 
	 * model.addAttribute("itemNum", itemNum); model.addAttribute("itemName",
	 * itemName); // itemName을 Model에 추가
	 * 
	 * return "item/insertQna"; // 뷰 이름을 반환 }
	 */

	@PostMapping("/insertQna")
	public String insertQnA(QnAVO vo, @RequestParam("img") MultipartFile file, @RequestParam("itemNum") int itemNum,
			RedirectAttributes rttr) {

		System.out.println(file);
		int result = service.qnaInsert(vo, file);

		if (result == -1) { // 파일 크기가 5MB가 넘었을경우
			rttr.addFlashAttribute("file", "up");
		} else if (result == -2) { // 사진 파일이 아닐 경우
			System.out.println("사진 파일 아님");
			rttr.addFlashAttribute("file", "noImg");
		} else if (result == -3) { // 파일 업로드 중 에러가 발생한 경우
			System.out.println("사진업로드 중 에러 발생");
		}

		log.info("아이템 넘이 넘우넘우 궁금하다 : " + itemNum);
//		return "redirect:/item/insertQna?pageNum=1"; //여기에 itemNum을 info에서 가지고 넘겨야함.

		return "redirect:/item/insertQna?itemNum=" + itemNum + "&pageNum=1";
	}
	
	
	
	// wishlist
	@GetMapping("/wishlist")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	public ResponseEntity<String> wishList(
	    @RequestParam("itemNum") int itemNum,
	    @RequestParam("email") String email) {

	    System.out.println("위시리스트");
	    int result = itemService.wishListSelect(itemNum, email);

	    if (result == 0) {
	        System.out.println("위시리스트에 추가");
	        itemService.wishListInsert(itemNum, email);
	        return ResponseEntity.status(HttpStatus.OK).body("ADD");
	        
	    } else {
	        System.out.println("위시리스트 제거");
	        itemService.wishListDelete(itemNum, email);
	        return ResponseEntity.status(HttpStatus.OK).body("REMOVE");
	   
	    }
	}

	 
	 

	
	
	
	

	/*
	 * 작성만 해 이거 ㄴㄴ , 추후에 코드 정리 할 때 수정, 삭제 내용을 삭제 할 수 있도록...
	 * 
	 * // 고객 상품리뷰 수정
	 * 
	 * @PostMapping("/updateReply") public String updateReply(ItemReplyVO
	 * vo, @RequestParam("replyNum") int replyNum, RedirectAttributes rttr) {
	 * rttr.addFlashAttribute("message", "리뷰가 수정되었습니다!"); int itemNum =
	 * replyService.getItemNumByReplyNum(replyNum); // 해당 리뷰의 상품 번호 가져오기
	 * 
	 * return "redirect:/item/itemInfo?itemNum=" + itemNum; }
	 * 
	 * // 고객 상품리뷰 삭제
	 * 
	 * @PostMapping("/deleteReply") public String
	 * deleteReply(@RequestParam("replyNum") int replyNum, RedirectAttributes rttr)
	 * {
	 * 
	 * int itemNum = replyService.getItemNumByReplyNum(replyNum); // 해당 리뷰의 상품 번호
	 * 가져오기 int result = replyService.deleteReply(replyNum);
	 * 
	 * if(result > 0) { rttr.addFlashAttribute("message", "리뷰가 성공적으로 삭제되었습니다."); }
	 * else { rttr.addFlashAttribute("message", "아뿔싸! 리뷰가 삭제가 안되었습니다."); } return
	 * "redirect:/item/itemInfo?itemNum=" + itemNum;
	 * 
	 * }
	 */

}
