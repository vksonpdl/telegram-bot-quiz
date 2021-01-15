package com.vksonpdl.qstnbnk.tel.mg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.cache.BotCacheable;
import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaQuestion;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategory;
import com.vksonpdl.qstnbnk.model.TriviaQuestionCategoryResponse;
import com.vksonpdl.qstnbnk.session.obj.QuizStatus;


@Component
public class MessageHelperQuiz {

	StringBuffer messageBuilder;

	@Autowired
	BotCacheable botCacheable;
	
	

	public String getQuizTypeSellectionMessage(String telId) {

		TriviaQuestionCategoryResponse questionTypes = botCacheable.getQuestionTypes();

		messageBuilder = new StringBuffer();
		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE)
				.append("Select Quiz Type from Below List !").append(MessageHelper.NEWLINE)
				.append(MessageHelper.NEWLINE);

		for (TriviaQuestionCategory questionType : questionTypes.getTrivia_categories()) {
			messageBuilder.append("/").append(questionType.getId()).append(" : ").append(questionType.getName())
					.append(MessageHelper.NEWLINE);
		}

		messageBuilder.append(MessageHelper.NEWLINE)
				.append("There will be total " + QuizConstants.QUIZ_QUSTION_COUNT
						+ " questions you have to attent, and " + QuizConstants.QUIZ_QUSTION_COUNT
						+ " minutes will be allocated.")
				.append(MessageHelper.NEWLINE)
				.append("If you are not able to attent answer all questions within the specified time, the session will be timed out !")
				.append(MessageHelper.NEWLINE);

		return messageBuilder.toString();
	}
	
	public String getMessageForNotReadyToUpdateAnswer(String telId) {

		messageBuilder = new StringBuffer();
		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE).append("Quiz session is interupted !")
				.append(MessageHelper.NEWLINE).append(MessageHelper.MESSAGE_START).append(" to start from beginning");

		return messageBuilder.toString();
	}
	
	public String getQuestion(String telId, QuizStatus status) {

		messageBuilder = new StringBuffer();

		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE);
		messageBuilder.append(getQuesionOptionstoDisplay(status));

		return messageBuilder.toString();
	}
	
	

	
	public String getMessageForAnswering(String telId, boolean isValidAnswer, boolean isQuestionsExceeded,
			QuizStatus status) {

		messageBuilder = new StringBuffer();
		messageBuilder.append("Hi ").append(telId).append(MessageHelper.NEWLINE).append(MessageHelper.NEWLINE);
		if (isValidAnswer) {
			messageBuilder.append(MessageHelper.getItalics("Congragulations, the previous answer was Correct !"))
					.append(MessageHelper.NEWLINE);
		} else {
			messageBuilder.append(MessageHelper.getItalics("Sorry, the previous answer was incorrect !"))
					.append(MessageHelper.NEWLINE);
		}
		if (isQuestionsExceeded) {
			messageBuilder.append(getQuizResults(status));
		} else {

			messageBuilder.append(getQuesionOptionstoDisplay(status));

		}

		return messageBuilder.toString();
	}
	
	private String getQuesionOptionstoDisplay(QuizStatus status) {

		int counter = 0;
		String answerOption = "";		
		List<String> answers = new ArrayList<>();
		List<String> answerOptions = MessageHelper.getAnswerOptions();
		answerOptions.remove(status.getCurrentAns());
		StringBuffer messageBuilder = new StringBuffer();
		TriviaQuestion triviaQuestion = status.getTriviaQuestion().get(status.getCurrentQId());

		answers.add(status.getCurrentAns().concat(" : ").concat(triviaQuestion.getCorrect_answer()));

		while (counter < answerOptions.size()) {
			answerOption = answerOptions.get(counter);
			answers.add(answerOption.concat(" : ").concat(triviaQuestion.getIncorrect_answers().get(counter)));
			counter++;
		}

		Collections.sort(answers);

		
		messageBuilder
			.append(MessageHelper.NEWLINE)
			.append(status.getCurrentQId()+1).append(") Question: ").append(triviaQuestion.getQuestion())
			.append(MessageHelper.NEWLINE);
		
		for (String answerOptiontoDisplay : answers) {
			messageBuilder.append(answerOptiontoDisplay).append(MessageHelper.NEWLINE);
		}

		messageBuilder.append(MessageHelper.NEWLINE).append("Select Proper Answer From the above list");

		return messageBuilder.toString();
	}
	
	private String getQuizResults(QuizStatus status) {

		StringBuffer messageBuilder = new StringBuffer();
		messageBuilder
			.append(MessageHelper.getBold("Quiz is complete")).append(MessageHelper.NEWLINE)
			.append(MessageHelper.NEWLINE).append("Total Questions: ").append(status.getCurrentQId() + 1).append(MessageHelper.NEWLINE)
			.append("Correct Answers: ").append(status.getAnsrCountValid()).append(MessageHelper.NEWLINE)
			.append("Incorrect Answers: ").append(status.getAnsrCountInValid()).append(MessageHelper.NEWLINE)
			.append("Quiz Score(%): ").append(status.getScore()).append(MessageHelper.NEWLINE);

		return messageBuilder.toString();
	}
	
}
