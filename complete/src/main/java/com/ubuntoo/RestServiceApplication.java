package com.ubuntoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages={"com.ubuntoo.graphql", "com.ubuntoo.api", "com.example.restservice"}) //, "com.ubuntoo.graphql"
public class RestServiceApplication {
    static {
        System.out.println("************* RestServiceApplication");
    }

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

/*
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {

		GraphQLSchema schema  = SchemaParser.newParser()
				.resolvers(authorResolver(ar), mutation(br, ar), query(br, ar))
				.file("graphql/author.graphqls")
				.file("graphql/book.graphqls")
				.build().makeExecutableSchema();
		ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
		GraphQLServlet servlet = new SimpleGraphQLServlet(schema, executionStrategy);
		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/graphql");
		return bean;
	}*/
}
