package com.vksonpdl.qstnbnk.cntrlr;

import java.net.InetAddress;
import java.util.Date;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.vksonpdl.qstnbnk.model.EncryptionResponse;
import com.vksonpdl.qstnbnk.util.HashingUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {

	@Autowired
	HashingUtil hashingUtil;

	@GetMapping()
	public ResponseEntity<String> getMeHome() {
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

		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			log.info("IP Address:{}", ip);
		} catch (Exception e) {
			log.warn("Exception While getting IP, Exception:{}", e.getMessage());
		}
		return response;
	}
}
