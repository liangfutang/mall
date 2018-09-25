package com.zjut.mall.content.service;

import java.util.List;

import com.mall.common.pojo.EasyUITreeNode;
import com.mall.common.pojo.MallResult;

public interface ContentCategoryService {

	public List<EasyUITreeNode> getContentCategoryList(Long parentId);
	
	public MallResult addContentCategory(Long parentId, String name);
}
