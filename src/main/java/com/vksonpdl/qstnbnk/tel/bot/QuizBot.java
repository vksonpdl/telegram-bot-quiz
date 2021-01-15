package com.vksonpdl.qstnbnk.tel.bot;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vksonpdl.qstnbnk.session.obj.ChatStore;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperCredential;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QuizBot extends TelegramLongPollingBot {

	@Value("${telegram.bot.username}")
	private String botUserName;

	@Value("${telegram.bot.token}")
	private String botToken;

	@Value("${app.name}")
	private String appName;

	@Autowired
	ChatStore chatStore;

	@Autowired
	MessageHelperCredential messageHelperCredential;

	@Autowired
	QuizBotMainCommandHelper quizBotMainCommandHelper;
	
	@Autowired
	QuizBotQuizCommandHelper quizBotQuizCommandHelper;
	
	@Autowired
	QuizBotNonRegisteredCommandHelper quizBotNonRegisteredCommandHelper;

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {

			String messageText = update.getMessage().getText();
			String telId = update.getMessage().getFrom().getUserName();
			Long chatId = update.getMessage().getChatId();


			SendMessage message = new SendMessage();
			message.setParseMode(ParseMode.HTML);
			message.setChatId(update.getMessage().getChatId().toString());
			String returnMessage = "TBD";

			if (null != chatStore.getSession(chatId, telId)) {

				returnMessage = quizBotMainCommandHelper.getMessage(messageText, returnMessage, chatId, telId);
				returnMessage = quizBotQuizCommandHelper.getMessage(messageText, returnMessage, chatId, telId);

				if (returnMessage.equals("TBD")) {
					returnMessage = messageHelperCredential.welcomeRegistredUserInvalidMessage(telId);
				}

			}  else {
				returnMessage = quizBotNonRegisteredCommandHelper.getMessage(messageText,returnMessage, chatId, telId);
			}

			try {
				
				message.setText(returnMessage);
				execute(message);
			} catch (TelegramApiException e) {
				log.error("TelegramApiException From onUpdateReceived() : {}", e.getMessage());
			}

		} else {
			log.warn("No Proper Message in the Update");
		}

	}

	@Override
	public String getBotUsername() {
		return botUserName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
}
