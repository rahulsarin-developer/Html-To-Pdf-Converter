package com.springboot.poc.html2pdf.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class PDFConvertorUtil {

	private PDFConvertorUtil() {}

	/**
	 * Generate PDF Stream using HTML Content
	 * 
	 * @param htmlContent
	 * @param command
	 * @param encoding
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static ByteArrayOutputStream generatePDF(String htmlContent, String command, String encoding)throws IOException {

		Process wkhtml; // Create Uninitialized Process

		try(ByteArrayOutputStream convertedStream = new ByteArrayOutputStream()) {
			
			//Read HTML Content
			InputStream inputStream = new ByteArrayInputStream(htmlContent.getBytes(Charset.forName(encoding)));

			wkhtml = Runtime.getRuntime().exec(command); //Start Process

			//Thread to read HTML content to set it to Process
			IOUtils.copy(inputStream, wkhtml.getOutputStream());

			wkhtml.getOutputStream().flush();
			wkhtml.getOutputStream().close();

			IOUtils.copy(wkhtml.getInputStream(), convertedStream);

			wkhtml.getInputStream().close();
			wkhtml.destroy();
			inputStream.close();

			return convertedStream;

		} catch (RuntimeException | IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}