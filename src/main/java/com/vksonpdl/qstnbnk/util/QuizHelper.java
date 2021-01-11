package com.vksonpdl.qstnbnk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelper;

@Component
public class QuizHelper {

	@Autowired
	BotCacheable botCacheable;

	public boolean isQuestionType(String questionType) {

		boolean isQuestionType = false;

		if (botCacheable.getQuestionTypes().contains(questionType.replace("/", ""))) {
			isQuestionType = true;
		}

		return isQuestionType;
	}
	
	public boolean isAnswerType(String answerType) {

		boolean isAnswerType = false;

		// TODO: Update MessageHelper.MESSAGE_OPTION_*
		if (answerType.equals(MessageHelper.MESSAGE_OPTION_A) || answerType.equals(MessageHelper.MESSAGE_OPTION_B) ||
				answerType.equals(MessageHelper.MESSAGE_OPTION_C) || answerType.equals(MessageHelper.MESSAGE_OPTION_D)) {
			isAnswerType = true;
		}

		return isAnswerType;
	}

}
