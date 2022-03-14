package com.ubuntoo.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityFeedRepository extends JpaRepository<ActivityFeed, Long> {
	ActivityFeed findById(long id);
	
	@Query(value="select * from activity_feeds where greenhouse_id is null and actionable_type in ('Blog', 'NewsItem', 'Solution', 'Conference') order by id desc limit 5", nativeQuery=true)
	public List<ActivityFeed> findWithCondition();

}
