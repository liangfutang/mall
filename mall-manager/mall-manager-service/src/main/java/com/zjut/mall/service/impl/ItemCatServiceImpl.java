package com.zjut.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.EasyUITreeNode;
import com.zjut.mall.dao.TbItemCatMapper;
import com.zjut.mall.pojo.TbItemCat;
import com.zjut.mall.pojo.TbItemCatExample;
import com.zjut.mall.pojo.TbItemCatExample.Criteria;
import com.zjut.mall.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EasyUITreeNode> result = new ArrayList<>();
		for(TbItemCat itemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed":"open");
			result.add(node);
		}  
		return result;
	}

}
