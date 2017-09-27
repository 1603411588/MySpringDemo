package com.liuyi.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamSource;

public class MailInfo {
	private String from = StringUtils.EMPTY;

	private String to = StringUtils.EMPTY;

	private String cc = StringUtils.EMPTY;

	private String bcc = StringUtils.EMPTY;

	private String subject = StringUtils.EMPTY;

	private String text = StringUtils.EMPTY;

	private boolean isHtml;

	private List<MailAttachment> attachments = new ArrayList<MailAttachment>();

	protected Date requestTime;
	protected String requestMessage;
	protected Date responseTime;
	protected String responseMessage;

	public String getTo() {
		return to;
	}

	public void setTo(String sendTo) {
		this.to = sendTo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String message) {
		this.text = message;
	}

	public void addAttachment(String attachmentFilename, InputStreamSource inputStreamSource, String contentType) {
		attachments.add(new MailAttachment(attachmentFilename, inputStreamSource, contentType));
	}

	public List<MailAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<MailAttachment> attachments) {
		this.attachments = attachments;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public boolean isHtml() {
		return isHtml;
	}

	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (from != null)
			buf.append("From:" + from + ", ");

		if (to != null)
			buf.append("To:" + to + ", ");

		if (subject != null)
			buf.append("Subject:" + subject + ", ");

		if (text != null)
			buf.append("Text:" + text);

		return buf.toString();
	}
}