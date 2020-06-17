package com.springboot.poc.html2pdf.model;

public class DownloadFile {
	
	private String fileName;
	private byte[] file;
	
	public DownloadFile() {
		// TODO Auto-generated constructor stub
	}
	
	public DownloadFile(String fileName, byte[] file) {
		super();
		this.fileName = fileName;
		this.file = file;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}
}
