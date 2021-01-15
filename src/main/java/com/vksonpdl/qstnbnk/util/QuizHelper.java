package com.vksonpdl.qstnbnk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;
import com.vksonpdl.qstnbnk.tel.mg.MessageHelper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QuizHelper {

	@Autowired
	BotCacheable botCacheable;

	public boolean isQuestionType(String questionType) {

		boolean isQuestionType = false;
		try {
			if (botCacheable.getQuestionTypes().keySet().contains(Integer.parseInt(questionType.replace("/", "")))) {
				isQuestionType = true;
			}
		} catch (Exception e) {
			log.debug("Message is not a number");
		}

		return isQuestionType;
	}

	public boolean isAnswerType(String answerType) {

		boolean isAnswerType = false;

		if (MessageHelper.getAnswerOptions().contains(answerType)) {
			isAnswerType = true;
		}

		return isAnswerType;
	}

}
