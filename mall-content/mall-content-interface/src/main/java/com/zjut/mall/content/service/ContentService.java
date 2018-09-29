package com.zjut.mall.content.service;

import java.util.List;

import com.mall.common.pojo.MallResult;
import com.zjut.mall.pojo.TbContent;

public interface ContentService {

	MallResult addContent(TbContent content);
	
	List<TbContent> getContentByCid(Long cid);
}
