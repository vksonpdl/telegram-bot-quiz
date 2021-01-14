package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Base64Utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TriviaQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String question;
	private String correct_answer;
	private List<String> incorrect_answers;
	private String category;
	private String type;
	private String difficulty;

	public String getQuestion() {

		return decodeMessage(question);
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrect_answer() {

		return decodeMessage(correct_answer);
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public List<String> getIncorrect_answers() {
		List<String> incorrect_answers_decoded = new ArrayList<>();
		for (String incorrect_answer : incorrect_answers) {
			incorrect_answers_decoded.add(decodeMessage(incorrect_answer));
		}
		return incorrect_answers_decoded;
	}

	public void setIncorrect_answers(List<String> incorrect_answers) {
		this.incorrect_answers = incorrect_answers;
	}

	public String getCategory() {
		return decodeMessage(category);
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return decodeMessage(type);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDifficulty() {
		return decodeMessage(difficulty);
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	private String decodeMessage(String message) {
		return new String(Base64Utils.decodeFromString(message));
	}
}
