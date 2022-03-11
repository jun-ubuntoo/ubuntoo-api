package com.ubuntoo.pojo;

import org.apache.commons.lang3.StringUtils;

public class SearchCategory {
	private String name;
	private String homebandTitle;
	private String viewAllUrl;
	private String elasticSearchUrl;
	private String elasticSearchStatusField;
	private String elasticSearchStatusActive = "active";
	
	public SearchCategory(String name, String homebandTitle, String viewAllUrl, String elasticSearchUrl,
			String elasticSearchStatusField, String elasticSearchStatusActive) {
		super();
		this.name = name;
		this.homebandTitle = homebandTitle;
		this.viewAllUrl = viewAllUrl;
		this.elasticSearchUrl = elasticSearchUrl;
		this.elasticSearchStatusField = elasticSearchStatusField;
		this.elasticSearchStatusActive = elasticSearchStatusActive;
	}
	
	public boolean isHomeband() {
		return StringUtils.isNotBlank(homebandTitle);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHomebandTitle() {
		return homebandTitle;
	}

	public void setHomebandTitle(String homebandTitle) {
		this.homebandTitle = homebandTitle;
	}

	public String getViewAllUrl() {
		return viewAllUrl;
	}

	public void setViewAllUrl(String viewAllUrl) {
		this.viewAllUrl = viewAllUrl;
	}

	public String getElasticSearchUrl() {
		return elasticSearchUrl;
	}

	public void setElasticSearchUrl(String elasticSearchUrl) {
		this.elasticSearchUrl = elasticSearchUrl;
	}

	public String getElasticSearchStatusField() {
		return elasticSearchStatusField;
	}

	public void setElasticSearchStatusField(String elasticSearchStatusField) {
		this.elasticSearchStatusField = elasticSearchStatusField;
	}

	public String getElasticSearchStatusActive() {
		return elasticSearchStatusActive;
	}

	public void setElasticSearchStatusActive(String elasticSearchStatusActive) {
		this.elasticSearchStatusActive = elasticSearchStatusActive;
	}
	
	
}
