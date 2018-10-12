package com.zjut.mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.mall.common.pojo.SearchItem;
import com.zjut.mall.search.mapper.SearchItemMapper;
import com.zjut.mall.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SolrServer solrServer;

	@Autowired
	private SearchItemMapper searchItemMapper;

	@Override
	public MallResult importItemsToIndex() {
		// 数据库中查询到相关信息
		// 将查询到的所有商品信息添加到索引库中
		try {
			List<SearchItem> itemList = searchItemMapper.getItemList();
			for (SearchItem searchItem : itemList) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				document.addField("item_desc", searchItem.getItem_desc());
				solrServer.add(document);
			}
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return MallResult.build(500, "数据导入失败");
		}
		return MallResult.ok();
	}

}
