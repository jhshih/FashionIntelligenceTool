package com.fit.objects;

public class MatchConfigBO {

	private String colourRef;
	private String taxonomyRef;
	private String inputFile;
	private String nameCol;	
	private String colourCol;
	private String cat1Col;
	private String cat2Col;
	private String outputFile;
	private String errorFile;
	
	public MatchConfigBO() {
		this.colourRef = null;
		this.taxonomyRef = null;
		this.inputFile = null;
		this.nameCol = null;
		this.colourCol = null;
		this.cat1Col = null;
		this.cat2Col = null;
		this.outputFile = null;
		this.errorFile = null;		
	}
	
	public String toString() {
		return 	"===CONFIGURATION===\n" +
				"Colour reference file: " + this.colourRef + "\n" +
				"Taxonomy reference file: " + this.taxonomyRef + "\n" +
				"Input file: " + this.inputFile + "\n" +
				"Output file: " + this.outputFile + "\n" +
				"Error file: " + this.errorFile + "\n" + 
				"-------------------";
	}

	public String getColourRef() {
		return colourRef;
	}

	public void setColourRef(String colourRef) {
		this.colourRef = colourRef;
	}

	public String getTaxonomyRef() {
		return taxonomyRef;
	}

	public void setTaxonomyRef(String taxonomyRef) {
		this.taxonomyRef = taxonomyRef;
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public int getNameCol() {
		return Integer.parseInt(nameCol);
	}

	public void setNameCol(String nameCol) {
		this.nameCol = nameCol;
	}

	public int getColourCol() {
		return Integer.parseInt(colourCol);
	}

	public void setColourCol(String colourCol) {
		this.colourCol = colourCol;
	}
	
	public int getCat1Col() {
		return Integer.parseInt(cat1Col);
	}

	public void setCat1Col(String cat1Col) {
		this.cat1Col = cat1Col;
	}

	public int getCat2Col() {
		return Integer.parseInt(cat2Col);
	}

	public void setCat2Col(String cat2Col) {
		this.cat2Col = cat2Col;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getErrorFile() {
		return errorFile;
	}

	public void setErrorFile(String errorFile) {
		this.errorFile = errorFile;
	}



}
