package com.vksonpdl.qstnbnk.tel.mg;

import java.util.ArrayList;
import java.util.List;

public final class MessageHelper {

	public final static String NEWLINE = "\n";

	public final static String MESSAGE_START = "/start";
	public final static String MESSAGE_REGISTER = "/register";
	public final static String MESSAGE_QUIZ_START = "/startQuiz";
	public final static String MESSAGE_QUIZ_RESULTS = "/viewResults";
	public final static String MESSAGE_EMAIL_REGISTER = "/registerEmail";
	public final static String MESSAGE_EMAIL_VIEW = "/viewEmail";
	public final static String MESSAGE_EMAIL_REPORT = "/sendReport";

	public static final List<String> getAnswerOptions() {
		List<String> answerOptions = new ArrayList<>();
		answerOptions.add("/a");
		answerOptions.add("/b");
		answerOptions.add("/c");
		answerOptions.add("/d");
		return answerOptions;
	}

	public final static String getBold(String text) {

		text = "<b>".concat(text).concat("</b>");
		return text;
	}

	public final static String getItalics(String text) {

		text = "<i>".concat(text).concat("</i>");
		return text;
	}

	public String getUnderLigned(String text) {

		text = "<u>".concat(text).concat("</u>");
		return text;
	}
}
