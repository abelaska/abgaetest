package com.appengine.abgaetest.web.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;

import com.appengine.abgaetest.dao.BlogDao;
import com.appengine.abgaetest.domain.BlogEntry;
import com.appengine.abgaetest.web.page.component.SimplePagingNavigator;
import com.appengine.abgaetest.web.provider.BlogEntryProvider;
import com.google.inject.Inject;

public final class Blog extends WebPage {

	@Inject
	private BlogDao blogDao;
	
	public Blog() {
		add(new BlogForm("blogForm"));

		IDataProvider<BlogEntry> blogEntriesProvider = new BlogEntryProvider();
		
		DataView<BlogEntry> blogEntriesView = new DataView<BlogEntry>("messages", blogEntriesProvider) {
			private static final long serialVersionUID = -9028944556242487477L;

			@Override
			protected void populateItem(Item<BlogEntry> item) {
				BlogEntry blogEntry = (BlogEntry) item.getModelObject();
				item.setModel(new CompoundPropertyModel<BlogEntry>(blogEntry));
				item.add(new Label("username"));
				item.add(new Label("message"));
			}
		};
		blogEntriesView.setItemsPerPage(8);
		add(blogEntriesView);
		add(new SimplePagingNavigator("pager", blogEntriesView));
	}

	public final class BlogForm extends Form<ValueMap> {
		private static final long serialVersionUID = -198149983713574255L;

		public BlogForm(final String id) {
			super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
			setMarkupId(id);
			add(new TextArea<String>("message").setType(String.class));
			add(new TextField<String>("username").setType(String.class));
		}

		@Override
		public final void onSubmit() {
			ValueMap values = getModelObject();

			String username = (String) values.get("username");
			String message = (String) values.get("message");
			
			if (message.isEmpty()) {
				return;
			}
			
			BlogEntry blogEntry = new BlogEntry();
			blogEntry.setUsername(username);
			blogEntry.setMessage(message);
			blogDao.save(blogEntry);

			values.put("message", "");
		}
	}
}