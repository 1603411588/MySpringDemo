package com.liuyi.util;

import org.springframework.core.io.InputStreamSource;

public class MailAttachment {
	private String attachmentFilename;

	private String contentType;

	private InputStreamSource inputStreamSource;

	public MailAttachment() {

	}

	public MailAttachment(String attachmentFilename, InputStreamSource inputStreamSource, String contentType) {
		this.attachmentFilename = attachmentFilename;
		this.contentType = contentType;
		this.inputStreamSource = inputStreamSource;
	}

	public String getAttachmentFilename() {
		return attachmentFilename;
	}

	public void setAttachmentFilename(String attachmentFilename) {
		this.attachmentFilename = attachmentFilename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStreamSource getInputStreamSource() {
		return inputStreamSource;
	}

	public void setInputStreamSource(InputStreamSource inputStreamSource) {
		this.inputStreamSource = inputStreamSource;
	}

}
