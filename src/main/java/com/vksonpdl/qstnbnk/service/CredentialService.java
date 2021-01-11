package com.vksonpdl.qstnbnk.service;


public interface CredentialService {

	public void createCredential(String username);
	
	public boolean isCredentialExist(String username);
}
