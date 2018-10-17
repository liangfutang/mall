package com.zjut.mall.search.service;

import com.mall.common.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}
