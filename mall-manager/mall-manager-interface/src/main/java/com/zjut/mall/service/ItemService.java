package com.zjut.mall.service;

import com.mall.common.pojo.EasyUIDataGridResult;
import com.mall.common.pojo.MallResult;
import com.zjut.mall.pojo.TbItem;

public interface ItemService {

	public TbItem getItemById(Long id);

	/**
	 * 获取商品列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Integer page, Integer rows);

	/**
	 * 添加商品
	 * 
	 * @param item
	 * @param desc
	 * @return
	 */
	public MallResult addItem(TbItem item, String desc);
}
