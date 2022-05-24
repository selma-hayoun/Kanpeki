package com.dam.kanpeki.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.exception.EmailServerException;
import com.dam.kanpeki.model.dto.ResponseUserDTO;
import com.dam.kanpeki.utils.KanpekiConstants;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(String toEmail, String subject, String message) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);

		mailMessage.setFrom(KanpekiConstants.EMAIL);

		javaMailSender.send(mailMessage);
	}

	public void sendRegistrationMail(String toEmail) {
		String subject = "¡Bienvenido a Kanpeki!";

//		String message = "/nこんにちは!" + "/nSu cuenta será validada en breve (24-72 horas)."
//				+ "/nCualquier problema contacte con: kanpeki.japanese@gmail.com" + "/n/nまたね!";

//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//		mailMessage.setTo(toEmail);
//		mailMessage.setSubject(subject);
//		mailMessage.setText(message);
//
//		mailMessage.setFrom(KanpekiConstants.EMAIL);
//
//		javaMailSender.send(mailMessage);

		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, KanpekiConstants.API_ENCODING);
			String htmlMsg = "<h3>こんにちは!</h3>" + "<p>Su cuenta será validada en breve (24-72 horas).</p>"
					+ "<p>Cualquier problema contacte con: kanpeki.japanese@gmail.com</p>" + "<b>またね!</b>";
			// mimeMessage.setContent(htmlMsg, "text/html");//Otra forma de configurarlo
			helper.setText(htmlMsg, true);
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setFrom(KanpekiConstants.EMAIL);
			javaMailSender.send(mimeMessage);

		} catch (MessagingException e) {
			throw new EmailServerException();
		}

	}

	public void sendActivationMail(ResponseUserDTO u) {
		String subject = "¡Su cuenta ha sido activada!";

//		String message = "/nこんにちは!" + "/nYa puedes acceder a los recursos de estudio y hacer tu primer test. がんばれ!"
//				+ "/nCualquier problema contacte con: kanpeki.japanese@gmail.com" + "/n/nまたね!";
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//		mailMessage.setTo(u.getEmail());
//		mailMessage.setSubject(subject);
//		mailMessage.setText(message);
//
//		mailMessage.setFrom(KanpekiConstants.EMAIL);
//
//		javaMailSender.send(mailMessage);

		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, KanpekiConstants.API_ENCODING);
			String htmlMsg = "<h3>こんにちは!</h3>"
					+ "<p>Ya puedes acceder a los recursos de estudio y hacer tu primer test. <b>がんばれ!</b></p>"
					+ "<p>Cualquier problema contacte con: kanpeki.japanese@gmail.com</p>" + "<b>またね!</b>";
			helper.setText(htmlMsg, true);
			helper.setTo(u.getEmail());
			helper.setSubject(subject);
			helper.setFrom(KanpekiConstants.EMAIL);
			javaMailSender.send(mimeMessage);

		} catch (MessagingException e) {
			throw new EmailServerException();
		}
	}

}
