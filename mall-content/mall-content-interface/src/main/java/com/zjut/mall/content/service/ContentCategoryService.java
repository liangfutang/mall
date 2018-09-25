package com.zjut.mall.content.service;

import java.util.List;

import com.mall.common.pojo.EasyUITreeNode;

public interface ContentCategoryService {

	public List<EasyUITreeNode> getContentCategoryList(Long parentId);
	
}
