package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TriviaQuestionCategoryCountResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private int category_id;
	private TriviaQuestionCategoryCount category_question_count;

}



