package com.zjut.mall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.EasyUITreeNode;
import com.zjut.mall.service.ItemCatService;

@Controller
@RequestMapping
public class ItemCatController {

	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0") Long parentId) {
		return itemCatService.getItemCatList(parentId);
	}
}
