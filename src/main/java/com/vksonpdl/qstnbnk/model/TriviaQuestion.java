package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TriviaQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String question;
	private String correct_answer;
	private List<String> incorrect_answers;
	private String category;
	private String type;
	private String difficulty;

}
