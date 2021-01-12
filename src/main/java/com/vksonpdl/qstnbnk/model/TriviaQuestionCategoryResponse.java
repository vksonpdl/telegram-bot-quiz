package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TriviaQuestionCategoryResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<TriviaQuestionCategory> trivia_categories;
}
