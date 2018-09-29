package com.zjut.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.common.pojo.MallResult;
import com.zjut.mall.content.service.ContentService;
import com.zjut.mall.pojo.TbContent;

@RestController
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/save")
	public MallResult addContent(TbContent content) {
		return contentService.addContent(content);
	}
	
}
