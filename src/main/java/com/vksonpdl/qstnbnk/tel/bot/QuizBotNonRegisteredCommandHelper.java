package com.vksonpdl.qstnbnk.tel.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.service.CredentialService;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelper;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperCredential;

@Component
public class QuizBotNonRegisteredCommandHelper {

	@Autowired
	CredentialService credentialService;

	@Autowired
	MessageHelperCredential messageHelperCredential;

	public String getMessage(String messageText, String returnMessage, Long chatId, String telId) {

		if (messageText.equals(MessageHelper.MESSAGE_REGISTER)) {

			credentialService.createCredential(telId);
			returnMessage = messageHelperCredential.welcomeRegistredUserMessage(telId);

		} else {
			returnMessage = messageHelperCredential.welcomeNotRegistredUserMessage(telId);
		}

		return returnMessage;
	}
}
