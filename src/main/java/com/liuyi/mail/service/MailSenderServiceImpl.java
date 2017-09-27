package com.liuyi.mail.service;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.liuyi.util.ContextUtils;
import com.liuyi.util.MailAttachment;
import com.liuyi.util.MailInfo;

@Service
public class MailSenderServiceImpl implements MailSenderService {

	private static final String SEPARATOR = ";";

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(MailInfo mailInfo) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			String to = mailInfo.getTo();
			if (StringUtils.isBlank(to))
				throw new RuntimeException("The receiver email address is empty.");

			if (to.contains(SEPARATOR)) {
				for (String toAddress : to.split(SEPARATOR)) {
					if (StringUtils.isNotBlank(toAddress))
						helper.addTo(StringUtils.trim(toAddress));
				}
			} else {
				helper.setTo(StringUtils.trim(to));
			}

			String cc = mailInfo.getCc();
			if (StringUtils.isNotBlank(cc)) {
				if (cc.contains(SEPARATOR))
					for (String toAddress : cc.split(SEPARATOR)) {
						if (StringUtils.isNotBlank(toAddress))
							helper.addCc(StringUtils.trim(toAddress));
					}
				else
					helper.setCc(StringUtils.trim(cc));
			}

			String bcc = mailInfo.getBcc();
			if (StringUtils.isNotBlank(bcc)) {
				if (bcc.contains(SEPARATOR))
					for (String toAddress : bcc.split(SEPARATOR)) {
						if (StringUtils.isNotBlank(toAddress))
							helper.addBcc(StringUtils.trim(toAddress));
					}
				else
					helper.setBcc(StringUtils.trim(bcc));
			}

			if (StringUtils.isNotBlank(mailInfo.getFrom())) {
				helper.setFrom(mailInfo.getFrom());
			} else {
				helper.setFrom(ContextUtils.getProperty("mail.username"));
			}
			helper.setSubject(mailInfo.getSubject());
			helper.setText(mailInfo.getText(), mailInfo.isHtml());
			for (MailAttachment attachment : mailInfo.getAttachments())
				helper.addAttachment(attachment.getAttachmentFilename(), attachment.getInputStreamSource(), attachment.getContentType());
		} catch (Exception e) {
			throw new MailParseException(e);
		}

		mailSender.send(message);
	}
}
