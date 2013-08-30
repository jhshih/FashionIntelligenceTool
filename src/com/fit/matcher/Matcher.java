package com.fit.matcher;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.fit.util.ConfigUtils;
import com.fit.objects.Item;
import com.fit.objects.ItemMap;
import com.fit.objects.MatchConfigBO;
import com.fit.util.CSVUtils;

public class Matcher {

	public static void main(String[] args) {
		
		/* Reads in configuration values such as input/output file path, 
		 * colour reference file path, etc. and starts timer */
		MatchConfigBO config = ConfigUtils.init("config/asos_config.csv");
		ItemMap map = ConfigUtils.getItemMap("config/field_mapping.csv");
		long startTime = System.nanoTime();
		
		/* Read in inventory CSV file */
		System.out.print("Reading input file...");
		List<Item> inventory = CSVUtils.readFile(config.getInputFile(), ',', '\t', 0, map.getInputMap());
		long readTime = System.nanoTime() - startTime;
		System.out.println("complete!");
		
		/* Initialize variables */
		ColourMatchUtil cmu = new ColourMatchUtil(config);
		TaxonomyMatchUtil tmu = new TaxonomyMatchUtil(config);
		
		Set<String> unknown = new HashSet<String>();
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.UK);
		money.setMaximumFractionDigits(2);
		NumberFormat percent = NumberFormat.getPercentInstance(Locale.UK);
		
		long count = 0;
		long matches = 0;
		
		System.out.print("Processing lines...");
		for (Item item : inventory) {
			if (++count%1000 == 0) System.out.print(".");
			/* Process colour taxonomy */
			item = cmu.processColour(item);

			/* Populates unknown colour list if no match */
			if (item.getColourTaxonomy().equals("-")) {
				unknown.add(item.getColour());
			}
		
			/* Process item taxonomy */
			item = tmu.processTaxonomy(item);
			if (item.getTaxonomy1() != null) matches++;
			
			/* Process discount % and indicator */
			if (item.getDiscountPrice().equals("No sale")) {
				item.setDiscountPercent("0%");
				item.setDiscounted(false);
				item.setDiscountPrice(item.getOriginalPrice());
			} else {
				try {
					Number oPrice = money.parse(item.getOriginalPrice());
					Number dPrice = money.parse(item.getDiscountPrice());
					Number discount = 1.0 - oPrice.doubleValue()/dPrice.doubleValue();
					//in the scrape, "price" is final price and "sale" has the original price
					item.setDiscountPrice(money.format(oPrice));
					item.setOriginalPrice(money.format(dPrice));
					item.setDiscountPercent(percent.format(discount));
					item.setDiscounted(true);
				} catch (ParseException e) {
					// cells may have been shifted or invalid price entered
					//e.printStackTrace();
					System.out.println("ERROR/dPrice:" + item.getDiscountPrice() + " oPrice:" + item.getOriginalPrice());
				}
			}

			/* Process availability indicator */
			
			
			//add additional levels of taxonomy
		}
		long processTime = System.nanoTime() - startTime - readTime;
		System.out.println("complete!");
		
		String[] array = unknown.toArray(new String[0]);
		List<String[]> unknownList = new ArrayList<String[]>();
		for (String s : array) unknownList.add(new String[] {s});

		/* Writes out output and error files */
		System.out.print("Writing output...");
		CSVUtils.writeFile(inventory, config.getOutputFile(), ',', '\t', map.getOutputMap());
		CSVUtils.writeFile(unknownList, config.getErrorFile(), ',', '\t');
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("complete!");
		System.out.println(matches + " matches found!");
		
		/* Prints out performance summary */
		System.out.println("Read time: " + readTime/1e9);
		System.out.println("Processing time: " + processTime/1e9);
		System.out.println("Total time: " + elapsedTime/1e9);
	}
	
}
