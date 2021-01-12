package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TriviaToken implements Serializable {

	private static final long serialVersionUID = 1L;
	private int response_code;
	private String response_message;
	private String token;
}
