package com.ubuntoo.pojo;

public class HomeBandItem {
	private String category;
	private String image;
	private String title;
	private String subtitle;
	private String body;
	private String url;
	private long date;

	public HomeBandItem(String category, String image, String title, String subtitle, String body, String url, long date) {
		this.category = category;
		this.image = image;
		this.title = title;
		this.subtitle = subtitle;
		this.body = body;
		this.url = url;
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HomeBandItem spotlight = (HomeBandItem) o;

		return url.equals(spotlight.url);
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}
}
