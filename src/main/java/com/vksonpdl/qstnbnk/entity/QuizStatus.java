package com.vksonpdl.qstnbnk.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "quiz_status_collection")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizStatus {

	
	@Id
	private ObjectId _id;
	
	private String telUn;
	private int ansrCountValid;
	private int ansrCountInValid;
	private String quizType;
	private float score;
	private Date completionDate;

}
