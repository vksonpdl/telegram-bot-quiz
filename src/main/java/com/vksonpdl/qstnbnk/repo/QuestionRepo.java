package com.vksonpdl.qstnbnk.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.vksonpdl.qstnbnk.entity.Question;

public interface QuestionRepo extends MongoRepository<Question, Long>{

}
