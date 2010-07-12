package com.appengine.abgaetest.web.page.component;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

public class SimplePagingNavigator extends PagingNavigator {
	private static final long serialVersionUID = -2870451969403113582L;

	public SimplePagingNavigator(String id, IPageable pageable,
			IPagingLabelProvider labelProvider) {
		super(id, pageable, labelProvider);
	}

	public SimplePagingNavigator(String id, IPageable pageable) {
		super(id, pageable);
	}
}
