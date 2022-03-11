package com.ubuntoo.pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeBandCategory {
	private String category;
	private String title;
	private String url;
	private List<SearchResultView> items = new ArrayList<SearchResultView>();

	public HomeBandCategory(String category, String title, String url) {
		this.category = category;
		this.title = title;
		this.url = url;
	}

	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public List<SearchResultView> getItems() {
		return items;
	}



	public void setItems(List<SearchResultView> items) {
		this.items = items;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HomeBandCategory spotlight = (HomeBandCategory) o;

		return category.equals(spotlight.category);
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}
}
