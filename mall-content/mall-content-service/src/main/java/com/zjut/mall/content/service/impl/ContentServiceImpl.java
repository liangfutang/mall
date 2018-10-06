package com.zjut.mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mall.common.pojo.MallResult;
import com.zjut.mall.content.service.ContentService;
import com.zjut.mall.dao.TbContentMapper;
import com.zjut.mall.jedis.JedisClient;
import com.zjut.mall.pojo.TbContent;
import com.zjut.mall.pojo.TbContentExample;
import com.zjut.mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${INDEX_CONTENT}")
	private String INDEX_CONTENT;

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public MallResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		// 同步缓存
		// 删除对应的缓存信息
		jedisClient.hdel(INDEX_CONTENT, content.getCategoryId().toString());
		return MallResult.ok();
	}

	@Override
	public List<TbContent> getContentByCid(Long cid) {
		try {
			// 查询缓存
			String json = jedisClient.hget(INDEX_CONTENT, cid + "");
			// 查询到结果，把json转换成List返回
			if (StringUtils.isNotBlank(json)) {
				//List<TbContent> list = JSONObject.parseObject(json, List.class);
				List<TbContent> list = JSONObject.parseArray(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);

		List<TbContent> list = contentMapper.selectByExample(example);

		try {
			jedisClient.hset(INDEX_CONTENT, cid + "", JSONObject.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
