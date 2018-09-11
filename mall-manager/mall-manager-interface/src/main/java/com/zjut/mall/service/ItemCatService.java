package com.zjut.mall.service;

import java.util.List;

import com.mall.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	/**
	 * 根据父id获取子列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getItemCatList(Long parentId);
}
