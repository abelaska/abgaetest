package com.appengine.abgaetest.web.provider;

import java.util.Iterator;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.appengine.abgaetest.dao.BlogDao;
import com.appengine.abgaetest.domain.BlogEntry;
import com.google.inject.Inject;

public class BlogEntryProvider implements IDataProvider<BlogEntry> {
	private static final long serialVersionUID = -4844552094305667329L;

	@Inject
	private BlogDao blogDao;
	
	public BlogEntryProvider() {
		InjectorHolder.getInjector().inject(this);
	}
	
	public Iterator<BlogEntry> iterator(int first, int count) {
		return blogDao.find(first, count).iterator();
	}

	public int size() {
		return blogDao.countAll();
	}

	public IModel<BlogEntry> model(final BlogEntry object) {
		return new LoadableDetachableModel<BlogEntry>() {
			private static final long serialVersionUID = 4913271469573693423L;

			@Override
			protected BlogEntry load() {
				return object;
			}
		};
	}

	public void detach() {
	}
}
