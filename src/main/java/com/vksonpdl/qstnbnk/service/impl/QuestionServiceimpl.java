package com.vksonpdl.qstnbnk.service.impl;

import org.springframework.stereotype.Service;

import com.vksonpdl.qstnbnk.service.QuestionService;
import com.vksonpdl.qstnbnk.session.obj.QuizStatus;

@Service
public class QuestionServiceimpl implements QuestionService{

	@Override
	public boolean validateQuestionAnswer(QuizStatus quizStatus,String answerOption) {
		if(quizStatus.getCurrentAns().equals(answerOption)) {
			return true;
		}
		return false;
	}

}
