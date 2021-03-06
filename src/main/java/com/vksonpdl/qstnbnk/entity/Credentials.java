package com.vksonpdl.qstnbnk.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tel_credentials_collection")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credentials {
	
	
	@Id
	@Indexed(unique = true)
	private String telUn;		
	private String email;
	private String emailCode;
}
