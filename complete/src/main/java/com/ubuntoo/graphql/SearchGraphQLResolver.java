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
import com.ubuntoo.pojo.SearchResultView;
import com.ubuntoo.staticdata.SearchCategories;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class SearchGraphQLResolver implements GraphQLQueryResolver {
	static final String PART_OF_FIELD = "part_of";
	static final String PART_OF_BOTH = "both";
	static final String STATUS_FIELD_FIND = "status";
	static final String GH_FIELD = "greenhouses";
	
	static final String NEWS_CAT = "news";
	static final String KNOWLEDGE_CAT = "knowledge";
	static final String EVENTS_CAT = "events";
	static final String SOLUTIONS_CAT = "solutions";
	static final String GREENHOUSES_CAT = "greenhouses";

	public List<SearchFilter> searchFilters(String jsonCriteria, DataFetchingEnvironment env) {
		
		return new ArrayList<SearchFilter>();
	}

	public List<SearchResultView> search(String jsonCriteria, DataFetchingEnvironment env) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SearchCriteria criteria = objectMapper.readValue(jsonCriteria, SearchCriteria.class);

		return searchInternal(criteria, env);
	}
	
	public List<SearchResultView> searchInternal(SearchCriteria criteria, DataFetchingEnvironment env) throws Exception {
		int greenhouse = 0;

		SearchResultMapper mapper = SearchResultMappers.getInstance().get(criteria.getCategory());
		SearchCategory cat = SearchCategories.getInstance().get(criteria.getCategory());
		if (StringUtils.isBlank(criteria.getCategory()) || cat==null || mapper==null) {
			throw new Exception("Invalid search category");
		}
		
		
		JSONArray filterItems = new JSONArray();
		for (SearchCriteriaFilterValues filterValues : criteria.getFilters()) {
			if (filterValues.getValues().size()>0) {
				JSONObject filterItem = new JSONObject();
				
				// do not add greenhouses or status terms
				if (!StringUtils.containsIgnoreCase(filterValues.getName(), STATUS_FIELD_FIND) && !StringUtils.equals(filterValues.getName(), GH_FIELD)) {
					filterItem.put("name", filterValues.getName());
					filterItem.put("values", new JSONArray(filterValues.getValues()));
					filterItems.put(filterItem);
				}
			}			
		}

		JSONObject filterItem = new JSONObject();
		filterItem.put("name", cat.getElasticSearchStatusField());
		filterItem.put("values", new JSONArray());
		filterItem.getJSONArray("values").put(cat.getElasticSearchStatusActive());
		filterItems.put(filterItem);

		if (greenhouse<=0) {
			filterItem = new JSONObject();
			filterItem.put("name", PART_OF_FIELD);
			filterItem.put("values", new JSONArray());
			filterItem.getJSONArray("values").put(PART_OF_BOTH);
			filterItems.put(filterItem);
		} 
		
		JSONObject query = ElasticSearchHelper.getInstance().generateQuery(
				greenhouse, 
				criteria.getSearchText(), 
				filterItems, 
				criteria.getSort(), 
				criteria.getAggregates(), 
				criteria.getStartAt(), 
				criteria.getPageSize());
		
		System.out.println(query.toString(5));
		
		JSONObject result = ElasticSearchHelper.getInstance().execute(cat.getElasticSearchUrl(), query.toString());
		JSONArray hits = result.getJSONObject("hits").getJSONArray("hits");
		
		List<SearchResultView> searchResults = new ArrayList<SearchResultView>();
		for (int i=0; i<hits.length(); i++) {
			JSONObject hitTop = hits.getJSONObject(i);
			JSONObject hit = hitTop.getJSONObject("_source");
			double score = 0;
			if (hitTop.has(ElasticSearchHelper.SCORE_FIELD) && !hitTop.isNull(ElasticSearchHelper.SCORE_FIELD)) {
				score = hitTop.getDouble(ElasticSearchHelper.SCORE_FIELD);
			}
			searchResults.add(mapper.jsonToObject(hitTop.getString(ElasticSearchHelper.ID_FIELD), score, hit));
		}
		
		
		return searchResults;
	}
}
