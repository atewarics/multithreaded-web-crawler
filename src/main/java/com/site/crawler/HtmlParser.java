package com.site.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlParser {

	public HtmlParser(String string) {
		// TODO Auto-generated constructor stub
	}

	public static Document parseUrl(String baseURL) throws IOException
	{
		Connection conn=Jsoup.connect(baseURL);
		Document document=conn.get();
		System.out.println(document.title());
		
		
		
		return document;
		
	}
	
	
	
}
