package com.ubuntoo.elasticsearch;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dvnelson72
 *
 */
public class ElasticSearchHelper {	
	public static final String SCORE_FIELD = "_score";
	public static final String ID_FIELD = "_id";
	private static final String ID2_FIELD = "id";
	private static final String GREENHOUSES_FILED = "greenhouses";
	private static final String ES_HOST_PROPERTY = "es_host";
	private static final String ES_HOST_DEFAULT = "https://vpc-prod-ubuntoo-sandbox-es-edjutwgq6f2wkhwt27og22n37m.us-east-1.es.amazonaws.com:443";

	private static final ElasticSearchHelper instance = new ElasticSearchHelper();

	private String esHost;
	private RestClient restClient;

	private ElasticSearchHelper() {
		esHost = System.getProperty(ES_HOST_PROPERTY, ES_HOST_DEFAULT);

		RestClientBuilder builder = RestClient.builder(new HttpHost("vpc-prod-ubuntoo-sandbox-es-edjutwgq6f2wkhwt27og22n37m.us-east-1.es.amazonaws.com", 443, "https"));
		restClient = builder.build();
	}
	
	public JSONObject generateIdSortBy(String direction) {
		if (StringUtils.isBlank(direction)) direction = "asc";
		direction = direction.toLowerCase();
		if (!StringUtils.equals(direction, "asc") && !StringUtils.equals(direction, "desc")) direction = "asc";
		
		String s = "{\"_script\":{\"type\":\"number\",\"script\":\"Integer.parseInt(doc['"+ID_FIELD+"'].value);\",\"order\":\""+direction+"\"}}";
		return new JSONObject(s);
	}

	public static ElasticSearchHelper getInstance() {
		return ElasticSearchHelper.instance;
	}

