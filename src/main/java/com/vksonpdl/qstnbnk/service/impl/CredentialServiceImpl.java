package com.vksonpdl.qstnbnk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vksonpdl.qstnbnk.entity.Credentials;
import com.vksonpdl.qstnbnk.repo.CredentialsRepo;
import com.vksonpdl.qstnbnk.service.CredentialService;
import com.vksonpdl.qstnbnk.util.HashingUtil;

@Service
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	CredentialsRepo credentialsRepo;
	
	@Autowired HashingUtil hashingUtil;
	
	@Override
	public void createCredential(String username) {
		
		Credentials credential = new Credentials();
		credential.setTelUn(hashingUtil.doEncryption(username));
		credentialsRepo.insert(credential);
		
		
	}

	@Override
	public boolean isCredentialExist(String username) {
		return credentialsRepo.existsById(hashingUtil.doEncryption(username));
	}

}
