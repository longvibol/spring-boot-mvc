package com.piseth.java.schoolmvc.phoneshopmvc.service.util;

import java.util.List;

public class GeneralUtilsTest {
	
	public static List<Integer> toIntegerList(List<String> list){
		
		return list.stream().map(s -> s.length()).toList();	
		
	}

}
