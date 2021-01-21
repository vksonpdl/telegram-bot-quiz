package com.vksonpdl.qstnbnk.service;

import java.util.List;

import com.vksonpdl.qstnbnk.session.obj.QuizStatus;

public interface QuizStatusService {

	public void saveQuizStatus(String telId, QuizStatus quizStatus);
	public List<com.vksonpdl.qstnbnk.entity.QuizStatus> getQuizStatus(String telId);
}
