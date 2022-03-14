package com.ubuntoo.pojo;

public class SearchResultView {
	private String id;
	private String category;
	private String image;
	private String title;
	private String subtitle;
	private String body;
	private String url;
	private Long date;
	private double score;

	public SearchResultView() {
		super();
	}

	public SearchResultView(String id, String category, String image, String title, String subtitle, String body, String url, Long date, double score) {
		this.id = id;
		this.category = category;
		this.image = image;
		this.title = title;
		this.subtitle = subtitle;
		this.body = body;
		this.url = url;
		this.date = date;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SearchResultView other = (SearchResultView) o;

		return url.equals(other.url);
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}
}