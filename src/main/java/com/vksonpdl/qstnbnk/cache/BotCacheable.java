package com.vksonpdl.qstnbnk.cache;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryCountResponse;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BotCacheable {

	@Autowired
	RestTemplate restTemplete;

	@Value("${trivia.host}")
	String triviaHost;
	
	@Value("${trivia.path.category}")
	String triviaQuestionCategoryPath;
	
	@Value("${trivia.path.category_count}")
	String triviaQuestionCategoryCountPath;

	
	@Cacheable(value = "questionTypeCache", key = "#root.methodName")
	public TriviaQuestionCategoryResponse getQuestionTypes() {
		TriviaQuestionCategoryResponse response = new TriviaQuestionCategoryResponse();
		List<TriviaQuestionCategory> trivia_categories = new ArrayList<>();

		String urlString = triviaHost.concat(triviaQuestionCategoryPath);

		try {
			log.info("Calling Trivia Service :{} from getQuestionTypes()", urlString);
			ResponseEntity<TriviaQuestionCategoryResponse> triviaResponse = restTemplete.exchange(new URI(urlString),HttpMethod.GET, null, TriviaQuestionCategoryResponse.class);

			if (triviaResponse.getStatusCode().equals(HttpStatus.OK)) {

				log.info("trivia Service :{} call is success Status:{}", urlString,triviaResponse.getStatusCodeValue());
				TriviaQuestionCategoryResponse responseFromService = triviaResponse.getBody();
				

				for (TriviaQuestionCategory triviaQuestionCategory : responseFromService.getTrivia_categories()) {
					int questionsCount = getQuestionCount(triviaQuestionCategory);
					if (questionsCount >= (QuizConstants.QUIZ_QUSTION_COUNT * 2)) {
						trivia_categories.add(triviaQuestionCategory);
						log.info("Question category id:{} added  - questions count:{}",triviaQuestionCategory.getId(), questionsCount);
					}else {
						log.warn("Question category id:{} removed due to insufficiant questions - questions count:{}",triviaQuestionCategory.getId(), questionsCount);
					}
					
					response.setTrivia_categories(trivia_categories);
				}

			} else {
				response.setTrivia_categories(new ArrayList<>());
				log.error("Non 200 Response code From trivia Service :{}, Status:{}", urlString,triviaResponse.getStatusCodeValue());
			}
		} catch (Exception e) {
			response.setTrivia_categories(new ArrayList<>());
			log.error("Exception while calling trivia Service :{}, Exception:{}", urlString, e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	private int getQuestionCount(TriviaQuestionCategory triviaQuestionCategory) {

		int questionsCount = 0;
		String urlString = triviaHost.concat(triviaQuestionCategoryCountPath).concat(String.valueOf(triviaQuestionCategory.getId()));
		
		try {

			
			log.info("Calling Trivia Service :{} from getQuestionCount()", urlString);
			ResponseEntity<TriviaQuestionCategoryCountResponse> triviaCategoryCountResponse = restTemplete.exchange(new URI(urlString), HttpMethod.GET, null, TriviaQuestionCategoryCountResponse.class);
			if (triviaCategoryCountResponse.getStatusCode().equals(HttpStatus.OK)) {

				log.info("trivia Service :{} call is success Status:{}", urlString,triviaCategoryCountResponse.getStatusCodeValue());

				TriviaQuestionCategoryCountResponse triviaQuestionCategoryCountResponse = triviaCategoryCountResponse.getBody();

				questionsCount = triviaQuestionCategoryCountResponse.getCategory_question_count().getTotal_medium_question_count();

			} else {

				log.error("Non 200 Response code From trivia Service :{}, Status:{}", urlString,triviaCategoryCountResponse.getStatusCodeValue());
			}
		} catch (Exception e) {

			log.error("Exception while calling trivia Service :{}, Exception:{}", urlString, e.getMessage());
			e.printStackTrace();
		}

		return questionsCount;
	}
}
