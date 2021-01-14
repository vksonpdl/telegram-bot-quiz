package com.vksonpdl.qstnbnk.tel.bot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vksonpdl.qstnbnk.model.TriviaQuestion;
import com.vksonpdl.qstnbnk.service.CredentialService;
import com.vksonpdl.qstnbnk.service.QuestionService;
import com.vksonpdl.qstnbnk.service.TriviaService;
import com.vksonpdl.qstnbnk.session.obj.ChatSession;
import com.vksonpdl.qstnbnk.session.obj.ChatStore;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelper;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperCredential;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperQuiz;
import com.vksonpdl.qstnbnk.util.QuizHelper;

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
	CredentialService credentialService;
	
	@Autowired
	ChatStore chatStore;
	
	@Autowired
	QuizHelper quizHelper;

	@Autowired
	MessageHelperCredential messageHelperCredential;
	
	@Autowired
	MessageHelperQuiz messageHelperQuiz;
	
	@Autowired
	TriviaService triviaService;
	
	@Autowired
	QuestionService questionService;
	
	

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {

			String messageText = update.getMessage().getText();
			String telId = update.getMessage().getFrom().getUserName();
			Long chatId = update.getMessage().getChatId();
			ChatSession session = chatStore.getSession(chatId, telId);

			SendMessage message = new SendMessage();
			message.setParseMode(ParseMode.HTML);
			message.setChatId(update.getMessage().getChatId().toString());

			if (null!=session) {

				switch (messageText) {
				
				case MessageHelper.MESSAGE_START:
					chatStore.startNewSession(chatId, telId);
					message.setText(messageHelperCredential.welcomeRegistredUserMessage(telId));
					break;
					
				case MessageHelper.MESSAGE_QUIZ_START:
					chatStore.updateSessionWithQuizStatus(chatId);
					message.setText(messageHelperQuiz.getQuizTypeSellectionMessage(telId));
					break;

				default:
					
					if(quizHelper.isQuestionType(messageText) && chatStore.isReadyToUpdateQuestiontype(chatId)) {
						
						List<TriviaQuestion> triviaQuestions = triviaService.getTriviaQuestions(session.getTriviaToken(), session.getQuizStatus());
						chatStore.updateSessionWithQuizTypeAndQuestions(chatId, messageText,triviaQuestions);
						chatStore.updateCurrentAnswerAndQuestionId(chatId,true);
						message.setText(messageHelperQuiz.getQuestion(telId,chatStore.getQuizStatus(chatId)));
						
					} else if (quizHelper.isAnswerType(messageText)) {
						if (chatStore.isReadyToUpdateAnswer(chatId, telId)) {

							boolean isValidAnswer = questionService.validateQuestionAnswer(session.getQuizStatus(),
									messageText);
							boolean isQuestionsExceeded = chatStore
									.updateAnswerStatusAndGetAvailableQuestionsCount(chatId, isValidAnswer);

							if (!isQuestionsExceeded) {
								chatStore.updateCurrentAnswerAndQuestionId(chatId, false);
							}

							message.setText(messageHelperQuiz.getMessageForAnswering(telId, isValidAnswer,
									isQuestionsExceeded, session.getQuizStatus()));

						} else {
							message.setText(messageHelperQuiz.getMessageForNotReadyToUpdateAnswer(telId));
						}

					}else {
						message.setText(messageHelperCredential.welcomeRegistredUserInvalidMessage(telId));
					}
					
					break;
				}

			} else if (messageText.equals(MessageHelper.MESSAGE_REGISTER)){
				
				credentialService.createCredential(telId);
				message.setText(messageHelperCredential.welcomeRegistredUserMessage(telId));
				
			}else {
				message.setText(messageHelperCredential.welcomeNotRegistredUserMessage(telId));
			}

			try {
				


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
