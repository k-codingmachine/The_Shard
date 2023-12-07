package com.shard.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shard.domain.ItemVO;
import com.shard.domain.PageVO;

public interface ItemSearchMapper {

	public List<ItemVO> getSearchAll(PageVO vo);
	public List<ItemVO> getSearchAllLatest(PageVO vo);
	public List<ItemVO> getRPrice(PageVO vo);
	public List<ItemVO> getHPrice(PageVO vo);
	public List<ItemVO> getCategoryHPrice(@Param("categoryNum") int categoryNum, @Param("vo") PageVO vo);
	public List<ItemVO> getCategoryRPrice(@Param("categoryNum") int categoryNum, @Param("vo") PageVO vo);
	public List<ItemVO> getCategoryLatest(@Param("categoryNum") int categoryNum, @Param("vo") PageVO vo);	
	public List<ItemVO> getColorHPrice(@Param("color")String color, @Param("vo") PageVO vo);
	public List<ItemVO> getColorRPrice(@Param("color")String color, @Param("vo") PageVO vo);
	public List<ItemVO> getColorLatest(@Param("color")String color, @Param("vo") PageVO vo);
	public List<ItemVO> getItemHPrice(@Param("itemName") String itemName, @Param("vo") PageVO vo);
	public List<ItemVO> getItemRPrice(@Param("itemName") String itemName, @Param("vo") PageVO vo);
	public List<ItemVO> getItemLatest(@Param("itemName") String itemName, @Param("vo") PageVO vo);
	public List<ItemVO> getItemColorHPrice(@Param("itemName") String itemName, @Param("color")String color, @Param("vo") PageVO vo);
	public List<ItemVO> getItemColorRPrice(@Param("itemName") String itemName, @Param("color")String color, @Param("vo") PageVO vo);
	public List<ItemVO> getItemColorLatest(@Param("itemName") String itemName, @Param("color")String color, @Param("vo") PageVO vo);
	//	public List<ItemVO> getColor(PageVO pageVO);
	public List<ItemVO> ColorWithPaging(@Param("color")String color,@Param("vo") PageVO vo);
//	public List<ItemVO> getCategory(PageVO pageVO);
	public List<ItemVO> CategoryWithPaging(@Param("categoryNum") int categoryNum, @Param("vo")PageVO pageVO);
//	public List<ItemVO> getItemSearch(PageVO pageVO);
	public List<ItemVO> getItemSearchWithPaging(@Param("itemName") String itemName, @Param("vo")PageVO pageVO);	
	public List<ItemVO> getItemColorWithPaging(@Param("itemName") String itemName, @Param("color")String color, @Param("vo")PageVO pagevo);
	public List<ItemVO> getSearchAllWithPaging(PageVO pagevo);
	public int getItemNameCount(@Param("itemName")String itemName);
	public int getColorCount(@Param("color")String color);
	public int getItemColorCount(@Param("itemName") String itemName, @Param("color")String color);
	public int getCategoryCount(@Param("categoryNum") int categoryNum);
	public int getTotalCount();	

}
