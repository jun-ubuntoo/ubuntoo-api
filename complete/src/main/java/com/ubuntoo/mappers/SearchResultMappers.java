package com.ubuntoo.mappers;

import java.util.HashMap;
import java.util.Map;

public class SearchResultMappers {
	private static SearchResultMappers instance = new SearchResultMappers(); 
	private Map<String, SearchResultMapper> mappers = new HashMap<String, SearchResultMapper>();
	
	private SearchResultMappers() {
	}
	
	public static SearchResultMappers getInstance() {
		return instance;
	}
	
	public void add(String category, SearchResultMapper mapper) {
		mappers.put(category, mapper);
	}
	public SearchResultMapper get(String category) {
		return mappers.get(category);
	}
	public boolean has(String category) {
		return mappers.containsKey(category);
	}
}
