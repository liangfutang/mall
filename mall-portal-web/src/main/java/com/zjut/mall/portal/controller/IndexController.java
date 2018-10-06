package com.zjut.mall.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.zjut.mall.content.service.ContentService;
import com.zjut.mall.pojo.TbContent;
import com.zjut.mall.portal.pojo.AD1Node;

@Controller
public class IndexController {

	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 根据cid查找到轮播图
		List<TbContent> contentList = contentService.getContentByCid(AD1_CATEGORY_ID);
		// 封装到结果显示
		List<AD1Node> result = new ArrayList<>();
		for(TbContent content : contentList) {
			AD1Node node = new AD1Node();
			node.setAlt(content.getTitle());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			node.setSrc(content.getPic());
			node.setSrcB(content.getPic2());
			node.setHref(content.getUrl());
			//添加到节点列表
			result.add(node);
		}
		String resultJson = JSONObject.toJSONString(result);
		model.addAttribute("ad1", resultJson);
		return "index";
	}
}
