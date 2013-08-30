package com.fit.scraper;

import java.util.ArrayList;
import java.util.List;

public class ScraperController {

	public static void main(String[] args) {
		
		// TODO include list of files in main config file?
		String[] scrapeFiles = {
				"config/asos_scraper.csv", 
				"config/newlook_scraper.csv"
		};
		
		List<Thread> scrapers = new ArrayList<Thread>();
		long startTime = System.nanoTime();
		System.out.println("Initializing scrapers...");
		for (String file : scrapeFiles) {
			System.out.println("Scraper " + scrapers.size() + ": " + file);
			scrapers.add(new Thread(new Scraper(scrapers.size(), file)));
		}
		long bootTime = System.nanoTime();
		System.out.print("complete: " + (bootTime - startTime)/1e9);
		
		System.out.print("Running scrapers...");
		for (Thread scraper : scrapers) {
			scraper.start();
		}
		
		//TODO thread join for combined output to write to file
		
		long scrapeTime = System.nanoTime();
		System.out.println("complete: " + (scrapeTime - bootTime)/1e9);
	}
		
}
