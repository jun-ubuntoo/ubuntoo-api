package com.ubuntoo.pojo;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteriaFilterValues {
	private String name;
	private String[] values = new String[0];

	public SearchCriteriaFilterValues() {
	}

	public SearchCriteriaFilterValues(String name, String[] values) {
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "SearchCriteriaFilterValues [name=" + name + ", values=" + values + "]";
	}
	
}