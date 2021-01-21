package com.vksonpdl.qstnbnk.repo;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vksonpdl.qstnbnk.entity.QuizStatus;

@Repository
public interface QuizStatusRepo extends MongoRepository<QuizStatus, ObjectId> {

	@Query(value = "{'telUn':?0}",sort = "{ 'completionDate': -1 }")
	public List<QuizStatus> findSortedRecordsById(String telid,PageRequest page);

}
