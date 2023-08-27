package com.site.crawler;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CrawlerTask implements Runnable {

	private static Set<String> visitedURLSet = ConcurrentHashMap.newKeySet();

	private final int MAX_DEPTH;

	private String seedURL;
	
	private String tname;
	
	private String host;
	 

	public CrawlerTask(int d, String seedURL,String tname) {

		this.MAX_DEPTH = d;
		this.seedURL = seedURL;
		this.tname=tname;
	}

	public void run() {

		try {
			Thread.currentThread().setName(tname);
				host=new URL(seedURL).getHost();
			
			System.out.println(Thread.currentThread().getName() + " - Browsing seedurl :" + seedURL);
			crawl(0, seedURL);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void crawl(int depth, String url) throws IOException {

		if(depth > MAX_DEPTH) {
			System.out.println("*** MAX depth  reached - " + depth+ ",... returning back now");

			return;
		}
		
		
		
		if (depth <= MAX_DEPTH) {

			Document document = HtmlParser.parseUrl(url);
			depth++;
			System.out.println("depth - " + depth);

			for (Element element : document.select("a[href]")) {

				String nexturl = element.absUrl("href");
				

				if (visitedURLSet.add(nexturl)  && nexturl.contains(host) && nexturl.contains("http") ) {
					System.out.println(Thread.currentThread().getName() +  " having depth - " + depth + " .. Browsing Child URLs - " + nexturl
							 );

					crawl(depth, nexturl);
				}

			}

		}

	}

	public static Set<String> getVisitedURLSet() {
		return visitedURLSet;
	}

	public static void setVisitedURLSet(Set<String> visitedURLSet) {
		CrawlerTask.visitedURLSet = visitedURLSet;
	}

}
