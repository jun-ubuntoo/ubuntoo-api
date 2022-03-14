package com.ubuntoo.graphql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubuntoo.elasticsearch.ElasticSearchHelper;
import com.ubuntoo.mappers.SearchResultMapper;
import com.ubuntoo.mappers.SearchResultMappers;
import com.ubuntoo.pojo.SearchCategory;
import com.ubuntoo.pojo.SearchCriteria;
import com.ubuntoo.pojo.SearchCriteriaFilterValues;
import com.ubuntoo.pojo.SearchFilter;
import com.ubuntoo.pojo.SearchFilterValue;
import com.ubuntoo.pojo.SearchResultView;
import com.ubuntoo.pojo.SearchResults;
import com.ubuntoo.staticdata.SearchCategories;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class SearchGraphQLResolver implements GraphQLQueryResolver {
	static final boolean SHOW_ALL = true;
	static final String PART_OF_FIELD = "part_of";
	static final String PART_OF_BOTH = "both";
	static final String STATUS_FIELD_FIND = "status";
	static final String GH_FIELD = "greenhouses";
	
	static final String NEWS_CAT = "news";
	static final String KNOWLEDGE_CAT = "knowledge";
	static final String EVENTS_CAT = "events";
	static final String SOLUTIONS_CAT = "solutions";
	static final String GREENHOUSES_CAT = "greenhouses";
	
	static final String[] SEARCH_FIELDS =  new String[]{"name.analyzed", "short_bio.analyzed", "about.analyzed", "keyword_tags.analyzed"};

	public List<SearchFilter> searchFilters(String category, DataFetchingEnvironment env) throws Exception {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();

		int greenhouse = 0;
		SearchCategory cat = SearchCategories.getInstance().get(category);
		if (StringUtils.isBlank(category) || cat==null) {
			throw new Exception("Invalid search category");
		}

		JSONArray filterItems = new JSONArray();
		JSONObject filterItem;
		if (!SHOW_ALL) {
			filterItem = new JSONObject();
			filterItem.put("name", cat.getElasticSearchStatusField());
			filterItem.put("values", new JSONArray());
			filterItem.getJSONArray("values").put(cat.getElasticSearchStatusActive());
			filterItems.put(filterItem);
		}

		if (!SHOW_ALL && greenhouse<=0) {
			filterItem = new JSONObject();
			filterItem.put("name", PART_OF_FIELD);
			filterItem.put("values", new JSONArray());
			filterItem.getJSONArray("values").put(PART_OF_BOTH);
			filterItems.put(filterItem);
		} 
		
		if (StringUtils.equals(category, SOLUTIONS_CAT)) {
			filters.add(new SearchFilter("search", "Search by Location", "Location", "location", null));
			filters.add(new SearchFilter("checkbox", "", "Status", cat.getElasticSearchStatusField(), null));
			filters.add(new SearchFilter("checkbox", "", "Part of", PART_OF_FIELD, null));
			filters.add(new SearchFilter("checkbox", "", "Organization Type", "organization_type", null));
			filters.add(new SearchFilter("checkbox", "", "Stage of Development", "stage_of_development", null));
			filters.add(new SearchFilter("checkbox", "", "Value Chain Impact", "value_chain_impacts", null));
			filters.add(new SearchFilter("checkbox", "", "Seeking", "seekings", null));
		} else if (StringUtils.equals(category, KNOWLEDGE_CAT)) {
				filters.add(new SearchFilter("checkbox", "", "Status", cat.getElasticSearchStatusField(), null));
				filters.add(new SearchFilter("checkbox", "", "Part of", PART_OF_FIELD, null));
				filters.add(new SearchFilter("checkbox", "", "Subject", "subjects", null));
				filters.add(new SearchFilter("checkbox", "", "Format", "formats", null));
				filters.add(new SearchFilter("checkbox", "", "Category", "categories", null));
				filters.add(new SearchFilter("checkbox", "", "Industry", "topics", null));
			}
		
		if (filters.size()>0) {
			String aggs = "";
			for (SearchFilter filter : filters) {
				if (StringUtils.equals(filter.getType(), "checkbox")) {
					if (aggs.length()>0) aggs+=",";
					aggs += filter.getField();
				}
			}
			if (aggs.length()>0) {
				JSONObject query = ElasticSearchHelper.getInstance().generateQuery(greenhouse, "", SEARCH_FIELDS, filterItems, "", aggs, 0, 0);
				JSONObject result = ElasticSearchHelper.getInstance().execute(cat.getElasticSearchUrl(), query.toString());
				int totalCount = result.getJSONObject("hits").getJSONObject("total").getInt("value");
				JSONObject aggregations = result.getJSONObject("aggregations");
				//System.out.println("TOTAL COUNT: "+totalCount);

				for (SearchFilter filter : filters) {
					if (StringUtils.equals(filter.getType(), "checkbox") && aggregations.has(filter.getField())) {
						JSONArray buckets = aggregations.getJSONObject(filter.getField()).getJSONArray("buckets");
						for (int i=0; i<buckets.length(); i++) {
							JSONObject bucket = buckets.getJSONObject(i);
							filter.getItems().add(new SearchFilterValue(bucket.getString("key"), bucket.getString("key"), bucket.getInt("doc_count")));
						}
					}
				}
			}
		}
		
		return filters;
	}

	public SearchResults search(String jsonCriteria, DataFetchingEnvironment env) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SearchCriteria criteria = objectMapper.readValue(jsonCriteria, SearchCriteria.class);

		return searchInternal(criteria, env);
	}
	
	public SearchResults searchInternal(SearchCriteria criteria, DataFetchingEnvironment env) throws Exception {
		int greenhouse = 0;

		SearchResultMapper mapper = SearchResultMappers.getInstance().get(criteria.getCategory());
		SearchCategory cat = SearchCategories.getInstance().get(criteria.getCategory());
		if (StringUtils.isBlank(criteria.getCategory()) || cat==null || mapper==null) {
			throw new Exception("Invalid search category");
		}
		System.out.println(criteria.toString());
		
		JSONArray filterItems = new JSONArray();
		for (SearchCriteriaFilterValues filterValues : criteria.getFilters()) {
			if (filterValues.getValues().length>0) {
				JSONObject filterItem = new JSONObject();
				
				// do not add greenhouses or status terms
					if ((SHOW_ALL || !StringUtils.containsIgnoreCase(filterValues.getName(), STATUS_FIELD_FIND)) && !StringUtils.equals(filterValues.getName(), GH_FIELD)) {
						filterItem.put("name", filterValues.getName());
						filterItem.put("values", new JSONArray(filterValues.getValues()));
						filterItems.put(filterItem);
					}
			}			
		}

		JSONObject filterItem;
		if (!SHOW_ALL) {
			filterItem = new JSONObject();
			filterItem.put("name", cat.getElasticSearchStatusField());
			filterItem.put("values", new JSONArray());
			filterItem.getJSONArray("values").put(cat.getElasticSearchStatusActive());
			filterItems.put(filterItem);
		}

		if (!SHOW_ALL && greenhouse<=0) {
			filterItem = new JSONObject();
			filterItem.put("name", PART_OF_FIELD);
			filterItem.put("values", new JSONArray());
			filterItem.getJSONArray("values").put(PART_OF_BOTH);
			filterItems.put(filterItem);
		} 
		
		JSONObject query = ElasticSearchHelper.getInstance().generateQuery(
				greenhouse, 
				criteria.getSearchText(), 
				SEARCH_FIELDS,
				filterItems, 
				criteria.getSort(), 
				criteria.getAggregates(), 
				criteria.getStartAt(), 
				criteria.getPageSize());
		
		System.out.println(query.toString(5));
		
		JSONObject result = ElasticSearchHelper.getInstance().execute(cat.getElasticSearchUrl(), query.toString());
		int totalCount = result.getJSONObject("hits").getJSONObject("total").getInt("value");
		JSONArray hits = result.getJSONObject("hits").getJSONArray("hits");
		
		List<SearchResultView> searchResultItems = new ArrayList<SearchResultView>();
		for (int i=0; i<hits.length(); i++) {
			JSONObject hitTop = hits.getJSONObject(i);
			JSONObject hit = hitTop.getJSONObject("_source");
			double score = 0;
			if (hitTop.has(ElasticSearchHelper.SCORE_FIELD) && !hitTop.isNull(ElasticSearchHelper.SCORE_FIELD)) {
				score = hitTop.getDouble(ElasticSearchHelper.SCORE_FIELD);
			}
			searchResultItems.add(mapper.jsonToObject(hitTop.getString(ElasticSearchHelper.ID_FIELD), score, hit));
		}
		
		SearchResults searchResults = new SearchResults(totalCount, searchResultItems);
		
		
		return searchResults;
	}
}
