package com.piseth.java.schoolmvc.phoneshopmvc.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundExpection extends ApiException{

	public ResourceNotFoundExpection(String resourName, Integer id) {
		super(HttpStatus.NOT_FOUND, String.format("%s With id = %d Not Found!",resourName,id));
	}

}
