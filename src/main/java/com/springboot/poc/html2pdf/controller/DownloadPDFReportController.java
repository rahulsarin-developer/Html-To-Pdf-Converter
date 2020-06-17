package com.springboot.poc.html2pdf.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.springboot.poc.html2pdf.model.DownloadFile;
import com.springboot.poc.html2pdf.utility.PDFGenerator;

@RestController
@RequestMapping("/html2pdf")
public class DownloadPDFReportController {

	public static final String MIME_TYPE = "application/octet-stream";
	public static final String FILE_HEADER = "Content-Disposition";
	public static final String DOWNLOAD_FILE_PROPERTY_NAME = "inline; filename=\"%s\"";

	@GetMapping(value = "downloadPdf")
	public StreamingResponseBody downloadThumbnail(HttpServletResponse httpResponse) {
		DownloadFile downloadFile = new DownloadFile("report.pdf",PDFGenerator.generatePDFContentFromHTML(getContentFromTemplate("E:\\html_template\\index.html")).toByteArray());
		httpResponse.setContentType(MIME_TYPE);
		httpResponse.setHeader(FILE_HEADER, String.format(DOWNLOAD_FILE_PROPERTY_NAME, downloadFile.getFileName()));
		return new StreamingResponseBody() {
			//@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				outputStream.write(downloadFile.getFile());
				outputStream.flush();
			}
		};
	}

	private String getContentFromTemplate(String templateFileLocation) {
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(templateFileLocation));
			String str;
			while((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch(IOException e) {}
		return contentBuilder.toString();
	}
}