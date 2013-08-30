package com.fit.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fit.objects.Item;
import com.fit.objects.ScrapeConfig;

public class ScraperUtils {

	private ScraperUtils() {}
	
	public static Item processPage(Document doc, ScrapeConfig config) {
		
		Item result = new Item();
		
		//String sku = getSingle(doc, config.getIdTag().getTag());
		
		//String productName = getSingle(doc, config.getProductName().getTag());
		
		/*
		//Retailer
		
		//discount indicator
		//availability
		//sizes
		//System.out.print("Colour(s): ");
		boolean first = true;
		for (Element colour : colours) {
			if (!colour.attr("value").equals("-1")) {
				if (!first) //System.out.print(",");
				//System.out.print(colour.text().split("£")[0].trim());
				first = false;
			}
		}
		//categories
		//brand
		//date
		//item url
		//image url
		*/
		
		Elements scripts = doc.select("script[type*=javascript]");
		for (Element script : scripts) {
			//System.out.println(script.html());
			if (script.html().matches(".*(var\\s).*(\\s=\\s)\\{.+\\}.*")) {
				String[] val = script.html().split("(?=\\{)|(?<=\\})");
				for (String s : val) {
					if (s.matches(".*\\{.+\\}.*")) {
						//System.out.println(s);
						JSONObject obj;
						try {
							obj = new JSONObject(new JSONTokener(s));
							for (String name : JSONObject.getNames(obj)) {
							//System.out.println(name + ": " + obj.getString(name));
							}
						} catch (JSONException e) {}
					}
				}
			}

		}
		
		return result;
		
	}
	
	public static String getSingle(Document doc, String tag) {
		
		String result = null;
		
		Elements matches = doc.select(tag);
		for (Element match : matches) {
			if (match.text().length() > 2) {
				result = match.text();
			}
		}
		
		return result;
		
	}
	
	public static String getMultiple(Document doc, String tag) {
		
		StringBuilder result = new StringBuilder();
		boolean firstItem = true;
		
		Elements matches = doc.select(tag);
		for (Element match : matches) {
			if (match.text().length() > 2) {
				result.append(firstItem ? match.text() : ";" + match.text());
			}
		}
		
		return result.toString();
		
	}
	
}
