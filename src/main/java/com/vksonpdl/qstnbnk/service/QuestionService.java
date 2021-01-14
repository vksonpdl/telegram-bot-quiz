package com.vksonpdl.qstnbnk.service;

import com.vksonpdl.qstnbnk.session.obj.QuizStatus;

public interface QuestionService {
	
	public boolean validateQuestionAnswer(QuizStatus quizStatus,String answerOption);

}
