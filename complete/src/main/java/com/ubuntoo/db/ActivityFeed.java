package com.ubuntoo.db;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_feeds")
public class ActivityFeed {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "actionable_type")
	private String actionableType;
	
	@Column(name = "actionable_id")
	private long actionableId;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "header")
	private String header;
	
	@Column(name = "title")
	private String title;

	
	@Column(name = "greenhouse_id")
	private Long greenhouseId;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;
	
	public ActivityFeed() {
	}

	public ActivityFeed(long id, String actionableType, long actionableId, String body, String url, String header,
			String title, Long greenhouseId, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.actionableType = actionableType;
		this.actionableId = actionableId;
		this.body = body;
		this.url = url;
		this.header = header;
		this.title = title;
		this.greenhouseId = greenhouseId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionableType() {
		return actionableType;
	}

	public void setActionableType(String actionableType) {
		this.actionableType = actionableType;
	}

	public long getActionableId() {
		return actionableId;
	}

	public void setActionableId(long actionableId) {
		this.actionableId = actionableId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getGreenhouseId() {
		return greenhouseId;
	}

	public void setGreenhouseId(Long greenhouseId) {
		this.greenhouseId = greenhouseId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "ActivityFeed [id=" + id + ", actionableType=" + actionableType + ", actionableId=" + actionableId
				+ ", body=" + body + ", url=" + url + ", header=" + header + ", title=" + title + ", greenhouseId="
				+ greenhouseId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