	/**
	 * Execute an ElasticSearch POST query to the relative URL with a query body
	 * 
	 * @param url relative URL for the search against the ES instance
	 * @param body Body of the query.  If you use generate query, do a toString() on the result of that method.
	 * @return the raw JSON object from the ES result
	 */
	public JSONObject execute(String url, String body) {
		try {
			Request request = new Request("POST", url);
			request.setJsonEntity(body);
			Response response = restClient.performRequest(request);
			String responseBody = EntityUtils.toString(response.getEntity());
			
			JSONObject jo = new JSONObject(responseBody);
			return jo;
		} catch (Exception e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

	/**
	 * Generate a query object based on search terms.  Calls the overload with from=0 and size=0
	 * 
	 * @param greenhouse greenhouse ID (int)
	 * @param searchText Text entered into the search box in the UI
	 * @param filterItems An array of objects with "name" (string) and "values" (array) attributes
	 * @param sort comma deliminated list of field names.  use "-" to denote descending.  "-date, id" would mean "date desc, id asc"
	 * @param aggs comma deliminated list of field names to send the aggregates with the query
	 * @return a JSON object representing the ES query
	 * @throws JSONException
	 * @throws JsonProcessingException
	 */
	public JSONObject generateQuery(int greenhouse, String searchText, String[] searchFields, JSONArray filterItems, String sort, String aggs) throws JSONException, JsonProcessingException {
		return generateQuery(greenhouse, searchText, searchFields, filterItems, sort, aggs, 0, 0);
	}
	
	/**
	 * Generate a query object based on search terms
	 * 
	 * @param greenhouse greenhouse ID (int)
	 * @param searchText Text entered into the search box in the UI
	 * @param filterItems An array of objects with "name" (string) and "values" (array) attributes
	 * @param sort comma deliminated list of field names.  use "-" to denote descending.  "-date, id" would mean "date desc, id asc"
	 * @param aggs comma deliminated list of field names to send the aggregates with the query
	 * @param from for pagination.  what object number in the sort to have as the first item
	 * @param size page size
	 * @return a JSON object representing the ES query
	 * @throws JSONException
	 * @throws JsonProcessingException
	 */
	public JSONObject generateQuery(int greenhouse, String searchText, String[] searchFields, JSONArray filterItems, String sort, String aggs, int from, int size) throws JSONException, JsonProcessingException {
		searchText = StringUtils.trim(searchText);
		sort = StringUtils.trim(sort);
		aggs = StringUtils.trim(aggs);
		if (size<0) size=10;
		if (from<0) from=0;
		
		JSONObject q = new JSONObject();
		q.put("from", from);
		q.put("size", size);
		
		/* if greenhouse>0 then let's ensure there is a 'greenhouse' filterItem
		 * if one exists already, override the value
		 */
		if (greenhouse>0) {
			JSONArray oldFilters = filterItems!=null?filterItems:(new JSONArray());
			filterItems = new JSONArray();

			// copy all filter items that ARE NOT 'greenhouses'
			for (int i=0; i<oldFilters.length(); i++) {
				JSONObject item = oldFilters.getJSONObject(i);
				String fieldName = item.getString("name");
				if (fieldName!=GREENHOUSES_FILED) {
					filterItems.put(item);
				}
			}
			
			// create greenhouses filter and add to new array
			JSONObject greenhousesFilter = new JSONObject();
			greenhousesFilter.put("name", GREENHOUSES_FILED);
			JSONArray greenhousesValues = new JSONArray();
			greenhousesValues.put(greenhouse);
			greenhousesFilter.put("values", greenhousesValues);
			filterItems.put(greenhousesFilter);
		}
		
		/* do the sort first
		 * sort is in the format of comma delim list.  use '-' in front of a field for desc
		 * so "-name, date" would sort by name desc, then date asc
		 */		
		if (StringUtils.isNotBlank(sort)) {
			JSONArray sarr = new JSONArray();
			String[] sorts = sort.split(",");
			
			for (int i=0; i<sorts.length; i++) {
				String fieldName = StringUtils.trim(sorts[i]);
				if (StringUtils.isNotBlank(fieldName)) {
					String direction = fieldName.startsWith("-")?"desc":"asc";
					while(fieldName.startsWith("-")) {
						fieldName = fieldName.substring(1);
					}

					if (StringUtils.isNotBlank(fieldName)) {
						if (StringUtils.equals(fieldName, ID_FIELD) || StringUtils.equals(fieldName, ID2_FIELD)) {
							sarr.put(this.generateIdSortBy(direction));
						} else {
							JSONObject sortO = new JSONObject();
							sortO.put(fieldName, direction);
							
							sarr.put(sortO);
						}
					}
				}
			}
			if (sarr.length()>0) {
				q.put("sort", sarr);
			}
		}
		
		/* do aggregates next
		 * it's a comma deliminated string of field names
		 * transform into the "aggs" array for Elastic Search
		 * 
		 */
		if (StringUtils.isNotBlank(aggs)) {
			JSONObject aggsJO = new JSONObject();
			String[] aggsValues = aggs.split(",");
			
			int aggCount = 0;
			for (int i=0; i<aggsValues.length; i++) {
				String fieldName = StringUtils.trim(aggsValues[i]);
				if (StringUtils.isNotBlank(fieldName)) {
					JSONObject terms = new JSONObject("{\"terms\": {\"field\": \""+fieldName+"\",\"size\": 10}}");
					aggsJO.put(fieldName, terms);
					aggCount++;
				}
			}
			if (aggCount>0) {
				q.put("aggs", aggsJO);
			}
		}
		
		/* 
		 * now let's construct the query 
		 * if nothing is specified, match all
		 * 
		 * if there is a searchText string, use a query_string value to search across the index
		 * 
		 * then, iterate through any filter items in the array.
		 *   -- the array is of objects that has two attributes
		 *      - name:  field name string
		 *      - values:  array of values to "or" search for
		 */
		if (StringUtils.isBlank(searchText) && (filterItems==null || filterItems.length()==0)) {
			q.put("query", new JSONObject("{\"match_all\": {}}"));
		} else {
			q.put("query", new JSONObject("{\"bool\": {\"must\": []}}"));
			JSONArray must = q.getJSONObject("query").getJSONObject("bool").getJSONArray("must");

        /*    "multi_match" : {
              "query":    "fiberx", 
              "fields": [ "name.analyzed", "short_bio.analyzed", "about.analyzed", "keyword_tags.analyzed"] 
            }*/
			if (StringUtils.isNotBlank(searchText)) {
				JSONObject mm = new JSONObject("{\"multi_match\":{\"query\":"+new ObjectMapper().writeValueAsString(searchText)+"}}");
				mm.getJSONObject("multi_match").put("fields", new JSONArray(searchFields));
				must.put(mm);
			}
			
			
			if (filterItems!=null) {
				for (int i=0; i<filterItems.length(); i++) {
					JSONObject item = filterItems.getJSONObject(i);
					String fieldName = item.getString("name");
					JSONArray values = item.getJSONArray("values");
					
					if (values.length()>0) {
						if (StringUtils.equals(fieldName, "location")) {
							/*JSONObject mm = new JSONObject("{\"multi_match\":{\"query\":"+new ObjectMapper().writeValueAsString(values.getString(0))+"}}");
							mm.getJSONObject("multi_match").put("fields", new JSONArray("[\"location.analyzed\"]"));
							must.put(mm);*/		
							String x = "{\"wildcard\": {\"location\": {\"value\": \"*"+(StringUtils.lowerCase(StringUtils.trim(values.getString(0))))+"*\", \"boost\": 1.0}}}";
							System.out.print(x);
							JSONObject mm = new JSONObject(x);
							must.put(mm);				
						} else {
							JSONObject terms= new JSONObject();
							terms.put(fieldName, values);
							
							JSONObject termsForArr = new JSONObject();
							termsForArr.put("terms", terms);
							must.put(termsForArr);
						}
					}
				}
			}
		}
		
		return q;
	}
	
	/**
	 * Close the rest client.
	 */
	public void cleanup() {
		try {
			restClient.close();
			restClient=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		try {
			JSONObject q = ElasticSearchHelper.getInstance().generateQuery(24, "aqua", new String[]{}, null, null, null);
			
			JSONArray testterms = new JSONArray();
			testterms.put(new JSONObject("{\"name\": \"status\", \"values\": [\"active\"]}"));
			testterms.put(new JSONObject("{\"name\": \"greenhouses\", \"values\": [35,122]}"));
			testterms.put(new JSONObject("{\"name\": \"part_of\", \"values\": [\"greenhouse\", \"both\"]}"));
			
			q = ElasticSearchHelper.getInstance().generateQuery(35, "aqua",  new String[]{}, testterms, "-id,-start_date", "status,greenhouses,part_of,topics", 0, 10);
			System.out.println(q.toString(4));
			
			String url = "/conferences_production/_search";
			JSONObject jo = ElasticSearchHelper.getInstance().execute(url, q.toString());
			System.out.println(jo.toString(5));
		} finally {
			ElasticSearchHelper.getInstance().cleanup();
		}
	}
}
