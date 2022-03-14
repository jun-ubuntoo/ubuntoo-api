package com.ubuntoo.pojo;

public class Spotlight {
	private String image;
	private String title;
	private String contentType;
	private String body;
	private String url;

	public Spotlight(String image, String title, String contentType, String body, String url) {
		this.image = image;
		this.title = title;
		this.contentType = contentType;
		this.body = body;
		this.url = url;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Spotlight spotlight = (Spotlight) o;

		return url.equals(spotlight.url);
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}
}
