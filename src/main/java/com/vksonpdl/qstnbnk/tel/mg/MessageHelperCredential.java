package com.vksonpdl.qstnbnk.tel.mg;

import org.springframework.stereotype.Component;

@Component
public class MessageHelperCredential {


	StringBuffer messageBuilder;
	
	public String welcomeNotRegistredUserMessage(String telId) {
		
		messageBuilder= new StringBuffer();
		messageBuilder.append("Welcome ").append(telId).append(MessageHelper.NEWLINE)
			.append(MessageHelper.MESSAGE_REGISTER).append(" :to start attending Telegram Quiz !").append(MessageHelper.NEWLINE);
		
		return messageBuilder.toString();
	}
	
public String welcomeRegistredUserMessage(String telId) {
		
		messageBuilder= new StringBuffer();
		messageBuilder.append("Welcome ").append(telId).append(MessageHelper.NEWLINE)
			.append(MessageHelper.MESSAGE_QUIZ_START).append(" : to start Telegram Quiz !").append(MessageHelper.NEWLINE)
			.append(MessageHelper.MESSAGE_QUIZ_RESULTS).append(" : to view Telegram Quiz Results!").append(MessageHelper.NEWLINE)
			.append(MessageHelper.MESSAGE_EMAIL_REGISTER).append(" : to Register E-mail !").append(MessageHelper.NEWLINE)
			.append(MessageHelper.MESSAGE_EMAIL_VIEW).append(" : to view Registered E-mail !").append(MessageHelper.NEWLINE)
			.append(MessageHelper.MESSAGE_EMAIL_REPORT).append(" : to send Quiz Results E-mail !").append(MessageHelper.NEWLINE);
		
		return messageBuilder.toString();
	}

public String welcomeRegistredUserInvalidMessage(String telId) {
	
	messageBuilder= new StringBuffer();
	messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE)
		.append("Invalid Message was sent, please use one of the below Bot Ation.").append(MessageHelper.NEWLINE)
		.append(MessageHelper.MESSAGE_QUIZ_START).append(" : to start Telegram Quiz !").append(MessageHelper.NEWLINE)
		.append(MessageHelper.MESSAGE_QUIZ_RESULTS).append(" : to view Telegram Quiz Results!").append(MessageHelper.NEWLINE)
		.append(MessageHelper.MESSAGE_EMAIL_REGISTER).append(" : to Register E-mail !").append(MessageHelper.NEWLINE)
		.append(MessageHelper.MESSAGE_EMAIL_VIEW).append(" : to view Registered E-mail !").append(MessageHelper.NEWLINE)
		.append(MessageHelper.MESSAGE_EMAIL_REPORT).append(" : to send Quiz Results E-mail !").append(MessageHelper.NEWLINE);
	
	return messageBuilder.toString();
}
}
