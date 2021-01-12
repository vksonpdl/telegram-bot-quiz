package com.vksonpdl.qstnbnk.tel.mg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;
import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;

@Component
public class MessageHelperQuiz {

	StringBuffer messageBuilder;

	@Autowired
	BotCacheable botCacheable;

	public String getQuizTypeSellectionMessage(String telId) {

		TriviaQuestionCategoryResponse questionTypes = botCacheable.getQuestionTypes();
		int count=1;

		messageBuilder = new StringBuffer();
		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE)
				.append("Select Quiz Type from Below List !").append(MessageHelper.NEWLINE).append(MessageHelper.NEWLINE);

		

		for (TriviaQuestionCategory questionType : questionTypes.getTrivia_categories()) {
			messageBuilder.append(count).append(") /").append(questionType.getId()).append(" : ").append(questionType.getName())
			.append(MessageHelper.NEWLINE);
			count++;
		}
				
		messageBuilder.append(MessageHelper.NEWLINE)
		.append("There will be total "+QuizConstants.QUIZ_QUSTION_COUNT+" questions you have to attent, and "+QuizConstants.QUIZ_QUSTION_COUNT+" minutes will be allocated.")
				.append(MessageHelper.NEWLINE)
				.append("If you are not able to attent answer all questions within the specified time, the session will be timed out !")
				.append(MessageHelper.NEWLINE);

		return messageBuilder.toString();
	}
	
	public String getMessageForNotReadyToUpdateAnswer(String telId) {

		messageBuilder = new StringBuffer();
		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE)
				.append("Quiz session is interupted !").append(MessageHelper.NEWLINE)
				.append(MessageHelper.MESSAGE_START).append(" to start from beginning");
		

		return messageBuilder.toString();
	}
}
