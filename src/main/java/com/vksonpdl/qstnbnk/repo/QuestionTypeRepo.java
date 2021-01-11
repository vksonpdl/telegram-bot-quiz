package com.vksonpdl.qstnbnk.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vksonpdl.qstnbnk.entity.QuestionType;

public interface QuestionTypeRepo extends MongoRepository<QuestionType, ObjectId>{

}
