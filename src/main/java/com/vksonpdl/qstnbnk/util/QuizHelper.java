package com.vksonpdl.qstnbnk.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
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
			List<TriviaQuestionCategory> questionCategoryList = botCacheable.getQuestionTypes().getTrivia_categories();
			List<Integer> concat = questionCategoryList.stream().map(TriviaQuestionCategory::getId)
					.collect(Collectors.toList());

			if (concat.contains(Integer.parseInt(questionType.replace("/", "")))) {
				isQuestionType = true;
			}
		} catch (Exception e) {
			log.debug("Message is not a number");
		}

		return isQuestionType;
	}

	public boolean isAnswerType(String answerType) {

		boolean isAnswerType = false;

		// TODO: Update MessageHelper.MESSAGE_OPTION_*
		if (answerType.equals(MessageHelper.MESSAGE_OPTION_A) || answerType.equals(MessageHelper.MESSAGE_OPTION_B)
				|| answerType.equals(MessageHelper.MESSAGE_OPTION_C)
				|| answerType.equals(MessageHelper.MESSAGE_OPTION_D)) {
			isAnswerType = true;
		}

		return isAnswerType;
	}

}
