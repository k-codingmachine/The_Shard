package com.shard.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shard.domain.ItemVO;
import com.shard.domain.PageVO;

public interface ItemSearchService {

	public List<ItemVO> Search(PageVO pagevo);
	public List<ItemVO> AllLatest(PageVO vo);
	public List<ItemVO> RPrice(PageVO vo);
	public List<ItemVO> HPrice(PageVO vo);
	public List<ItemVO> getCategoryHPrice(int categoryNum, PageVO vo);
	public List<ItemVO> getCategoryRPrice(int categoryNum, PageVO vo);
	public List<ItemVO> getCategoryLatest(int categoryNum, PageVO vo);	
	public List<ItemVO> getColorHPrice(String color, PageVO vo);
	public List<ItemVO> getColorRPrice(String color, PageVO vo);
	public List<ItemVO> getColorLatest(String color, PageVO vo);
	public List<ItemVO> getItemHPrice(String itemName, PageVO vo);
	public List<ItemVO> getItemRPrice(String itemName, PageVO vo);
	public List<ItemVO> getItemLatest(String itemName, PageVO vo);
	public List<ItemVO> getItemColorHPrice(String itemName, String color, PageVO vo);
	public List<ItemVO> getItemColorRPrice(String itemName, String color, PageVO vo);
	public List<ItemVO> getItemColorLatest(String itemName, String color, PageVO vo);
	//	public List<ItemVO> Color(@Param("itemName") String itemName);
	public List<ItemVO> ColorWithPaging(String color, PageVO pageVO);
//	public List<ItemVO> Category(@Param("itemName") int categoryNum);
	public List<ItemVO> CategoryWithPaging(int categoryNum, PageVO pageVO);
//	public List<ItemVO> ItemSearch(@Param("itemName") String itemName);
	public List<ItemVO> ItemSearchWithPaging(String itemName, PageVO pageVO);
//	public List<ItemVO> searchWithPaging(PageVO pageVO);
	public List<ItemVO> getItemColorWithPaging(String itemName, String color, PageVO pagevo);
	public int getItemNameCount(String itemName);
	public int getColorCount(String color);
	public int getItemColorCount(String itemName, @Param("color")String color);
	public int getCategoryCount(int categoryNum);
	public int getTotalCount();
}
