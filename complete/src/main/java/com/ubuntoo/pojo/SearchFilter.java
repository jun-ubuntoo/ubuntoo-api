package com.ubuntoo.pojo;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter {
	private String type;
	private String title;
	private String hint;
	private String field;
	private List<SearchFilterValue> items = new ArrayList<SearchFilterValue>();

	public SearchFilter() {
		
	}
	public SearchFilter(String type, String hint, String title, String field, List<SearchFilterValue> items) {
		if (items==null) {
			items = new ArrayList<SearchFilterValue>();
		}
		this.type = type;
		this.hint = hint;
		this.title = title;
		this.field = field;
		this.items = items;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<SearchFilterValue> getItems() {
		return items;
	}

	public void setItems(List<SearchFilterValue> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SearchFilter other = (SearchFilter) o;

		return field.equals(other.field);
	}

	@Override
	public int hashCode() {
		return field.hashCode();
	}
}

