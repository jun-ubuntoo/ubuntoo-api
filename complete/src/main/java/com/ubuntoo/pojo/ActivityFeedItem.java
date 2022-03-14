package com.ubuntoo.pojo;

public class ActivityFeedItem {
	private String image;
	private String title;
	private String body;
	private String url;
	private long date;

	public ActivityFeedItem(String image, String title, String body, String url, long date) {
		this.image = image;
		this.title = title;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ActivityFeedItem spotlight = (ActivityFeedItem) o;

		return url.equals(spotlight.url);
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}
}
