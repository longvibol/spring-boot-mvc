package com.piseth.java.school.phoneshopenight.service.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;

public class ReportTestHelper {
	
	private static Product product1 = Product.builder()
			.id(1L)
			.name("iphone 14 pro")
			.build();
	
	private static Product product2 = Product.builder()
			.id(2L)
			.name("iphone 13 pro max")
			.build();
	
	private static Product product3 = Product.builder()
			.id(2L)
			.name("samsung galaxy s10")
			.build();
	
	public static List<Product> getProducts(){
		return List.of(product1,product2);
	}
	
	public static List<ProductImportHistory> getProductImportHistories(){
		
		ProductImportHistory history1 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(3)
				.pricePerUnit(BigDecimal.valueOf(1000))
				.dateImport(LocalDateTime.of(2024, 1, 1, 12, 50))
				.build();
		
		ProductImportHistory history2 = ProductImportHistory.builder()
				.product(product2)
				.importUnit(2)
				.pricePerUnit(BigDecimal.valueOf(700))
				.dateImport(LocalDateTime.of(2024, 1, 5, 10, 20))
				.build();
		
		ProductImportHistory history3 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(5)
				.pricePerUnit(BigDecimal.valueOf(2000))
				.dateImport(LocalDateTime.of(2024, 1, 6, 11, 30))
				.build();
		
		return List.of(history1,history2,history3);
	}

}
