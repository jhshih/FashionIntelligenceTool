package com.fit.objects;


public class ScrapeConfig {

	private String baseURL;
	private int scrapeLevel;
	private String validLink;
	private ScrapeTag idTag;
	private ScrapeTag productName;

	public ScrapeConfig() {}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public int getScrapeLevel() {
		return scrapeLevel;
	}

	public void setScrapeLevel(int scrapeLevel) {
		this.scrapeLevel = scrapeLevel;
	}

	public String getValidLink() {
		return validLink;
	}

	public void setValidLink(String validLink) {
		this.validLink = validLink;
	}

	public ScrapeTag getIdTag() {
		return idTag;
	}

	public void setIdTag(ScrapeTag idTag) {
		this.idTag = idTag;
	}

	public ScrapeTag getProductName() {
		return productName;
	}

	public void setProductName(ScrapeTag productName) {
		this.productName = productName;
	}
	
}
