package com.ubuntoo.mappers;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import com.ubuntoo.pojo.SearchResultView;

public interface SearchResultMapper {
	public SearchResultView jsonToObject(String id, double score, JSONObject json) throws JSONException, ParseException;
	public SearchResultView jsonToObject(String id, double score, String json) throws JSONException, ParseException;
}
