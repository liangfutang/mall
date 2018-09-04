package com.zjut.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjut.mall.dao.TbItemMapper;
import com.zjut.mall.pojo.TbItem;
import com.zjut.mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

}
