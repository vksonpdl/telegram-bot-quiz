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
	
	public final static String MESSAGE_OPTION_A = "/a ";
	public final static String MESSAGE_OPTION_B = "/b ";
	public final static String MESSAGE_OPTION_C = "/c ";
	public final static String MESSAGE_OPTION_D = "/d ";
	
	public static final List<String> MESSAGE_OPLIONS_LIST= new ArrayList<>();

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
