package com.shard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shard.domain.ItemVO;
import com.shard.domain.SearchPageVO;
import com.shard.domain.SortType;
import com.shard.service.ItemSearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@CrossOrigin
@Controller
@RequestMapping("/itemSearch/*")
@Log4j
@RequiredArgsConstructor
public class ItemSearchController {

	private final ItemSearchService service;

	@GetMapping("/list")
	public void list(@RequestParam(name = "itemName", required = false) String itemName,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "color", required = false) String color,
			@RequestParam(name = "sortType", defaultValue = "") String sortType, Model model) {
		System.out.println(sortType); // 가격순,최신순을 위한 문자
		// 색상과 이름을 같이 검색할때
		if (color != "" && itemName != "") {
			if(sortType.equals("Latest")) {
				// 최신순
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemColorCount(itemName, color));
				model.addAttribute("list", service.getItemColorLatest(itemName, color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("itemName", itemName);
				model.addAttribute("sortType", sortType);
			}else if (sortType.equals("HPrice")) {
				// 높은 가격순
				System.out.println("아이템 컬러 H들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemColorCount(itemName, color));
				model.addAttribute("list", service.getItemColorHPrice(itemName, color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("itemName", itemName);
				model.addAttribute("sortType", sortType);
			} else if (sortType.equals("RPrice")) {
				// 낮은 가격순
				System.out.println("아이템 컬러 R들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemColorCount(itemName, color));
				model.addAttribute("list", service.getItemColorRPrice(itemName, color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("itemName", itemName);
				model.addAttribute("sortType", sortType);
			}else {
				// 색상 이름만 검색했을때
				System.out.println("그냥 아이템 컬러 검색");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemColorCount(itemName, color));
				model.addAttribute("list", service.getItemColorWithPaging(itemName, color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("itemName", itemName);
			}
		}

		// 색상만 검색했을때
		else if (color != "") {
			if(sortType.equals("Latest")) {
				// 색상 최신순
				System.out.println("컬러 최신순 들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getColorCount(color));
			    model.addAttribute("list", service.getColorLatest(color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("sortType", sortType);	
			}else if(sortType.equals("HPrice")) {
				// 색상 높은가격순
				System.out.println("컬러 H들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getColorCount(color));
			    model.addAttribute("list", service.getColorHPrice(color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("sortType", sortType);
			}else if (sortType.equals("RPrice")) {
				// 색상 낮은 가격순
				System.out.println("컬러 R들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getColorCount(color));
				model.addAttribute("list", service.getColorRPrice(color, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("color", color);
				model.addAttribute("sortType", sortType);
			}else {
				// 색상만 검색했을때
			System.out.println("그냥 컬러 들어옴");
			SearchPageVO vo = new SearchPageVO(pageNum, service.getColorCount(color));
			model.addAttribute("list", service.ColorWithPaging(color, vo));
			model.addAttribute("pageMaker", vo);
			model.addAttribute("color", color);
			}
		}
		// 이름 검색
		else if (itemName != "") {
			if(sortType.equals("Latest")) {
				// 이름검색 최신순
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemNameCount(itemName));
				model.addAttribute("list", service.getItemLatest(itemName, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("itemName", itemName);
				model.addAttribute("sortType", sortType);
			}else if(sortType.equals("HPrice")) {
				// 이름검색 높은가격순
				System.out.println("아이템 H들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemNameCount(itemName));
				model.addAttribute("list", service.getItemHPrice(itemName, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("itemName", itemName);
				model.addAttribute("sortType", sortType);
			}else if(sortType.equals("RPrice")) {
				// 이름검색 낮은가격순
				System.out.println("아이템 R들어옴");
				SearchPageVO vo = new SearchPageVO(pageNum, service.getItemNameCount(itemName));
				model.addAttribute("list", service.getItemRPrice(itemName, vo));
				model.addAttribute("pageMaker", vo);
				model.addAttribute("itemName", itemName);
				model.addAttribute("sortType", sortType);
			}else {
				// 그냥 이름만 검색했을때
			System.out.println("그냥 아이템 들어옴");	
			SearchPageVO vo = new SearchPageVO(pageNum, service.getItemNameCount(itemName));
			model.addAttribute("list", service.ItemSearchWithPaging(itemName, vo));
			model.addAttribute("pageMaker", vo);
			model.addAttribute("itemName", itemName);
			}
			
	}
		
		
		// 전체 상품 최신순
		else {
			if(sortType.equals("Latest")) {
			System.out.println("전체 리스트 최신순 들어옴");	
	        SearchPageVO vo = new SearchPageVO(pageNum, service.getTotalCount());
	        model.addAttribute("list", service.AllLatest(vo));
	        model.addAttribute("pageMaker", vo);
	        model.addAttribute("sortType", sortType);
			}
			// 전체상품 높은가격순
			else if(sortType.equals("HPrice")) {
		        System.out.println("전체리스트 H들어옴");    
		        SearchPageVO vo = new SearchPageVO(pageNum, service.getTotalCount());
		        model.addAttribute("list", service.HPrice(vo));
		        model.addAttribute("pageMaker", vo);
		        model.addAttribute("sortType", sortType);
		        // 전체상품 낮은가격순
		    } else if(sortType.equals("RPrice")) {
		        System.out.println("전체리스트 R들어옴");    
		        SearchPageVO vo = new SearchPageVO(pageNum, service.getTotalCount());
		        model.addAttribute("list", service.RPrice(vo));
		        model.addAttribute("pageMaker", vo);
		        model.addAttribute("sortType", sortType);
		    } else {
		    	// 그냥 전체상품
		        System.out.println("그냥 전체리스트 들어옴");    
		        SearchPageVO vo = new SearchPageVO(pageNum, service.getTotalCount());
		        model.addAttribute("list", service.Search(vo));
		        model.addAttribute("pageMaker", vo);
		    }
		}

	}

	// 카테고리 전용(메인페이지에서 클릭시 이동)
	@GetMapping("/list/category/{categoryNum}")
	public String categoryList(@PathVariable(name = "categoryNum") int categoryNum,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, Model model) {
		SearchPageVO vo = new SearchPageVO(pageNum, service.getCategoryCount(categoryNum));
		vo.setCategoryNum(categoryNum);
		System.out.println("categoryNumSelect >>>" + vo);
		model.addAttribute("list", service.CategoryWithPaging(categoryNum, vo));
		model.addAttribute("pageMaker", vo);
		model.addAttribute("categoryNum", categoryNum);
		return "/itemSearch/categorylist";
	}

	//카테고리 가격순,최신순 정렬
	@GetMapping("/list/category/{categoryNum}/{sortType}")
	public String priceSortedList(@PathVariable(name = "categoryNum") int categoryNum,
			@PathVariable(name = "sortType") SortType sortType,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, Model model) {
		if(sortType == SortType.Latest) {
			SearchPageVO vo = new SearchPageVO(pageNum, service.getCategoryCount(categoryNum));
			model.addAttribute("list", service.getCategoryLatest(categoryNum, vo));
			model.addAttribute("pageMaker", vo);
			model.addAttribute("categoryNum", categoryNum);
		}
		else if (sortType == SortType.HPrice) {
			SearchPageVO vo = new SearchPageVO(pageNum, service.getCategoryCount(categoryNum));
			model.addAttribute("list", service.getCategoryHPrice(categoryNum, vo));
			model.addAttribute("pageMaker", vo);
			model.addAttribute("categoryNum", categoryNum);
			System.out.println("categoryHP >>" + vo);
		} else if (sortType == SortType.RPrice) {
			SearchPageVO vo = new SearchPageVO(pageNum, service.getCategoryCount(categoryNum));
			model.addAttribute("list", service.getCategoryRPrice(categoryNum, vo));
			model.addAttribute("pageMaker", vo);
			model.addAttribute("categoryNum", categoryNum);
			System.out.println("itemNameSelect >>" + vo);
		} else {
			throw new IllegalArgumentException("지원하지 않는 정렬 유형: " + sortType);
		}
		return "/itemSearch/categoryPricelist";
	}
}

