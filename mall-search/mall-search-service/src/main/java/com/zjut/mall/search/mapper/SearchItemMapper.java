package com.zjut.mall.search.mapper;

import java.util.List;

import com.mall.common.pojo.SearchItem;

public interface SearchItemMapper {

	/**
	 * 获取商品item列表
	 * @return
	 */
	List<SearchItem> getItemList();

	/**
	 * 通过id获取相应的商品item
	 * 
	 * @param itemId
	 * @return
	 */
	SearchItem getItemById(long itemId);
}
