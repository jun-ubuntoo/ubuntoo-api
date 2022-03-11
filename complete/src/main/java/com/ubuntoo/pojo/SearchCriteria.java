package com.ubuntoo.pojo;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
	private String category;
	private String searchText;
	private String sort;
	private String aggregates;
	private List<SearchCriteriaFilterValues> filters = new ArrayList<SearchCriteriaFilterValues>();
	
	private int pageSize;
	private int startAt;

	public SearchCriteria() {
	
	}

	public SearchCriteria(String category, String searchText, String sort, String aggregates, int pageSize, int startAt, List<SearchCriteriaFilterValues> filters) {
		this.category = category;
		this.searchText = searchText;
		this.sort = sort;
		this.aggregates = aggregates;
		this.filters = filters;
		this.pageSize = pageSize;
		this.startAt = startAt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAggregates() {
		return aggregates;
	}

	public void setAggregates(String aggregates) {
		this.aggregates = aggregates;
	}

	public List<SearchCriteriaFilterValues> getFilters() {
		return filters;
	}

	public void setFilters(List<SearchCriteriaFilterValues> filters) {
		this.filters = filters;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartAt() {
		return startAt;
	}

	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}
	
	
}