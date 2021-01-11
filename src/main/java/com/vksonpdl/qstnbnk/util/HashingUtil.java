package com.vksonpdl.qstnbnk.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HashingUtil {

	@Value("${salt.one}")
	String saltOne;

	@Value("${salt.two}")
	String saltTwo;

	public String doEncryption(String text) {
		if (null == text || text.isEmpty()) {
			log.error("Encryption Error : Empty or Null String");
		} else {
			text = DigestUtils.md5Hex(saltOne.concat(saltTwo).concat(text));
		}

		return text;
	}
}
