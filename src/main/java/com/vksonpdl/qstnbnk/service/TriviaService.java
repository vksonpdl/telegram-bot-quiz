package com.vksonpdl.qstnbnk.service;

import java.util.List;

import com.vksonpdl.qstnbnk.model.TriviaQuestion;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;
import com.vksonpdl.qstnbnk.model.TriviaToken;
import com.vksonpdl.qstnbnk.session.obj.QuizStatus;

public interface TriviaService {
	
	public TriviaQuestionCategoryResponse getQuestionCategory();
	public int getQuestionCategoryCount(TriviaQuestionCategory triviaQuestionCategory);
	public TriviaToken getTriviaToken();
	public List<TriviaQuestion> getTriviaQuestions(TriviaToken triviaToken,QuizStatus quizStatus);
	

}
