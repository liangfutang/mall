package com.zjut.mall.search.search;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

public class TestSolr {

	SolrServer solrServer = null;

	@Before
	public void before() {
		solrServer = new HttpSolrServer("http://localhost:8983/solr/new_core");
	}

	@Test
	public void addDocument() throws SolrServerException, IOException {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "123");
		document.addField("item_title", "测试商品3");
		document.addField("item_price", 1000);
		solrServer.add(document);
		solrServer.commit();
	}

	@Test
	public void deleteDocumentById() throws SolrServerException, IOException {
		solrServer.deleteById("test001");
		solrServer.commit();
	}
	
	@Test
	public void searchDocument() throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery("一");
		query.setStart(0);
		query.setRows(10);
		query.set("df", "item_keywords");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<div>");
		query.setHighlightSimplePost("</div>");
		QueryResponse response = solrServer.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = "";
			if (list != null && list.size() >0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) solrDocument.get("item_title");
			}
			System.out.println(itemTitle);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
			System.out.println("=============================================");
		}
	}
}
