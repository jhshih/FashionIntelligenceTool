package com.fit.objects;


public class ScrapeConfig {

	private String baseURL;
	private int scrapeLevel;
	private String validLink;
	private ScrapeTag idTag;
	private ScrapeTag productName;
	private ScrapeTag originalPrice;
	private ScrapeTag discountPrice;
	private ScrapeTag discounted;
	private ScrapeTag available;
	private ScrapeTag size;
	private ScrapeTag colour;
	private ScrapeTag category;
	private ScrapeTag brand;
	private ScrapeTag imageURL;

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

	public ScrapeTag getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(ScrapeTag originalPrice) {
		this.originalPrice = originalPrice;
	}

	public ScrapeTag getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(ScrapeTag discountPrice) {
		this.discountPrice = discountPrice;
	}

	public ScrapeTag getDiscounted() {
		return discounted;
	}

	public void setDiscounted(ScrapeTag discounted) {
		this.discounted = discounted;
	}

	public ScrapeTag getAvailable() {
		return available;
	}

	public void setAvailable(ScrapeTag available) {
		this.available = available;
	}

	public ScrapeTag getSize() {
		return size;
	}

	public void setSize(ScrapeTag size) {
		this.size = size;
	}

	public ScrapeTag getColour() {
		return colour;
	}

	public void setColour(ScrapeTag colour) {
		this.colour = colour;
	}

	public ScrapeTag getCategory() {
		return category;
	}

	public void setCategory(ScrapeTag category) {
		this.category = category;
	}

	public ScrapeTag getBrand() {
		return brand;
	}

	public void setBrand(ScrapeTag brand) {
		this.brand = brand;
	}

	public ScrapeTag getImageURL() {
		return imageURL;
	}

	public void setImageURL(ScrapeTag imageURL) {
		this.imageURL = imageURL;
	}
	
}
