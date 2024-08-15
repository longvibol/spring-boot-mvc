package com.piseth.java.school.phoneshopenight.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.service.ReportService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("reports/")
public class ReportController {
	
	private final ReportService reportService;
	
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> prodcutSale(){
		
//		@PathVariable "startDate", @PathVariable = "endDate"
		
		
		return ResponseEntity.ok().build();
	}
}
