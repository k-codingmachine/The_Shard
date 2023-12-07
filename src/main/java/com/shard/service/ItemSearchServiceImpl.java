package com.shard.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shard.domain.ItemVO;
import com.shard.domain.PageVO;
import com.shard.mapper.ItemSearchMapper;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

	@Autowired
	private ItemSearchMapper mapper;
	
	@Override
	public List<ItemVO> Search(PageVO pagevo) {
		return mapper.getSearchAllWithPaging(pagevo);
	}
	
	@Override
	public List<ItemVO> AllLatest(PageVO vo) {
		return mapper.getSearchAllLatest(vo);
	}


	@Override
	public List<ItemVO> RPrice(PageVO vo) {
		return mapper.getRPrice(vo);
	}

	@Override
	public List<ItemVO> HPrice(PageVO vo) {
		return mapper.getHPrice(vo);
	}

	@Override
	public List<ItemVO> getCategoryHPrice(int categoryNum, PageVO vo) {
		return mapper.getCategoryHPrice(categoryNum, vo);
	}
	
	@Override
	public List<ItemVO> getCategoryRPrice(int categoryNum, PageVO vo) {
		return mapper.getCategoryRPrice(categoryNum, vo);
	}
	
	@Override
	public List<ItemVO> getCategoryLatest(int categoryNum, PageVO vo) {
		return mapper.getCategoryLatest(categoryNum, vo);
	}
	
	@Override
	public List<ItemVO> getColorHPrice(String color, PageVO vo) {
		return mapper.getColorHPrice(color, vo);
	}
	
	@Override
	public List<ItemVO> getColorRPrice(String color, PageVO vo) {
		return mapper.getColorRPrice(color, vo);
	}
	
	@Override
	public List<ItemVO> getColorLatest(String color, PageVO vo) {
		return mapper.getColorLatest(color, vo);
	}

	
	@Override
	public List<ItemVO> getItemHPrice(String itemName, PageVO vo) {
		return mapper.getItemHPrice(itemName, vo);
	}
	
	@Override
	public List<ItemVO> getItemRPrice(String itemName, PageVO vo) {
		return mapper.getItemRPrice(itemName, vo);
	}
	
	@Override
	public List<ItemVO> getItemLatest(String itemName, PageVO vo) {
		return mapper.getItemLatest(itemName, vo);
	}

	
	@Override
	public List<ItemVO> getItemColorHPrice(String itemName, String color, PageVO vo) {
		return mapper.getItemColorHPrice(itemName, color, vo);
	}
	
	
	@Override
	public List<ItemVO> getItemColorRPrice(String itemName, String color, PageVO vo) {
		return mapper.getItemColorRPrice(itemName, color, vo);
	}
	
	@Override
	public List<ItemVO> getItemColorLatest(String itemName, String color, PageVO vo) {
		return mapper.getItemColorLatest(itemName, color, vo);
	}
	
//	  @Override 
//	  public List<ItemVO> Color(String itemName) {
//		  return null; 
//	}
	 

//	@Override
//	public List<ItemVO> Category(int categoryNum) {
//		return null;
//	}

//	@Override
//	public List<ItemVO> ItemSearch(String itemName) {
//		return null;
//	}

//	@Override
//	public List<ItemVO> searchWithPaging(PageVO pageVO) {
//		return mapper.getItemSearchWithPaging(null, pageVO);
//	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public List<ItemVO> CategoryWithPaging(int categoryNum, PageVO pageVO) {
        return mapper.CategoryWithPaging(categoryNum, pageVO);
	}

	@Override
	public List<ItemVO> ColorWithPaging(String color, PageVO pageVO) {
		return mapper.ColorWithPaging(color, pageVO);
	}

	@Override
	public List<ItemVO> ItemSearchWithPaging(String itemName, PageVO pageVO) {
		return mapper.getItemSearchWithPaging(itemName, pageVO);
	}

	@Override
	public int getItemNameCount(String itemName) {
		return mapper.getItemNameCount(itemName);
	}

	@Override
	public int getColorCount(String color) {
		return mapper.getColorCount(color);
	}

	@Override
	public int getItemColorCount(String itemName, String color) {
		return mapper.getItemColorCount(itemName, color);
	}

	@Override
	public int getCategoryCount(int categoryNum) {
		return mapper.getCategoryCount(categoryNum);
	}

	@Override
	public List<ItemVO> getItemColorWithPaging(String itemName, String color, PageVO pagevo) {
		return mapper.getItemColorWithPaging(itemName, color, pagevo);
	}











	



}
