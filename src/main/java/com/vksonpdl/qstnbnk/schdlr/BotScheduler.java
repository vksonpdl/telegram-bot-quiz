package com.vksonpdl.qstnbnk.schdlr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BotScheduler {
	
	@Autowired
	BotCacheable botCacheable;
	
	@Autowired
	CacheManager cacheManager;

	@Scheduled(cron = "${schdlr.trivia.qstntypes}")
	public void updateQuestionTypesInBotCache() {
		
		log.info("Executing Scheduler updateQuestionTypesInBotCache()");
		
		cacheManager.getCache("questionTypeCache").invalidate();
		botCacheable.getQuestionTypes();
		
		log.info("Cache Populated Successfully");
		
	}
	
	@EventListener(classes = ApplicationStartedEvent.class)
	public void listenToStart(ApplicationStartedEvent event) {
		botCacheable.getQuestionTypes();
	}
	
}
