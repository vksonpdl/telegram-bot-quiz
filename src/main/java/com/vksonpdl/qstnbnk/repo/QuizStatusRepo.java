package com.vksonpdl.qstnbnk.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vksonpdl.qstnbnk.entity.QuizStatus;

@Repository
public interface QuizStatusRepo extends MongoRepository<QuizStatus, ObjectId>{

}
