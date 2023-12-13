package com.shard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shard.domain.ItemVO;
import com.shard.domain.SearchPageVO;

public interface ItemSearchMapper {

	public List<ItemVO> getSearchAll(SearchPageVO vo); // 전체 리스트
	public List<ItemVO> getSearchAllLatest(SearchPageVO vo); // 전체 리스트 최신순 정렬
	public List<ItemVO> getAllLatest(); //메인페이지용
	public List<ItemVO> getRPrice(SearchPageVO vo); // 전체 상품 높은 가격순
	public List<ItemVO> getHPrice(SearchPageVO vo); // 전체 상품 낮은 가격순
	public List<ItemVO> getCategoryHPrice(@Param("categoryNum") int categoryNum, @Param("vo") SearchPageVO vo); // 카테고리 검색시 높은가격순
	public List<ItemVO> getCategoryRPrice(@Param("categoryNum") int categoryNum, @Param("vo") SearchPageVO vo); // 카테고리 검색시 낮은가격순
	public List<ItemVO> getCategoryLatest(@Param("categoryNum") int categoryNum, @Param("vo") SearchPageVO vo);	// 카테고리 검색시 최신순
	public List<ItemVO> getColorHPrice(@Param("color")String color, @Param("vo") SearchPageVO vo); // 색상 검색시 높은 가격순
	public List<ItemVO> getColorRPrice(@Param("color")String color, @Param("vo") SearchPageVO vo); // 색상 검색시 낮은 가격순
	public List<ItemVO> getColorLatest(@Param("color")String color, @Param("vo") SearchPageVO vo); // 색상 검색시 최신순
	public List<ItemVO> getItemHPrice(@Param("itemName") String itemName, @Param("vo") SearchPageVO vo); // 이름 검색시 높은 가격순
	public List<ItemVO> getItemRPrice(@Param("itemName") String itemName, @Param("vo") SearchPageVO vo); // 이름 검색시 낮은 가격순
	public List<ItemVO> getItemLatest(@Param("itemName") String itemName, @Param("vo") SearchPageVO vo); // 이름 검색시 최신순
	public List<ItemVO> getItemColorHPrice(@Param("itemName") String itemName, @Param("color")String color, @Param("vo") SearchPageVO vo); // 색상, 이름 검색시 높은가격순
	public List<ItemVO> getItemColorRPrice(@Param("itemName") String itemName, @Param("color")String color, @Param("vo") SearchPageVO vo); // 색상, 이름 검색시 낮은가격순
	public List<ItemVO> getItemColorLatest(@Param("itemName") String itemName, @Param("color")String color, @Param("vo") SearchPageVO vo); // 색상, 이름 검색시 최신순
	//	public List<ItemVO> getColor(SearchPageVO SearchPageVO);
	public List<ItemVO> ColorWithPaging(@Param("color")String color,@Param("vo") SearchPageVO vo); // 색상 검색시 페이징처리
//	public List<ItemVO> getCategory(SearchPageVO SearchPageVO);
	public List<ItemVO> CategoryWithPaging(@Param("categoryNum") int categoryNum, @Param("vo")SearchPageVO SearchPageVO); // 카테고리 페이징처리
//	public List<ItemVO> getItemSearch(SearchPageVO SearchPageVO);
	public List<ItemVO> getItemSearchWithPaging(@Param("itemName") String itemName, @Param("vo")SearchPageVO SearchPageVO);	// 이름 검색시 페이징처리
	public List<ItemVO> getItemColorWithPaging(@Param("itemName") String itemName, @Param("color")String color, @Param("vo")SearchPageVO SearchPageVO); // 색상, 이름 검색시 페이징처리
	public List<ItemVO> getSearchAllWithPaging(SearchPageVO SearchPageVO); // 전체 리스트 페이징처리
	public int getItemNameCount(@Param("itemName")String itemName); //이름 검색시 상품개수
	public int getColorCount(@Param("color")String color); //색상 검색시 상품개수
	public int getItemColorCount(@Param("itemName") String itemName, @Param("color")String color); //이름, 색상 검색시 상품개수
	public int getCategoryCount(@Param("categoryNum") int categoryNum); //카테고리 검색시 상품개수
	public int getTotalCount();	 // 전체상품 개수

}
