package com.ubuntoo.pojo;

import org.apache.commons.lang3.StringUtils;

public class SearchCategory {
	private String name;
	private String homebandTitle;
	private String viewAllUrl;
	private String elasticSearchUrl;
	private String elasticSearchStatusField;
	private String elasticSearchStatusActive = "active";
	private String elasticSearchPartofField;
	private String elasticSearchPartofFieldBoth = "both";
	private String[] searchTextFields = new String[]{};
	
	public SearchCategory(String name, String homebandTitle, String viewAllUrl, String elasticSearchUrl,
			String elasticSearchStatusField, String elasticSearchStatusActive, String[] searchTextFields,
			String elasticSearchPartofField, String elasticSearchPartofFieldBoth) {
		super();
		this.name = name;
		this.homebandTitle = homebandTitle;
		this.viewAllUrl = viewAllUrl;
		this.elasticSearchUrl = elasticSearchUrl;
		this.elasticSearchStatusField = elasticSearchStatusField;
		this.elasticSearchStatusActive = elasticSearchStatusActive;
		this.searchTextFields = searchTextFields;
		this.elasticSearchPartofField = elasticSearchPartofField;
		this.elasticSearchPartofFieldBoth = elasticSearchPartofFieldBoth;
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

	public String[] getSearchTextFields() {
		return searchTextFields;
	}

	public void setSearchTextFields(String[] searchTextFields) {
		this.searchTextFields = searchTextFields;
	}

	public String getElasticSearchPartofField() {
		return elasticSearchPartofField;
	}

	public void setElasticSearchPartofField(String elasticSearchPartofField) {
		this.elasticSearchPartofField = elasticSearchPartofField;
	}

	public String getElasticSearchPartofFieldBoth() {
		return elasticSearchPartofFieldBoth;
	}

	public void setElasticSearchPartofFieldBoth(String elasticSearchPartofFieldBoth) {
		this.elasticSearchPartofFieldBoth = elasticSearchPartofFieldBoth;
	}
	
	
}
