package com.vksonpdl.qstnbnk.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "question_collection")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {

	@Id
	@Indexed(unique = true)
	private Long qid;
	private String qstn;
	private List<String> optns;
	private String ans;
	private String qstn_typ;
}
