package com.zjut.mall.search.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.common.pojo.SearchItem;
import com.zjut.mall.search.mapper.SearchItemMapper;

/**
 * 监听器:接收添加商品的信息，，xml配置中已经将该实体注入到容器中，只需要在监听器中做出相应的动作
 * 
 * @author liangfutang
 *
 */
public class ItemAddMessageListener implements MessageListener {

	@Autowired
	private SearchItemMapper searchItemMapper;

	@Autowired
	private SolrServer solrServer;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println("打印出收到的消息:" + text);
			long itemId = Long.parseLong(text);
			// 根据商品id查询数据，取商品信息
			// 等待事务提交
			Thread.sleep(1000);  // 防止那边的事务还没提交但是消息已经发送了，造成这边无法查找到相应的数据
			SearchItem searchItem = searchItemMapper.getItemById(itemId);
			// 创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			// 向文档对象中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			// 把文档对象写入索引库
			solrServer.add(document);
			// 提交
			solrServer.commit();
			System.out.println("消息提交到索引库中");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
