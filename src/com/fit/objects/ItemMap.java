package com.fit.objects;

import java.util.HashMap;
import java.util.Map;

public class ItemMap {

	private Map<String, String> inputMap;
	private Map<String, String> outputMap;
	
	public ItemMap() {
		this.inputMap = new HashMap<String, String>();
		this.outputMap = new HashMap<String, String>();
	}

	public Map<String, String> getInputMap() {
		return inputMap;
	}

	public void setInputMap(Map<String, String> inputMap) {
		this.inputMap = inputMap;
	}
	
	public void addInputPair(String key, String value) {
		this.inputMap.put(key, value);
	}

	public Map<String, String> getOutputMap() {
		return outputMap;
	}

	public void setOutputMap(Map<String, String> outputMap) {
		this.outputMap = outputMap;
	}
	
	public void addOutputPair(String key, String value) {
		this.outputMap.put(key, value);
	}

}
