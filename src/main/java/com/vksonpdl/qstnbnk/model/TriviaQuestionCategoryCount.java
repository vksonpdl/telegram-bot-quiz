package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TriviaQuestionCategoryCount implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int total_question_count;
	private int total_easy_question_count;
	private int total_medium_question_count;
	private int total_hard_question_count;
	
}