package com.fit.objects;

public class ScrapeTag {

	private String type;
	private String tag;
	
	public ScrapeTag() {}
	
	public ScrapeTag(String type, String tag) {
		this.setType(type);
		this.setTag(tag);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
