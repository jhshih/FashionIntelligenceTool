package com.fit.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fit.objects.ItemMap;
import com.fit.objects.MatchConfigBO;
import com.fit.objects.ScrapeConfig;
import com.fit.objects.ScrapeTag;
import com.fit.util.CSVUtils;

/**
 * @author joshih
 *
 */
public class ConfigUtils {

	/* Private constructor to prevent instances */
	private ConfigUtils() {}
	
	public static MatchConfigBO init(String configFile) {
		
		/* Initialize variables */
		MatchConfigBO result = new MatchConfigBO();
		Field field = null;
		
		/* Reads in CSV config file */
		List<String[]> content = CSVUtils.readFile(configFile, ':', '\"', 0);
		
		/* Sets configuration object properties when possible */
		for (String[] row : content) {
			try {
				field = MatchConfigBO.class.getDeclaredField(row[0]);
				field.setAccessible(true);
				field.set(result, row[1]);
			} catch (NoSuchFieldException | SecurityException |
					 IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/* Prints the configuration settings */
		System.out.println(result);
		return result;
	}
	
	/**
	 * @param path filepath of CSV file containing item field mappings
	 * @return ItemMap containing input and output field mappings
	 */
	public static ItemMap getItemMap(String path) {
		
		ItemMap result = new ItemMap();
		
		List<String[]> content = CSVUtils.readFile(path, ',', '\t', 0);
		for (String[] row : content) {
			if (row[0] != null) {
				result.addInputPair(row[0], row[1]);
			}
			result.addOutputPair(row[1], row[2]);
		}
		
		return result;
		
	}
	
	public static ScrapeConfig configScraper(String path) {
		
		ScrapeConfig result = new ScrapeConfig();
		Field field;
		
		List<String[]> content = CSVUtils.readFile(path, ',', '\t', 0);
		
		/* Sets configuration object properties when possible */
		for (String[] row : content) {
			if (row[0].equals("baseURL")) {
				result.setBaseURL(row[1]);
				result.setScrapeLevel(Integer.parseInt(row[2]));
				result.setValidLink(row[3]);
			} else {
				try {
					field = ScrapeConfig.class.getDeclaredField(row[0]);
					field.setAccessible(true);
					field.set(result, new ScrapeTag(row[1], row[2]));
					System.out.println("set " + row[2]);
				} catch (NoSuchFieldException | SecurityException |
						 IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		
		return result;
		
	}
	
}
