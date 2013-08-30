/**
 * 
 */
package com.fit.objects;

/**
 * @author joshih
 *
 */
public class Item {

	private String SKU;
	private String retailerName;
	private String productName;
	private String originalPrice;
	private String discountPrice;
	private String discountPercent;
	private boolean discounted;
	private String available;
	private String size;
	private String sizeTaxonomy;
	private String colour;
	private String colourTaxonomy;
	private String category1;
	private String category2;
	private String category3;
	private String category4;
	private String taxonomy1;
	private String taxonomy2;
	private String taxonomy3;
	private String taxonomy4;
	private String brand;
	private String brandTaxonomy;
	private String date;
	private String itemURL;
	private String imageURL;
	
	public Item() {}
	
	public String[] getArray() {
		String[] result = {
				this.SKU,
				this.retailerName,
				this.productName,
				this.originalPrice,
				this.discountPrice,
				this.discountPercent,
				this.discounted ? "1" : "0",
				this.available,
				this.size,
				this.sizeTaxonomy,
				this.colour,
				this.colourTaxonomy,
				this.category1,
				this.category2,
				this.category3,
				this.category4,
				this.taxonomy1,
				this.taxonomy2,
				this.taxonomy3,
				this.taxonomy4,
				this.brand,
				this.brandTaxonomy,
				this.date,
				this.itemURL,
				this.imageURL
		};
		
		return result;
	}
	
	public void print() {
		System.out.println("SKU: " + this.SKU);
		System.out.println("Product Name: " + this.productName);
		System.out.println("Original Price: " + this.originalPrice);
		System.out.println("Discount Price: " + this.discountPrice);
		System.out.println("Available: " + this.available);
		System.out.println("Size: " + this.size);
		System.out.println("Colour: " + this.colour);
		System.out.println("Category: " + this.category1);
		System.out.println("Image: " + this.imageURL);
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public boolean isDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

	public String isAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeTaxonomy() {
		return sizeTaxonomy;
	}

	public void setSizeTaxonomy(String sizeTaxonomy) {
		this.sizeTaxonomy = sizeTaxonomy;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getColourTaxonomy() {
		return colourTaxonomy;
	}

	public void setColourTaxonomy(String colourTaxonomy) {
		this.colourTaxonomy = colourTaxonomy;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getCategory4() {
		return category4;
	}

	public void setCategory4(String category4) {
		this.category4 = category4;
	}

	public String getTaxonomy1() {
		return taxonomy1;
	}

	public void setTaxonomy1(String taxonomy1) {
		this.taxonomy1 = taxonomy1;
	}

	public String getTaxonomy2() {
		return taxonomy2;
	}

	public void setTaxonomy2(String taxonomy2) {
		this.taxonomy2 = taxonomy2;
	}

	public String getTaxonomy3() {
		return taxonomy3;
	}

	public void setTaxonomy3(String taxonomy3) {
		this.taxonomy3 = taxonomy3;
	}

	public String getTaxonomy4() {
		return taxonomy4;
	}

	public void setTaxonomy4(String taxonomy4) {
		this.taxonomy4 = taxonomy4;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrandTaxonomy() {
		return brandTaxonomy;
	}

	public void setBrandTaxonomy(String brandTaxonomy) {
		this.brandTaxonomy = brandTaxonomy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getItemURL() {
		return itemURL;
	}

	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}
