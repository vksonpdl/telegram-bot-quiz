package com.vksonpdl.qstnbnk.cntrlr;

import java.util.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vksonpdl.qstnbnk.model.EncryptionResponse;
import com.vksonpdl.qstnbnk.util.HashingUtil;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class ApiController {

	@Autowired
	HashingUtil hashingUtil;

	@GetMapping()
	public ResponseEntity<String> getMeHome() {
		log.info("welcome home log");
		String message = "TD ";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/encrypt/{plainText}")
	public ResponseEntity<EncryptionResponse> encryptString(@PathVariable("plainText") String plainText) {

		EncryptionResponse responsePayload = new EncryptionResponse();
		responsePayload.setPlainText(plainText);
		responsePayload.setEncryptionDate(new Date());
		responsePayload.setEncryptedText(hashingUtil.doEncryption(plainText));

		ResponseEntity<EncryptionResponse> response = new ResponseEntity<EncryptionResponse>(responsePayload,
				HttpStatus.OK);

		return response;
	}
	
}
