package com.vksonpdl.qstnbnk.tel.bot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.entity.QuizStatus;
import com.vksonpdl.qstnbnk.service.QuizStatusService;
import com.vksonpdl.qstnbnk.session.obj.ChatStore;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelper;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperCredential;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelperQuiz;

@Component
public class QuizBotMainCommandHelper {

	@Autowired
	ChatStore chatStore;

	@Autowired
	MessageHelperCredential messageHelperCredential;

	@Autowired
	MessageHelperQuiz messageHelperQuiz;
	
	@Autowired
	QuizStatusService quizStatusService;

	public String getMessage(String messageText, String returnMessage, Long chatId, String telId) {

		if (returnMessage.equals("TBD")) {

			switch (messageText) {
			case MessageHelper.MESSAGE_START:
				chatStore.startNewSession(chatId, telId);
				returnMessage = messageHelperCredential.welcomeRegistredUserMessage(telId);
				break;
			case MessageHelper.MESSAGE_QUIZ_START:
				chatStore.updateSessionWithQuizStatus(chatId);
				returnMessage = messageHelperQuiz.getQuizTypeSellectionMessage(telId);
				break;
			case MessageHelper.MESSAGE_QUIZ_RESULTS:
				List<QuizStatus> quizStatusList=  quizStatusService.getQuizStatus(telId);
				returnMessage = messageHelperQuiz.getMessageForQuizResults(telId, quizStatusList);
				break;
			default:
				returnMessage = "TBD";
				break;
			}
		}

		return returnMessage;
	}

}
