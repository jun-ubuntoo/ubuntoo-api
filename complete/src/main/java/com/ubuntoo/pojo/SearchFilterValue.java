package com.ubuntoo.pojo;

public class SearchFilterValue {
	private String id;
	private String title;
	private int count;

	public SearchFilterValue(String id, String title, int count) {
		this.id = id;
		this.title = title;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}

