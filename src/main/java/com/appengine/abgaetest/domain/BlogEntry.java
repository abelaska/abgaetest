package com.appengine.abgaetest.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.wicket.IClusterable;

@Entity
public class BlogEntry implements Serializable, IClusterable {
	private static final long serialVersionUID = -4844552094305667329L;

	@Id
	private Long id;
	private String username;
	private String message;

	public BlogEntry() {
		// Empty constructor needed for GWT serialization and Objectify
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return "[BlogEntry id=" + id + ", username=" + username + ", message="
				+ message + "]";
	}
}