package com.vksonpdl.qstnbnk.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String plainText;
	private String encryptedText;
	private Date encryptionDate;

}
