package com.ubuntoo.graphql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.ubuntoo.pojo.HomeBandCategory;
import com.ubuntoo.pojo.SearchCategory;
import com.ubuntoo.pojo.SearchCriteria;
import com.ubuntoo.pojo.SearchResultView;
import com.ubuntoo.staticdata.SearchCategories;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class HomeBandGraphQLResolver implements GraphQLQueryResolver {
	static {
		System.out.println(" ***************** ActivityFeedGraphQLResolver");
	}
	
	public List<HomeBandCategory> homeBandCategories(DataFetchingEnvironment env) {
        /*System.out.println("___________________ homeBandCategories");
		GraphQLServletContext context =  env.getContext();
        HttpServletRequest request = context.getHttpServletRequest();
        String ct = request.getHeader("content-type");
        System.out.println("___________________ content-type: "+ct);
        System.out.println("___________________ Content-Type: "+request.getHeader("Content-Type"));
        System.out.println("___________________ Authorization: "+request.getHeader("Authorization"));*/
        
        List<HomeBandCategory> cats = new ArrayList<HomeBandCategory>();
        cats.add(homeBandCategory("solutions", env));
        cats.add(homeBandCategory("knowledge", env));
        cats.add(homeBandCategory("news", env));
        
		return cats;
	}

	public HomeBandCategory homeBandCategory(String category, DataFetchingEnvironment env) {
        /*System.out.println("___________________ homeBandCategory");
		GraphQLServletContext context =  env.getContext();
        HttpServletRequest request = context.getHttpServletRequest();
        String ct = request.getHeader("content-type");
        System.out.println("___________________ content-type: "+ct);
        System.out.println("___________________ Content-Type: "+request.getHeader("Content-Type"));
        System.out.println("___________________ Authorization: "+request.getHeader("Authorization"));*/

		SearchCategory searchCat = SearchCategories.getInstance().get(category);
		HomeBandCategory cat = new HomeBandCategory(searchCat.getName(), searchCat.getHomebandTitle(), searchCat.getViewAllUrl());
		if (category.equals("news")) {
			SearchCriteria criteria = new SearchCriteria();
			criteria.setCategory(category);
			criteria.setPageSize(6);
			criteria.setSort("-news_date,-_id");
			
			SearchGraphQLResolver sgr = new SearchGraphQLResolver();
			try {
				cat.setItems(sgr.searchInternal(criteria, env));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (category.equals("knowledge")) {
			SearchCriteria criteria = new SearchCriteria();
			criteria.setCategory(category);
			criteria.setPageSize(6);
			criteria.setSort("-_id");
			
			SearchGraphQLResolver sgr = new SearchGraphQLResolver();
			try {
				cat.setItems(sgr.searchInternal(criteria, env));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  else if (category.equals("solutions")) {
			SearchCriteria criteria = new SearchCriteria();
			criteria.setCategory(category);
			criteria.setPageSize(6);
			criteria.setSort("-_id");
			criteria.setSearchText("protein");
			
			SearchGraphQLResolver sgr = new SearchGraphQLResolver();
			try {
				cat.setItems(sgr.searchInternal(criteria, env));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (int i=0; i<4; i++) {
				cat.getItems().add(new SearchResultView(
						"1",
						"solutions",
						"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
						"This is the title", 
						"Subtitle Publications",
						"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
						"/solutions/"+i, 
						null,//(new Date()).getTime(),
						0)
					);
			}
		}
		
		return cat;
	}
}
