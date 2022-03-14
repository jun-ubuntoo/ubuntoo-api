package com.ubuntoo.pojo;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
	private int count;
	private List<SearchResultView> items;

	public SearchResults() {
		super();
	}

	public SearchResults(int count, List<SearchResultView> items) {
		if (items==null) {
			items = new ArrayList<SearchResultView>();
		}
		this.count = count;
		this.items = items;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<SearchResultView> getItems() {
		return items;
	}

	public void setItems(List<SearchResultView> items) {
		this.items = items;
	}

	
}