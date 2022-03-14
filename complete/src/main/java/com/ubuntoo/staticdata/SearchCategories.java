package com.ubuntoo.staticdata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ubuntoo.pojo.SearchCategory;

public class SearchCategories {
	private static SearchCategories instance = new SearchCategories(); 
	private Map<String, SearchCategory> categories = new HashMap<String, SearchCategory>();
	
	private SearchCategories() {
	}
	
	public static SearchCategories getInstance() {
		return instance;
	}
	
	public void add(SearchCategory cat) {
		categories.put(cat.getName(), cat);
	}
	public void addAll(Collection<SearchCategory> cats) {
		for (SearchCategory cat : cats) {
			add(cat);
		}
	}
	public void clear() {
		categories.clear();
	}
	public void reload(Collection<SearchCategory> cats) {
		clear();
		addAll(cats);
	}
	
	public boolean has(String category) {
		return categories.containsKey(category);
	}
	
	public SearchCategory get(String category) {
		System.out.println(categories.keySet());
		return categories.get(category);
	}
	public List<SearchCategory> getAll() {
		return new ArrayList<SearchCategory>(categories.values());
	}
}
