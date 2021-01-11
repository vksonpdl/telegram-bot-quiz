package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String status;
	private String statusDescription;
	private QuestionModel question;
	
}
