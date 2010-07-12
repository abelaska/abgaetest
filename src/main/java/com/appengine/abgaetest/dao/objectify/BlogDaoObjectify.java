package com.appengine.abgaetest.dao.objectify;

import java.util.List;

import com.appengine.abgaetest.dao.BlogDao;
import com.appengine.abgaetest.domain.BlogEntry;

public class BlogDaoObjectify extends ObjectifyGenericDao<BlogEntry> implements
		BlogDao {

	protected BlogDaoObjectify() {
		super(BlogEntry.class);
	}

	@Override
	public List<BlogEntry> findAll() {
		return ofy().query(clazz).order("-id").list();
	}

	@Override
	public List<BlogEntry> find(int first, int count) {
		return ofy().query(clazz).order("-id").offset(first).limit(count)
				.list();
	}

	@Override
	public int countAll() {
		return ofy().query(clazz).countAll();
	}

	@Override
	public void save(BlogEntry blogEntry) {
		put(blogEntry);
	}
}
