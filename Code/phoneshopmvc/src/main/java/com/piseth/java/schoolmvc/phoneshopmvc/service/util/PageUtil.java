package com.piseth.java.schoolmvc.phoneshopmvc.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
	
	// when we use interface it is finial for each field 
	int DEFAULT_PAGE_LIMIT = 2;
	int DEFAULT_PAGE_NUMBER = 1;
	String PAGE_LIMIT = "_limit";
	String PAGE_NUMBER = "_page";
	
	// PAGE_LIMIT & PAGE_NUMBER are the key when the user input 
	
	static Pageable getPageable(int pageNumber, int pageSize) {
		
		if(pageNumber < DEFAULT_PAGE_NUMBER) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		
		if(pageSize <1) {
			pageSize = DEFAULT_PAGE_NUMBER;
		}
		
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		
		return pageable;
		
	}

}
