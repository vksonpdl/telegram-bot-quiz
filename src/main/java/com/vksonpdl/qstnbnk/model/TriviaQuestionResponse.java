package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TriviaQuestionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int response_code;
	List<TriviaQuestion> results;
}
