package com.ubuntoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.ubuntoo.mappers.EventsResultsMapper;
import com.ubuntoo.mappers.GreenhousesResultsMapper;
import com.ubuntoo.mappers.KnowledgeResultsMapper;
import com.ubuntoo.mappers.NewsResultsMapper;
import com.ubuntoo.mappers.SearchResultMappers;
import com.ubuntoo.mappers.SolutionsResultsMapper;
import com.ubuntoo.pojo.SearchCategory;
import com.ubuntoo.staticdata.SearchCategories;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages={"com.ubuntoo.graphql", "com.ubuntoo.api", "com.example.restservice"}) //, "com.ubuntoo.graphql"
public class RestServiceApplication {
    static {
        System.out.println("************* RestServiceApplication");
    }

    public static void main(String[] args) {		
    	SearchCategories.getInstance().add(new SearchCategory("solutions", "Newest Solutions", "/solutions", "/solutions_production/_search", "solution_status", "active"));
    	SearchCategories.getInstance().add(new SearchCategory("knowledge", "Trending Knowledge", "/blogs", "/blogs_production/_search", "status", "active"));
    	SearchCategories.getInstance().add(new SearchCategory("news", "Latest News", "/solutions", "/news_items_production/_search", "status", "active"));
    	SearchCategories.getInstance().add(new SearchCategory("events", null, null, "/conferences_production/_search", "status", "active"));
    	SearchCategories.getInstance().add(new SearchCategory("greenhouses", null, null, "/greenhouses_production/_search", "greenhouse_status", "active"));

    	SearchResultMappers.getInstance().add("solutions", new SolutionsResultsMapper());
    	SearchResultMappers.getInstance().add("knowledge", new KnowledgeResultsMapper());
    	SearchResultMappers.getInstance().add("news", new NewsResultsMapper());
    	SearchResultMappers.getInstance().add("events", new EventsResultsMapper());
    	SearchResultMappers.getInstance().add("greenhouses", new GreenhousesResultsMapper());
    	
        SpringApplication.run(RestServiceApplication.class, args);
    }
}
