package com.fit.matcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Taxonomy {
	
	private String data;
	private int level;
	private List<String> path;
	private Taxonomy parent;
	private List<Taxonomy> children;
	
	public Taxonomy(String data) {
		this.data = data;
		this.level = 0;
		this.path = new ArrayList<String>();
		path.add(data);
		this.parent = null;
		this.children = new ArrayList<Taxonomy>();
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public List<String> getPath() {
		return this.path;
	}
	
	public String pathString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		
		for (String node : path) {
			if (first) first = false;
			else result.append(">");
			result.append(node);
		}
		return result.toString();
	}
	
	public void setPath(List<String> path) {
		this.path = path;
	}
	
	public void addPath(List<String> path) {
		this.path.addAll(0, path);
	}
	
	public Taxonomy getParent() {
		return parent;
	}

	public void setParent(Taxonomy parent) {
		this.parent = parent;
	}
	
	public List<Taxonomy> getChildren() {
		return children;
	}
	
	public List<String> getChildrenNames() {
		List<String> result = new ArrayList<String>();
		for (Taxonomy child : children) {
			result.add(child.getData());
		}
		return result;
	}
	
	public Taxonomy getChild(String data) {
		for (Taxonomy child : children) {
			if (child.getData().equals(data)) return child;
		}
		return null;
	}

	public Taxonomy addChild(String data) {
		Taxonomy result = new Taxonomy(data);
		result.setLevel(this.level + 1);
		result.addPath(this.path);
		result.setParent(this);
		children.add(result);
		return result;
	}
	
	public boolean match(String value, String ref) {
		int startIndex = 0, endIndex = 0;

		if (value.contains(ref) && value.length() != ref.length()) {
			startIndex = value.indexOf(ref) - 1;
			endIndex = startIndex + ref.length() + 1;
			
			//System.out.print(ref + ":" + value + " - ");
			
			/* Check for ref contained within a non-ref word */
			if ((startIndex >= 0 && value.charAt(startIndex) != ' ' && value.charAt(startIndex) != '-')) {
				//System.out.print("s:"+value.charAt(startIndex));
				return false;
			}else if ((value.length() > endIndex && value.charAt(endIndex) != ' ' && value.charAt(endIndex) != '/')) {
				//System.out.print("e:"+value.charAt(endIndex));
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public String findKeyword(String value) {
		String result = null;
		String temp = null;
		if (level == 5) {
			if (value.matches(".*\\b"+data+".*")) {
				result = pathString();
				//System.out.println("match:" + value + ":" + result + "!");
			} else {
				result = null;
			}
		} else if (children.size() != 0) {
			for (Taxonomy child : children) {
				temp = child.findKeyword(value);
				if (temp != null) {
					//compare to current result...
					if (result == null) {
						result = temp;
					} else if (temp.split(">")[5].contains(result.split(">")[5])) {
						result = temp;
					} else {
						//ignore
					}
				} // what to do if multiple matches? back up somehow?
			}
		}
		return result;
	}
	
	public String findKeyword1(String name, String cat1) {
		String result = null;
		String temp = null;
		List<String> lookup = getPath();
		
		if (level == 5) { //keyword level
			//if (match(name, data)) {
			if (name.matches(".*\\b"+data+".*") && lookup.get(1).matches(".*\\b"+cat1+".*")) {
			//if (match(name, data) && match(lookup.get(1),cat1)) {
				result = pathString();
				//System.out.print(lookup.get(1).contains(cat1));
				//System.out.println("match:" + cat1+"/"+name + ":" + lookup.get(1) +"/"+result + "!");
			} else {
				result = null;
			}
		} else if (children.size() != 0) {
			for (Taxonomy child : children) {
				temp = child.findKeyword1(name, cat1);
				if (temp != null) {
					//compare to current result...
					if (result == null) {
						result = temp;
					} else if (temp.split(">")[5].contains(result.split(">")[5])) {
						result = temp;
					} else {
						//ignore
					}
				} // what to do if multiple matches? back up somehow?
			}
			if (result == null) {
				//try a less specific search?
				//for (Taxonomy child : children) {
					//temp = child.findKeyword(cat1);
				//}
			}
		}
		
		return result;
	}
	
	public String findKeyword2(String name, String cat2) {
		String result = null;
		String temp = null;
		List<String> lookup = getPath();
		
		if (level == 5) { //keyword level
			//if (match(name, data)) {
			if (name.matches(".*\\b"+data+".*") && lookup.get(2).matches(".*\\b"+cat2+".*")) {
			//if (match(name, data) && match(lookup.get(1),cat1)) {
				result = pathString();
				//System.out.print(lookup.get(1).contains(cat1));
				//System.out.println("match:" + cat1+"/"+name + ":" + lookup.get(1) +"/"+result + "!");
			} else {
				result = null;
			}
		} else if (children.size() != 0) {
			for (Taxonomy child : children) {
				temp = child.findKeyword2(name, cat2);
				if (temp != null) {
					//compare to current result...
					if (result == null) {
						result = temp;
					} else if (temp.split(">")[5].contains(result.split(">")[5])) {
						result = temp;
					} else {
						//ignore
					}
				} // what to do if multiple matches? back up somehow?
			}
			if (result == null) {
				//try a less specific search?
				//for (Taxonomy child : children) {
					//temp = child.findKeyword(cat1);
				//}
			}
		}
		
		return result;
	}
	
	public String findDeepest(String value) {
		String result = null;
		String temp = null;
		int level = 0;
		if (children.size() != 0) {
			for (Taxonomy child : children) {
				temp = child.findDeepest(value);
				if (temp != null && Integer.parseInt(temp.substring(0,1)) > level) {
					result = temp;
					level = Integer.parseInt(temp.substring(0,1));
				} // what to do if multiple matches? back up somehow?
			}
			//if (match(data, value)) {
			//	temp = getLevel() + path() + data;
			//}
			//if (temp != null && Integer.parseInt(temp.substring(0,1)) > level) {
			//	result = temp;
			//	level = Integer.parseInt(temp.substring(0,1));
			//}
		} else {
			
			if (match(value, data)) {
				result = getLevel() + pathString();
				//System.out.println("match:" + value + ":" + result + "!");
			} else {
				result = null;
			}
		}
		
		if (result != null) {
		result = level + result.substring(1,result.length());
		}
		//return path
		return result;
	}
	
	public Set<String> pathList() {
		Set<String> result = new HashSet<String>();
		if (children.size() != 0) {
			for (Taxonomy child : children) {
				result.addAll(child.pathList());
			}
		} else {
			result.add(pathString());
		}
		return result;
	}
	
	public String print() {
		StringBuilder result = new StringBuilder();
		
		if (children.size() != 0) {
			for (Taxonomy child : children) {
				result.append(child.print());
			}
			return result.toString();
		} else {
			return pathString() + "\n";
		}
	}
	
}
