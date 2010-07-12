package com.appengine.abgaetest.dao;

import java.util.List;

import com.appengine.abgaetest.domain.BlogEntry;

public interface BlogDao {

	List<BlogEntry> findAll();
	
	List<BlogEntry> find(int first, int count);
	
	int countAll();
	
	void save(BlogEntry blogEntry);
}
