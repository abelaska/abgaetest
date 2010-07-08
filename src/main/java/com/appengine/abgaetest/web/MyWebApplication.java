package com.appengine.abgaetest.web;

import org.apache.wicket.Page;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.session.ISessionStore;

import com.appengine.abgaetest.web.page.HelloWorld;
import com.google.inject.Binder;
import com.google.inject.Module;

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
				// binder.bind(IService.class).to(Service.class);
			}
		};
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HelloWorld.class;
	}
}