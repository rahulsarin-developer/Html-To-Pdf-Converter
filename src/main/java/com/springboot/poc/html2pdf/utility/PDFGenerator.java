package com.springboot.poc.html2pdf.utility;

import java.io.ByteArrayOutputStream;

public class PDFGenerator {

	/*private static final String PDF_CONVERSION_COMMAND = "C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf --javascript-delay 5000 --margin-left 0  --margin-right 0 --margin-top 10 --header-html E:/Poc_Java/html_template/includes/header.html --footer-html "
			+ "E:/Poc_Java/html_template/includes/footer.html --footer-spacing 10 - -";*/
	
	private static final String PDF_CONVERSION_COMMAND = "C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf --javascript-delay 5000 --footer-center [page]/[topage] --page-offset 4 --margin-left 0 --margin-right 0 --margin-top 10 - -";

	/**
	 * 
	 * @param htmlContent
	 * @return
	 */
	public static ByteArrayOutputStream generatePDFContentFromHTML(String htmlContent) {
		try {
			return PDFConvertorUtil.generatePDF(htmlContent, PDF_CONVERSION_COMMAND, "UTF-8");
		} catch (Exception e) {}
		return null;
	}
}