package com.vksonpdl.qstnbnk.session.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaQuestion;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class QuizStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public QuizStatus() {
		this.sessionStartTime = new Date().getTime();
		this.validity = Long.valueOf(1000*60*QuizConstants.QUIZ_QUSTION_COUNT); // 10 minutes
	}
	
	private String currentAns;
	private int ansrCountValid;
	private int ansrCountInValid;
	private int currentQId;
	private boolean startQuiz;
	private String quizType;
	
	private List<TriviaQuestion> triviaQuestion;
	
	private Long sessionStartTime;
	private Long validity;
	
	

}
