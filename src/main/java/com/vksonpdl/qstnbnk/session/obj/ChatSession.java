package com.vksonpdl.qstnbnk.session.obj;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ChatSession implements Serializable{

	public ChatSession() {
		this.sessionStartTime = new Date().getTime();
		this.validity = Long.valueOf(1000*60*25); // 25 minutes
	}
	private static final long serialVersionUID = 1L;
	private QuizStatus quizStatus;
	private Long sessionStartTime;
	private Long validity;
}
