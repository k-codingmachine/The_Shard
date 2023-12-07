package com.shard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shard.domain.ItemVO;
import com.shard.domain.WishListVO;

public interface ItemService {
	public ItemVO getItem(int itemNum);
	
	public int itemInsert(ItemVO vo, List<MultipartFile> file);
	
	public void itemDelete(int itemNum);
	
	public int itemUpdate(ItemVO vo, List<MultipartFile> file);
	
	public int wishListSelect(int itemNum, String email);
	
	public void wishListInsert(int itemNum, String email);
	
	public void wishListDelete(int itemNum, String email);
}
 