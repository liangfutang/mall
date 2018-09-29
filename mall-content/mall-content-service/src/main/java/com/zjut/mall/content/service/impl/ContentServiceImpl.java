package com.zjut.mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.zjut.mall.content.service.ContentService;
import com.zjut.mall.dao.TbContentMapper;
import com.zjut.mall.pojo.TbContent;
import com.zjut.mall.pojo.TbContentExample;
import com.zjut.mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public MallResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return MallResult.ok();
	}

	@Override
	public List<TbContent> getContentByCid(Long cid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		
		List<TbContent> list = contentMapper.selectByExample(example);
		
		return list;
	}
	
	
}
