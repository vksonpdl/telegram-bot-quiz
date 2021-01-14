package com.vksonpdl.qstnbnk.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;
import com.vksonpdl.qstnbnk.service.TriviaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BotCacheable {

	@Autowired
	TriviaService triviaService;

	@Cacheable(value = "questionTypeCache", key = "#root.methodName")
	public TriviaQuestionCategoryResponse getQuestionTypes() {
		TriviaQuestionCategoryResponse response = new TriviaQuestionCategoryResponse();
		List<TriviaQuestionCategory> trivia_categories = new ArrayList<>();

		TriviaQuestionCategoryResponse responseFromService = triviaService.getQuestionCategory();

		for (TriviaQuestionCategory triviaQuestionCategory : responseFromService.getTrivia_categories()) {
			int questionsCount = triviaService.getQuestionCategoryCount(triviaQuestionCategory);
			if (questionsCount >= (QuizConstants.QUIZ_QUSTION_COUNT * 2)) {
				trivia_categories.add(triviaQuestionCategory);
				log.info("Question category id:{} added  - questions count:{}", triviaQuestionCategory.getId(),
						questionsCount);
			} else {
				log.warn("Question category id:{} removed due to insufficiant questions - questions count:{}",
						triviaQuestionCategory.getId(), questionsCount);
			}

			response.setTrivia_categories(trivia_categories);
		}

		return response;
	}

}
