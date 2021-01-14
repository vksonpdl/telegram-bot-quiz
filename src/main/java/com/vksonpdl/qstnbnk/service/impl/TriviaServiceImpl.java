package com.vksonpdl.qstnbnk.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaQuestion;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryCountResponse;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;
import com.vksonpdl.qstnbnk.model.TriviaQuestionResponse;
import com.vksonpdl.qstnbnk.model.TriviaToken;
import com.vksonpdl.qstnbnk.service.TriviaService;
import com.vksonpdl.qstnbnk.session.obj.QuizStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TriviaServiceImpl implements TriviaService {

	@Autowired
	RestTemplate restTemplete;

	@Value("${trivia.host}")
	String triviaHost;

	@Value("${trivia.path.category}")
	String triviaQuestionCategoryPath;

	@Value("${trivia.path.category_count}")
	String triviaQuestionCategoryCountPath;
	
	@Value("${trivia.path.token}")
	String triviaTokenPath;
	
	@Value("${trivia.path.questions}")
	String triviaQuestionsPath;

	@Override
	public TriviaQuestionCategoryResponse getQuestionCategory() {
		String urlString = triviaHost.concat(triviaQuestionCategoryPath);
		TriviaQuestionCategoryResponse triviaQuestionCategoryResponse = new TriviaQuestionCategoryResponse();
		try {
			log.info("Calling Trivia Service :{}", urlString);
			ResponseEntity<TriviaQuestionCategoryResponse> triviaResponse = restTemplete.exchange(new URI(urlString),
					HttpMethod.GET, null, TriviaQuestionCategoryResponse.class);
			if (triviaResponse.getStatusCode().equals(HttpStatus.OK)) {
				triviaQuestionCategoryResponse = triviaResponse.getBody();
				log.info("trivia Service :{} call is success Status:{}", urlString,
						triviaResponse.getStatusCodeValue());
			} else {
				triviaQuestionCategoryResponse.setTrivia_categories(new ArrayList<>());
				log.error("Non 200 Response code From trivia Service :{}, Status:{}", urlString,
						triviaResponse.getStatusCodeValue());
			}

		} catch (Exception e) {
			triviaQuestionCategoryResponse.setTrivia_categories(new ArrayList<>());
			log.error("Exception while calling trivia Service :{}, Exception:{}", urlString, e.getMessage());
		}

		return triviaQuestionCategoryResponse;
	}

	@Override
	public int getQuestionCategoryCount(TriviaQuestionCategory triviaQuestionCategory) {
		int questionsCount = 0;
		String urlString = triviaHost.concat(triviaQuestionCategoryCountPath)
				.concat(String.valueOf(triviaQuestionCategory.getId()));
		log.info("Calling Trivia Service :{}", urlString);
		try {
			ResponseEntity<TriviaQuestionCategoryCountResponse> triviaCategoryCountResponse = restTemplete
					.exchange(new URI(urlString), HttpMethod.GET, null, TriviaQuestionCategoryCountResponse.class);
			if (triviaCategoryCountResponse.getStatusCode().equals(HttpStatus.OK)) {

				log.info("trivia Service :{} call is success Status:{}", urlString,
						triviaCategoryCountResponse.getStatusCodeValue());
				questionsCount = triviaCategoryCountResponse.getBody().getCategory_question_count()
						.getTotal_medium_question_count();

			} else {

				log.error("Non 200 Response code From trivia Service :{}, Status:{}", urlString,
						triviaCategoryCountResponse.getStatusCodeValue());
			}
		} catch (Exception e) {
			log.error("Exception while calling trivia Service :{}, Exception:{}", urlString, e.getMessage());
		}

		return questionsCount;
	}

	@Override
	public TriviaToken getTriviaToken() {
		TriviaToken triviaToken = new TriviaToken();
		String urlString = triviaHost.concat(triviaTokenPath);

		try {
			log.info("Calling Trivia Service :{}", urlString);
			ResponseEntity<TriviaToken> triviaResponse = restTemplete.exchange(new URI(urlString), HttpMethod.GET, null,
					TriviaToken.class);

			if (triviaResponse.getStatusCode().equals(HttpStatus.OK)
					&& triviaResponse.getBody().getResponse_code() == 0) {

				log.info("trivia Service :{} call is success Status:{}", urlString,
						triviaResponse.getStatusCodeValue());
				triviaToken = triviaResponse.getBody();

			} else {

				log.error("Non 200 Response code From trivia Service :{}, Status:{}", urlString,
						triviaResponse.getStatusCodeValue());
			}

		} catch (Exception e) {
			log.error("Exception while calling trivia Service :{}, Exception:{}", urlString, e.getMessage());
		}

		return triviaToken;
	}

	@Override
	public List<TriviaQuestion> getTriviaQuestions(TriviaToken triviaToken, QuizStatus quizStatus) {
		String urlString = triviaHost.concat(triviaQuestionsPath);

		List<TriviaQuestion> triviaQuestions = new ArrayList<>();

		try {

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlString)
					.queryParam("amount", QuizConstants.QUIZ_QUSTION_COUNT)
					.queryParam("category", quizStatus.getQuizType())
					.queryParam("difficulty", QuizConstants.QUIZ_DIFICULTY).queryParam("type", QuizConstants.QUIZ_TYPE)
					.queryParam("encode", QuizConstants.QUIZ_ENCODE).queryParam("token", triviaToken.getToken());
			urlString = builder.toUriString();
			log.info("Calling Trivia Service :{}", urlString);
			ResponseEntity<TriviaQuestionResponse> triviaResponse = restTemplete.exchange(new URI(urlString),
					HttpMethod.GET, null, TriviaQuestionResponse.class);

			if (triviaResponse.getStatusCode().equals(HttpStatus.OK)
					&& triviaResponse.getBody().getResponse_code() == 0) {

				log.info("trivia Service :{} call is success Status:{}", urlString,
						triviaResponse.getStatusCodeValue());
				triviaQuestions = triviaResponse.getBody().getResults();

			} else {

				log.error("Non 200 Response code From trivia Service :{}, Status:{}", urlString,
						triviaResponse.getStatusCodeValue());
			}

		} catch (Exception e) {
			log.error("Exception while calling trivia Service :{}, Exception:{}", urlString, e.getMessage());
		}

		return triviaQuestions;
	}

}
