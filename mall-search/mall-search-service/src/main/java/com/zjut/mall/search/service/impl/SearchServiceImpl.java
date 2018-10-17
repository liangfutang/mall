package com.zjut.mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.SearchResult;
import com.zjut.mall.search.dao.SearchDao;
import com.zjut.mall.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);
		if (page < 1)
			page = 1;
		query.setStart((page - 1) * rows);
		if (rows < 1)
			rows = 10;
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_title");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		// 调用dao执行查询
		SearchResult searchResult = searchDao.search(query);
		// 计算查询结果的总页数
		long recordCount = searchResult.getRecordCount();
		long pages = recordCount / rows;
		if (recordCount % rows > 0) {
			pages++;
		}
		searchResult.setTotalPages(pages);
		// 返回结果
		return searchResult;
	}

}
