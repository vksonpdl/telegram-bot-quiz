package com.vksonpdl.qstnbnk.service;

import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;

public interface TriviaService {
	
	public TriviaQuestionCategoryResponse getQuestionCategory();
	public int getQuestionCategoryCount(TriviaQuestionCategory triviaQuestionCategory);

}
