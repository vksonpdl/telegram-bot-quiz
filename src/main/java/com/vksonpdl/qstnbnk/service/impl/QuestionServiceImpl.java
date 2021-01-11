package com.vksonpdl.qstnbnk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vksonpdl.qstnbnk.repo.QuestionRepo;
import com.vksonpdl.qstnbnk.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepo questionRepo;
	
	@Override
	public Long getQuestionsCount() {
		return questionRepo.count();
	}

}
