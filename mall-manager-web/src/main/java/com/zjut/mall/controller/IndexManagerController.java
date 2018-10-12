package com.zjut.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mall.common.pojo.MallResult;
import com.zjut.mall.search.service.SearchItemService;

@RestController
public class IndexManagerController {

	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/import")
	public MallResult importIndex() {
		return searchItemService.importItemsToIndex();
	}
}
