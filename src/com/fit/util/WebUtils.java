package com.fit.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

/**does some stuff
 * @author jhshih
 * 
 */
public class WebUtils {

	private WebUtils() {}
	
	/**
	 * @param url
	 * @return JSoup document
	 */
	public static Document getXML(String url) {
		
		Document result = null;
		Connection connection = Jsoup.connect(url)
				.parser(Parser.xmlParser())
				.userAgent("Mozilla/5.01")
				.maxBodySize(0)
				.timeout(10000);
		try {
			Connection.Response response = connection.execute();
			if (response.statusCode() == 200) {
				result = connection.get();
			}
		} catch (IOException e) {}
		
		return result;
		
	}
	
	/**
	 * @param url
	 * @return
	 */
	public static Document getPage(String url) {
		
		Document result = null;
		Connection connection = Jsoup.connect(url)
				.userAgent("Mozilla/5.0")
				//.followRedirects(false)
				//.maxBodySize(0)
				.timeout(10000);
		try {
			Connection.Response response = connection.execute();
			if (response.statusCode() == 200) {
				result = connection.get();
			}
		} catch (IOException e) {}
		
		return result;
		
	}
	
	/**
	 * @param url
	 * @return
	 */
	public static boolean isValidLink(String url) {
		boolean result = false;
		Connection connection = Jsoup.connect(url)
				.userAgent("Mozilla/5.0")
				.followRedirects(false)
				.maxBodySize(0)
				.timeout(1000);
		try {
			Connection.Response response = connection.execute();
			if (response.statusCode() == 200) {
				result = true;
			}
		} catch (IOException e) {}
		
		return result;
	}
	
}
