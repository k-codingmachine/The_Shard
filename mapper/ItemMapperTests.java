package com.shard.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ItemMapperTests {

	@Autowired
	private ItemMapper mapper;
	
	@Test
	public void testwishListSelect() {
	 mapper.wishListSelect(1, "user1@admin.com");
	}
	
	@Test
	public void testwishListInsert() {
	mapper.wishListInsert(3, "user1@admin.com");
	}
	
	@Test
	public void testwishListDelete() {
	mapper.wishListDelete(3, "user1@admin.com");
	}
}
