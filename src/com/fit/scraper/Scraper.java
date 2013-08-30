package com.fit.scraper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fit.objects.ScrapeConfig;
import com.fit.util.ConfigUtils;
import com.fit.util.ScraperUtils;
import com.fit.util.WebUtils;

public class Scraper implements Runnable {
	
	private int id;
	private ScrapeConfig config;
	private List<String> urls;
	
	public Scraper(int id, String configPath) {
		super();
		this.setId(id);
		this.config = ConfigUtils.configScraper(configPath);
	}
	
	@Override
	public void run() {
		
		/* get site map of urls */
		System.out.print("Getting page list for " + config.getBaseURL() + "...");
		this.urls = getSiteMap(config.getBaseURL(), config.getScrapeLevel());
		System.out.println("initialized!");
		
		scrape();
		System.out.println(this.id + ": finished scraping!");	
	}
	
	private void scrape() {
		
		System.out.println(this.id + ": Processing...");
		long startTime = System.nanoTime();
		
		int count = 0;
		
		for (String url : urls) {
			if (count++ < 10) {
			//if (WebUtils.isValidLink(url)) {
				System.out.println(url);
				Document doc = WebUtils.getPage(url);
				if (doc != null) {
					//Item item = ScraperUtils.processItem(doc, config);
					String id = ScraperUtils.getSingle(doc, config.getIdTag().getTag());
					System.out.println("SKU: " + id);
					
					String name = ScraperUtils.getSingle(doc, config.getProductName().getTag());
					System.out.println("Product Name: " + name);
				}
			//}	
			}
		
		}
		long endTime = System.nanoTime();
		System.out.println(this.id + ": Scraped in " + (endTime-startTime)/1e9);
		
	}

	private List<String> getSiteMap(String baseURL, int scrapeLevel) {
		List<String> result = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		result.add(baseURL);
		
		int count = 0;
		Document doc;
		Elements links;
	
		for (int level = 1; level <= scrapeLevel; level++) {
			System.out.println("level " + level);
			
			for (Iterator<String> url = result.iterator(); url.hasNext(); ) {
				doc = WebUtils.getXML(url.next());
				url.remove();
				if (doc != null) {
					links = doc.select("loc");
					for (Element link : links) {
						if (isValid(link.text())) {
							temp.add(link.text());
							count++;
						}
					}
				}
			}
			result.addAll(temp);
			temp.clear();
		}
		
		System.out.println(count);
		return result;
	}

	private boolean isValid(String link) {
		if (link.matches(config.getValidLink())) {
			return true;
		} else {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
}
