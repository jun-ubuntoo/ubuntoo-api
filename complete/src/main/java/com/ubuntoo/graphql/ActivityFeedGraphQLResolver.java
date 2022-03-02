package com.ubuntoo.graphql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ubuntoo.pojo.ActivityFeedItem;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ActivityFeedGraphQLResolver implements GraphQLQueryResolver {
	static {
		System.out.println(" ***************** ActivityFeedGraphQLResolver");
	}
	static List<ActivityFeedItem> feedItems = new ArrayList<ActivityFeedItem>();
	static {
		feedItems.add(new ActivityFeedItem(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Fashion United", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/activity/1", (new Date()).getTime()));
		feedItems.add(new ActivityFeedItem(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Fashion United", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/activity/2", (new Date()).getTime()));
		feedItems.add(new ActivityFeedItem(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Fashion United", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/activity/3", (new Date()).getTime()));
		feedItems.add(new ActivityFeedItem(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Fashion United ", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/activity/4", (new Date()).getTime()));
		feedItems.add(new ActivityFeedItem(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Fashion United", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/activity/5", (new Date()).getTime()));
	}

	public ActivityFeedItem activityFeedItem(String url) {
		for (ActivityFeedItem s : feedItems) {
			if (s.getUrl().equals(url)) return s;
		}
		return null;
	}

	public List<ActivityFeedItem> allActivityFeedItems() {
		return feedItems;
	}
}
