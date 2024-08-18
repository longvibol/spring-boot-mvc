package com.piseth.java.school.phoneshopenight.dto.report;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseReportDTO {
	
//	productid, productName,total unit, total Amount
	
	private Long productId;
	private String productName;
	private Integer totalUnit;
	private BigDecimal totalAmout;


}
