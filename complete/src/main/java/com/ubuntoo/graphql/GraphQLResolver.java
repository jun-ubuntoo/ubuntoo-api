package com.ubuntoo.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ubuntoo.pojo.Spotlight;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class GraphQLResolver implements GraphQLQueryResolver {
	static List<Spotlight> spotlights = new ArrayList<Spotlight>();
	static {
		spotlights.add(new Spotlight(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Plastic Promises", 
				"Blog", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/spotlight/1"));
		spotlights.add(new Spotlight(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Plastic Promises", 
				"Blog 2", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/spotlight/2"));
		spotlights.add(new Spotlight(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Plastic Promises", 
				"Blog 3", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/spotlight/3"));
		spotlights.add(new Spotlight(
				"https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
				"Plastic Promises", 
				"Blog 4", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex  quis nostrud exercitation ullamco laboris nisi ut aliquip ex", 
				"/spotlight/4"));
	}

	public Spotlight spotlight(String url) {
		for (Spotlight s : spotlights) {
			if (s.getUrl().equals(url)) return s;
		}
		return null;
	}

	public List<Spotlight> allSpotlights() {
		return spotlights;
	}
}
