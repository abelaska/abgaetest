package com.appengine.abgaetest.web.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HelloWorld extends WebPage
{
    /**
     * Constructor
     */
    public HelloWorld()
    {
        add(new Label("message", "Hello World!"));
    }
}