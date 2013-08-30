package com.fit.matcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import com.fit.objects.Item;
import com.fit.objects.MatchConfigBO;
import com.fit.util.CSVUtils;

public class ColourMatchUtil {

	private HashMap<String, String> colourMap = new HashMap<String, String>();
	private Set<String> colourList = new HashSet<String>();
	
	/* Constructor */
	public ColourMatchUtil(MatchConfigBO config){
		/* Read in colour reference file, populates colour list and colour map*/
		String[] row;
		String child, parent;
		List<String[]> content = CSVUtils.readFile(config.getColourRef(), ',','\"', 1);
		for (String[] object : content) {
		    row = (String[]) object;
		    child = row[0].toLowerCase().trim();
		    parent = row[1].toLowerCase().trim();
			colourMap.put(child, parent);
			colourList.add(child);
			colourList.add(parent);
		}
		//System.out.println(colourList);
	}
	
	public Item processColour(Item item) {
		String colour = null;
		
		/* Looks up colour from colour field */
		if (item.getColour() != null && !item.getColour().equals("-")) {
			colour = findColour(item.getColour().toLowerCase());
		}
		
		/* If no colour field present, searches product name for colour */
		if ((colour = parentColour(colour)) == null) {
			colour = parentColour(findColour(item.getProductName().toLowerCase()));
		}
		
		if (colour == null) {
			colour = "-";
		}
		item.setColourTaxonomy(StringUtils.capitalize(colour));
		return item;
	}
	
	public String findColour(String value) {
		String result = null;
		boolean isMatched = false;
		int startIndex = 0, endIndex = 0;
		
		for (String colour : colourList) {
			
			if (value.matches(".*\\b"+colour+"\\b.*")) {
				if (isMatched && !parentColour(colour).equals(parentColour(result))) {
					result = "mixed";
				} else {
					result = colour;
				}
				isMatched = true;
			}
			
			/* Check for colour contained within a non-colour word (e.g. checkeRED, TANk) */
			/*
			if (value.contains(colour)) {
				startIndex = value.indexOf(colour) - 1;
				endIndex = startIndex + colour.length() + 1;
				if ((startIndex >= 0 && value.charAt(startIndex) != ' ' && value.charAt(startIndex) != '-') ||
				   (value.length() > endIndex && value.charAt(endIndex) != ' ' && value.charAt(endIndex) != '/')) {
					// do nothing; not a match
				} else {
					if (isMatched && !parentColour(colour).equals(parentColour(result))) {
						result = "mixed";
					} else {
						result = colour;
					}
					isMatched = true;
				}
			}
		*/
		}
		return result;
	}
	
	public String parentColour(String childColour) {
		if (childColour != null) {
			return colourMap.get(childColour);
		} else return null;
	}
	
}
