package cz.jiripinkas.jba.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.exception.RssException;

public class RssServiceTest {
	
	public RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService=new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems("http://feeds.feedburner.com/javavids?format=xml");
		assertEquals(10, items.size());
		Item fitem = items.get(9);
		assertEquals("Spring web app tutorial 48: Java blog aggregator homepage", fitem.getTitle());
		//assertEquals("23 06 2014 02:05:49", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(fitem.getPublishedDate()));
		assertEquals("http://feedproxy.google.com/~r/javavids/~3/96hVAaLGYQM/spring-web-app-tutorial-48-java-blog-aggregator-homepage.html", fitem.getLink());
	}

}
