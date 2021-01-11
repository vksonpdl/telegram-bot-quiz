package com.vksonpdl.qstnbnk.tel.mg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;

@Component
public class MessageHelperQuiz {

	StringBuffer messageBuilder;

	@Autowired
	BotCacheable botCacheable;

	public String getQuizTypeSellectionMessage(String telId) {

		List<String> questionTypes = botCacheable.getQuestionTypes();
		int count=1;

		messageBuilder = new StringBuffer();
		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE)
				.append("Select Quiz Type from Below List !").append(MessageHelper.NEWLINE);
		
		for (String questionType : questionTypes) {
			messageBuilder.append(count).append(") /").append(questionType).append(MessageHelper.NEWLINE);
			count++;
		}
				
		messageBuilder.append("There will be total 20 questions you have to attent, and 25 minutes will be allocated.")
				.append(MessageHelper.NEWLINE)
				.append("If you are not able to attent answer 20 questions, the session will be timed out !")
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
