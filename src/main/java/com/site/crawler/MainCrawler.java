package com.site.crawler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainCrawler {

	public static void main(String[] args) throws Exception {

		String[] seedUrls = new String[] { "http://www.foxnews.com", "http://www.nytimes.com", "http://www.hilton.com" };
		
		
		
	//	HtmlParser parser=new HtmlParser(seedUrls[0]);
		
		
		//HtmlParser.parseUrl(seedUrls[0]);
		
		
		ExecutorService executorService=Executors.newFixedThreadPool(10);
		
		int i=1;
		
		
		long start=System.currentTimeMillis();
		
		for(String s:seedUrls) {
		
			CompletableFuture.runAsync(new CrawlerTask(4,s,"Bot - "+i++),executorService);
			
			
		}
		
		
		executorService.shutdown();
		
		executorService.awaitTermination(300, TimeUnit.SECONDS);
		
		long total =System.currentTimeMillis()-start;
		
		System.out.println("--- TOTAL TIME TAKEN in seconds ---"+(total/1000));
		
		System.out.println("*****  Complete list of URLs browsed---  \n" +CrawlerTask.getVisitedURLSet());
		
		
	
		
		
		
		
		
		
		

	}

}
