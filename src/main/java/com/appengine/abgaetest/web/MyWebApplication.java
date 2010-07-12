package com.appengine.abgaetest.web;

import org.apache.wicket.Page;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.ISessionStore;

import com.appengine.abgaetest.dao.BlogDao;
import com.appengine.abgaetest.dao.objectify.BlogDaoObjectify;
import com.appengine.abgaetest.web.page.Blog;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

public class MyWebApplication extends WebApplication {

	protected void init() {
		// appengine hack
		getResourceSettings().setResourcePollFrequency(null);
		// guice hack
		addComponentInstantiationListener(new GuiceComponentInjector(this,
				getModule()));
	}

	@Override
	protected ISessionStore newSessionStore() {
		// appengine hack
		return new HttpSessionStore(this);
	}

	private Module getModule() {
		return new Module() {
			public void configure(Binder binder) {
				binder.bind(BlogDao.class).to(BlogDaoObjectify.class).in(Scopes.SINGLETON);
			}
		};
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Blog.class;
	}
}