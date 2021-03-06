package com.ubuntoo.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import com.ubuntoo.pojo.SearchResultView;

public class GreenhousesResultsMapper implements SearchResultMapper {
	public SearchResultView jsonToObject(String id, double score, JSONObject json) throws JSONException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		SearchResultView view = new SearchResultView();
		view.setId(id);
		view.setScore(score);
		view.setCategory("greenhouses");
		view.setImage("https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
		view.setTitle(WordUtils.capitalize(json.getString("name")));
		
		view.setSubtitle(WordUtils.capitalize(json.getString("enterprise_name")));
		
		
		view.setBody(Jsoup.parse(json.getString("description")).text());
		view.setUrl("/k/="+id);
		
		if (json.has("date")) {
			view.setDate(sdf.parse(json.getString("date")).getTime());
		}
		
		return view;
	}
	public SearchResultView jsonToObject(String id, double score, String json) throws JSONException, ParseException {
		return jsonToObject(id, score, new JSONObject(json));
	}
}
