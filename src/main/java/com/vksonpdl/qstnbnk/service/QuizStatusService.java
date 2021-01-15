package com.vksonpdl.qstnbnk.service;

import com.vksonpdl.qstnbnk.session.obj.QuizStatus;

public interface QuizStatusService {

	public void saveQuizStatus(String telId, QuizStatus quizStatus);
}
