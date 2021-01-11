package com.vksonpdl.qstnbnk.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vksonpdl.qstnbnk.entity.QuestionType;
import com.vksonpdl.qstnbnk.repo.QuestionTypeRepo;

@Service
public class BotCacheable {

	@Autowired
	QuestionTypeRepo questionTypeRepo;

	@Cacheable(value = "questionTypeCache", key = "#root.methodName")
	public List<String> getQuestionTypes() {

		List<String> questionTypeList = new ArrayList<>();
		for (QuestionType questionType : questionTypeRepo.findAll()) {
			questionTypeList.add(questionType.getQstn_typ());
		}
		return questionTypeList;
	}
}
