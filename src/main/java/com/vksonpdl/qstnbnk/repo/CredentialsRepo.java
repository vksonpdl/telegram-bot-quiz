package com.vksonpdl.qstnbnk.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vksonpdl.qstnbnk.entity.Credentials;

@Repository
public interface CredentialsRepo extends MongoRepository<Credentials, Long>{

}
