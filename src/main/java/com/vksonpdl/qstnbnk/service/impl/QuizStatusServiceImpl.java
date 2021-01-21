package com.vksonpdl.qstnbnk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.vksonpdl.qstnbnk.cache.BotCacheable;
import com.vksonpdl.qstnbnk.repo.QuizStatusRepo;
import com.vksonpdl.qstnbnk.service.QuizStatusService;
import com.vksonpdl.qstnbnk.session.obj.QuizStatus;
import com.vksonpdl.qstnbnk.util.HashingUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizStatusServiceImpl implements QuizStatusService {

	@Autowired
	QuizStatusRepo quizStatusRepo;

	
	@Autowired
	HashingUtil hashingUtil;
	
	@Autowired
	BotCacheable botCacheable;
	
	@Override
	public void saveQuizStatus(String telId, QuizStatus quizStatus) {

		try {
			com.vksonpdl.qstnbnk.entity.QuizStatus statusToSave = new com.vksonpdl.qstnbnk.entity.QuizStatus();
			
			BeanUtils.copyProperties(quizStatus, statusToSave);
			statusToSave.setQuizType(botCacheable.getQuestionTypes().get(Integer.parseInt(quizStatus.getQuizType())));
			statusToSave.setTelUn(hashingUtil.doEncryption(telId));
			statusToSave.setCompletionDate(new Date());
			quizStatusRepo.save(statusToSave);
			log.info("Quiz Status saved");
		}catch (Exception e) {
			log.error("Exception while saving Quiz Status, Exception: {}",e.getMessage());
		}
	}

	@Override
	public List<com.vksonpdl.qstnbnk.entity.QuizStatus> getQuizStatus(String telId) {

		List<com.vksonpdl.qstnbnk.entity.QuizStatus> quizStatusList = new ArrayList<>();

		PageRequest page = PageRequest.of(0, 5);

		Query query = new Query();
		query.addCriteria(Criteria.where("").is(hashingUtil.doEncryption(telId)));
		quizStatusList = quizStatusRepo.findSortedRecordsById(hashingUtil.doEncryption(telId), page);
		
		return quizStatusList;
	}

}
