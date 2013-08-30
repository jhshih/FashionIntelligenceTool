package com.fit.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fit.objects.Item;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVUtils {

	/* Private constructor to prevent instances */
	private CSVUtils() {}
	
	/**
	 * @param path
	 * @param separator
	 * @param quotechar
	 * @param ignoreHeader
	 * @return 
	 */
	public static List<String[]> readFile(String path, char separator, char quotechar, int ignoreHeader) {
		
		List<String[]> result = null;
		
		try (CSVReader reader = new CSVReader(new FileReader(path), separator, quotechar, ignoreHeader)) {
			result = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * @param path
	 * @param separator
	 * @param quotechar
	 * @param ignoreHeader
	 * @param columnMap
	 * @return
	 */
	public static List<Item> readFile(String path, char separator, char quotechar, int ignoreHeader, Map<String, String> columnMap) {
		
		List<Item> result = null;
		CsvToBean<Item> csv = new CsvToBean<Item>();
		
		HeaderColumnNameTranslateMappingStrategy<Item> strategy = new HeaderColumnNameTranslateMappingStrategy<Item>();
		strategy.setType(Item.class);
		strategy.setColumnMapping(columnMap);
		
		try (CSVReader reader = new CSVReader(new FileReader(path), separator, quotechar, ignoreHeader)) {
			result = csv.parse(strategy, reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void writeFile(List<Item> content, String path, char separator, char quotechar, Map<String, String> columnMap) {
		//writes headers from columnMap?
		
		List<String[]> output = new ArrayList<String[]>();
		output.add(new String[] {"SKU",
			"Retailer",
			"Product Name",
			"Original Price",
			"Discount Price",
			"Discount %",
			"Discount Indicator",
			"Availability Indicator",
			"Size",
			"Size Taxonomy",
			"Colour",
			"Colour Taxonomy",
			"Category1",
			"Category2",
			"Category3",
			"Category4",
			"Taxonomy1",
			"Taxonomy2",
			"Taxonomy3",
			"Taxonomy4",
			"Brand",
			"Brand Taxonomy",
			"Date",
			"Item URL",
			"Image URL"});
		for (Item item : content) {
			output.add(item.getArray());
		}
		
		try (CSVWriter writer = new CSVWriter(new FileWriter(path), separator, quotechar)) {
			writer.writeAll(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writeFile(List<String[]> content, String path, char separator, char quotechar) {
		
		try (CSVWriter writer = new CSVWriter(new FileWriter(path), separator, quotechar)) {
			writer.writeAll(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
