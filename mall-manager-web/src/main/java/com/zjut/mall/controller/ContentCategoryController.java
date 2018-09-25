package com.zjut.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.common.pojo.EasyUITreeNode;
import com.mall.common.pojo.MallResult;
import com.zjut.mall.content.service.ContentCategoryService;

@RestController
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0") Long parentId) {
		return contentCategoryService.getContentCategoryList(parentId);
	}
	
	@RequestMapping("/content/category/create")
	public MallResult addContentCategory(Long parentId, String name) {
		return contentCategoryService.addContentCategory(parentId, name);
	}
}
