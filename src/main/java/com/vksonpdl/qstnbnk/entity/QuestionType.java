package com.vksonpdl.qstnbnk.entity;



import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "question_type_collection")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionType {

	@Id
	private ObjectId _id;
	private String qstn_typ;
	
}
