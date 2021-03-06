package com.zjut.mall.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.EasyUIDataGridResult;
import com.mall.common.pojo.MallResult;
import com.mall.common.utils.IDUtils;
import com.zjut.mall.dao.TbItemDescMapper;
import com.zjut.mall.dao.TbItemMapper;
import com.zjut.mall.pojo.TbItem;
import com.zjut.mall.pojo.TbItemDesc;
import com.zjut.mall.pojo.TbItemExample;
import com.zjut.mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource(name="itemAddtopic")
	private Topic topic;
	
	@Override
	public TbItem getItemById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public MallResult addItem(TbItem item, String desc) {
		final Long id = IDUtils.genItemId();
		item.setId(id);
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		// 向activemq发送添加的商品消息
		jmsTemplate.send(topic, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("" + id);
				return textMessage;
			}
		});
		
		return MallResult.ok();
	}

}
