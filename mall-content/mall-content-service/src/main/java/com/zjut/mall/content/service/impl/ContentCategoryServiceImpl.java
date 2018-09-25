package com.zjut.mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.EasyUITreeNode;
import com.mall.common.pojo.MallResult;
import com.zjut.mall.content.service.ContentCategoryService;
import com.zjut.mall.dao.TbContentCategoryMapper;
import com.zjut.mall.pojo.TbContentCategory;
import com.zjut.mall.pojo.TbContentCategoryExample;
import com.zjut.mall.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> result = new ArrayList<>();
		for(TbContentCategory category : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

	@Override
	public MallResult addContentCategory(Long parentId, String name) {
		TbContentCategory category = new TbContentCategory();
		category.setIsParent(false);
		category.setParentId(parentId);
		category.setName(name);
		category.setStatus(1);
		category.setSortOrder(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		contentCategoryMapper.insert(category);
		
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		return MallResult.ok(category);
	}

}
