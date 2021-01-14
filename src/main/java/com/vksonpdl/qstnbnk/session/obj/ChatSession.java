package com.vksonpdl.qstnbnk.session.obj;

import java.io.Serializable;
import java.util.Date;

import com.vksonpdl.qstnbnk.constant.QuizConstants;
import com.vksonpdl.qstnbnk.model.TriviaToken;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ChatSession implements Serializable{

	public ChatSession() {
		this.sessionStartTime = new Date().getTime();
		this.validity = Long.valueOf((1000*60*QuizConstants.QUIZ_QUSTION_COUNT)+(1000*60*5)); // 10 minutes
	}
	private static final long serialVersionUID = 1L;
	private QuizStatus quizStatus;
	private Long sessionStartTime;
	private Long validity;
	private TriviaToken triviaToken;
}
