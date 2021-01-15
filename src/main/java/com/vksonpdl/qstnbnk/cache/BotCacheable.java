package com.vksonpdl.qstnbnk.cache;

import java.util.HashMap;

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
	public HashMap<Integer, String> getQuestionTypes() {

		HashMap<Integer, String> questionCategory = new HashMap<>();

		TriviaQuestionCategoryResponse responseFromService = triviaService.getQuestionCategory();

		for (TriviaQuestionCategory triviaQuestionCategory : responseFromService.getTrivia_categories()) {
			int questionsCount = triviaService.getQuestionCategoryCount(triviaQuestionCategory);
			if (questionsCount >= (QuizConstants.QUIZ_QUSTION_COUNT * 2)) {
				questionCategory.put(triviaQuestionCategory.getId(), triviaQuestionCategory.getName());
				log.info("Question category id:{} added  - questions count:{}", triviaQuestionCategory.getId(),
						questionsCount);
			} else {
				log.warn("Question category id:{} removed due to insufficiant questions - questions count:{}",
						triviaQuestionCategory.getId(), questionsCount);
			}

		}

		return questionCategory;
	}

}
