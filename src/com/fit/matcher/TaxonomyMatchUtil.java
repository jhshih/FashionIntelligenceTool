package com.fit.matcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.fit.objects.Item;
import com.fit.objects.MatchConfigBO;
import com.fit.util.CSVUtils;

public class TaxonomyMatchUtil {

	private Taxonomy taxonomyTree = new Taxonomy("clothing & accessories");
	private Set<String> taxonomyList = new HashSet<String>();
	private Set<String> keywordList = new HashSet<String>();
	private List<String[]> keyList = new ArrayList<String[]>();
	
	
	/* Constructor */
	public TaxonomyMatchUtil(MatchConfigBO config){
		
		Taxonomy t = null;
		String value = null;
		
		/* Read in taxonomy reference file, populates taxonomy list map*/
		List<String[]> content = CSVUtils.readFile(config.getTaxonomyRef(), ',', '\t', 1);
		
		for (String[] row : content) {
			
			/* Hierarchy: ID,RootCategory,Level1,Level2,Level3,Level4,Keywords */
			/* Populates taxonomy tree*/
			t = taxonomyTree;
			for (int j = 2; j < row.length; j++) {
				value = row[j].toLowerCase().trim();
				if (!t.getChildrenNames().contains(value)) {
					t.addChild(value);
					if (t.getChild(value).getLevel() == 5) {
						keyList.add(new String[]{t.getChild(value).getPath().get(1),t.getChild(value).getPath().get(2),value,t.getChild(value).pathString()});
						keywordList.add(value);
					}
				}
				t = t.getChild(value);
			}
			while (t.getParent() != null) t = t.getParent();
			taxonomyTree = t;
		}
		
		/* add paths to list */
		taxonomyList = taxonomyTree.pathList();
		System.out.println(taxonomyTree.print());
	}
	
	public Item processTaxonomy(Item item) {
		String taxonomyPath = null;
		String[] taxonomy = new String[5];
		String name = item.getProductName().toLowerCase().trim();
		String cat1 = item.getCategory1().toLowerCase().trim();
		String cat2 = item.getCategory2().toLowerCase().trim();
		String temp = null;
		String partialMatch = null;
		
		/*
		if (existsKeyword(name)) {
			for (String[] s : keyList) {
				if (name.matches(".*\\b"+s[1]+".*")) {
					temp = s[3];
					if (s[0].matches(".*\\b"+cat1+".*") || s[1].matches(".*\\b"+cat2+".8")) {
					} else {
						partialMatch = temp;
					}
					
					if (taxonomyPath == null) {
						taxonomyPath = temp;
					} else if (temp.split(">")[5].contains(taxonomyPath.split(">")[5])) {
						taxonomyPath = temp;
					} else {
						//ignore
					}
				}
			}
			if (taxonomyPath == null) taxonomyPath = partialMatch;
			
		}
		*/
		
		taxonomyPath = taxonomyTree.findKeyword1(name, cat1);
		if (taxonomyPath == null) {
			taxonomyPath = taxonomyTree.findKeyword2(name, cat2);
		}
		if (taxonomyPath == null) {
			taxonomyPath = taxonomyTree.findKeyword(name);
		}
		if (taxonomyPath == null) {
			taxonomyPath = "clothing & accessories>other>>>";
		}
		
		if (taxonomyPath != null) {
			String[] set = taxonomyPath.split(">");
			if (set.length > 1) taxonomy[1] = set[1];
			if (set.length > 2) taxonomy[2] = set[2];
			if (set.length > 3) taxonomy[3] = set[3];
			if (set.length > 4) taxonomy[4] = set[4];
		}

		//blanks to "Other"
		
		item.setTaxonomy1(WordUtils.capitalize(taxonomy[1]));
		item.setTaxonomy2(WordUtils.capitalize(taxonomy[2]));
		item.setTaxonomy3(WordUtils.capitalize(taxonomy[3]));
		item.setTaxonomy4(WordUtils.capitalize(taxonomy[4]));
		
		return item;
	}
	
	public boolean existsKeyword(String value) {
		for (String taxonomy : keywordList) {
			if (value.contains(taxonomy)) {
				return true;
			}
		}
		return false;
	}
	
}
