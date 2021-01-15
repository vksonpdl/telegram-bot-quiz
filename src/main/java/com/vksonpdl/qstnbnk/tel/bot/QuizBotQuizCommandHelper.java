package com.vksonpdl.qstnbnk.tel.bot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.model.TriviaQuestion;
import com.vksonpdl.qstnbnk.service.CredentialService;
import com.vksonpdl.qstnbnk.service.QuestionService;
import com.vksonpdl.qstnbnk.service.QuizStatusService;
import com.vksonpdl.qstnbnk.service.TriviaService;
import com.vksonpdl.qstnbnk.session.obj.ChatSession;
import com.vksonpdl.qstnbnk.session.obj.ChatStore;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperCredential;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperQuiz;
import com.vksonpdl.qstnbnk.util.QuizHelper;

@Component
public class QuizBotQuizCommandHelper {

	@Autowired
	ChatStore chatStore;

	@Autowired
	TriviaService triviaService;

	@Autowired
	QuestionService questionService;

	@Autowired
	CredentialService credentialService;

	@Autowired
	QuizStatusService quizStatusService;

	@Autowired
	QuizHelper quizHelper;

	@Autowired
	MessageHelperCredential messageHelperCredential;

	@Autowired
	MessageHelperQuiz messageHelperQuiz;

	public String getMessage(String messageText, String returnMessage, Long chatId, String telId) {

		if (returnMessage.equals("TBD")) {

			ChatSession session = chatStore.getSession(chatId, telId);
			if (quizHelper.isQuestionType(messageText) && chatStore.isReadyToUpdateQuestiontype(chatId)) {

				List<TriviaQuestion> triviaQuestions = triviaService.getTriviaQuestions(session.getTriviaToken(),
						session.getQuizStatus());
				chatStore.updateSessionWithQuizTypeAndQuestions(chatId, messageText, triviaQuestions);
				chatStore.updateCurrentAnswerAndQuestionId(chatId, true);
				returnMessage = messageHelperQuiz.getQuestion(telId, chatStore.getQuizStatus(chatId));

			} else if (quizHelper.isAnswerType(messageText)) {
				if (chatStore.isReadyToUpdateAnswer(chatId, telId)) {

					boolean isValidAnswer = 
							questionService.validateQuestionAnswer(session.getQuizStatus(),messageText);
					boolean isQuestionsExceeded = 
							chatStore.updateAnswerStatusAndGetAvailableQuestionsCount(chatId,isValidAnswer);

					returnMessage = messageHelperQuiz.getMessageForAnswering(telId, isValidAnswer, isQuestionsExceeded,
							session.getQuizStatus());
					
					if (!isQuestionsExceeded) {
						chatStore.updateCurrentAnswerAndQuestionId(chatId, false);

					} else {
						quizStatusService.saveQuizStatus(telId, chatStore.getQuizStatus(chatId));
					}

					

				} else {
					returnMessage = messageHelperQuiz.getMessageForNotReadyToUpdateAnswer(telId);
				}

			}
		}

		return returnMessage;
	}
}
