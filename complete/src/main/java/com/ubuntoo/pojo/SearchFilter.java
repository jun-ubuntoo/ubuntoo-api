package com.ubuntoo.pojo;

public class SearchFilter {
	private String type;
	private String title;
	private String hint;
	private String field;

	public SearchFilter(String type, String hint, String title, String field) {
		this.type = type;
		this.hint = hint;
		this.title = title;
		this.field = field;
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

