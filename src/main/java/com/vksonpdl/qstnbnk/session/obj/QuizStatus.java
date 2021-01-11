package com.vksonpdl.qstnbnk.session.obj;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class QuizStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public QuizStatus() {
		this.sessionStartTime = new Date().getTime();
		this.validity = Long.valueOf(1000*60*10); // 10 minutes
	}
	private String telUn;
	private String telUnPlain;
	private int ansValid;
	private int ansInValid;
	private long currentQId;
	private boolean startQuiz;
	private String quizType;
	
	private Long sessionStartTime;
	private Long validity;

}
