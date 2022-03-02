package com.ubuntoo.graphql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ubuntoo.pojo.HomeBandCategory;
import com.ubuntoo.pojo.HomeBandItem;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class HomeBandGraphQLResolver implements GraphQLQueryResolver {
	static {
		System.out.println(" ***************** ActivityFeedGraphQLResolver");
	}
	static List<HomeBandCategory> categories = new ArrayList<HomeBandCategory>();
	static {		
		HomeBandCategory cat = new HomeBandCategory("solutions", "Newest Solutions", "/solutions");
		categories.add(cat);
		for (int i=0; i<4; i++) {
			cat.getItems().add(new HomeBandItem(
					"solutions",
					"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
					"This is the title", 
					"Subtitle Publications",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
					"/solutions/"+i, 
					(new Date()).getTime())
				);
		}

		cat = new HomeBandCategory("knowledge", "Trending Knowledge", "/blogs");
		categories.add(cat);
		for (int i=0; i<4; i++) {
			cat.getItems().add(new HomeBandItem(
					"knowledge",
					"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
					"This is the title", 
					"Subtitle Publications",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
					"/blogs/"+i, 
					(new Date()).getTime())
				);
		}

		cat = new HomeBandCategory("news", "Latest News", "/news-items");
		categories.add(cat);
		for (int i=0; i<4; i++) {
			cat.getItems().add(new HomeBandItem(
					"news",
					"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
					"This is the title", 
					"Subtitle Publications",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
					"/news-items/"+i, 
					(new Date()).getTime())
				);
		}
	}
	
	public List<HomeBandCategory> homeBandCategories() {
		return categories;
	}

	public HomeBandCategory homeBandCategory(String category) {
		for (HomeBandCategory s : categories) {
			if (s.getUrl().equals(category)) return s;
		}
		return null;
	}
}
