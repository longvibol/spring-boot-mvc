package com.piseth.java.school.phoneshopenight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.impl.ReportServiceImpl;
import com.piseth.java.school.phoneshopenight.service.util.ReportTestHelper;
import com.piseth.java.school.phoneshopenight.spec.ProductImportHistorySpec;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	
	@Mock
	private  SaleRepository saleRepository;
	@Mock
	private  SaleDetailRepository detailRepository;
	@Mock
	private  ProductRepository productRepository;
	@Mock
	private  ProductImportHistoryRepository productImportHistoryRepository;
	
	
	private ReportService reportService;
	
	@BeforeEach
	public void setUp() {
		reportService = new ReportServiceImpl(saleRepository, detailRepository, productRepository, productImportHistoryRepository);
	}
	
	
	@Test
	public void testGetExpenseReport() {
		
		//given
		
		List<ProductImportHistory> importHistories = ReportTestHelper.getProductImportHistories();
		List<Product> products = ReportTestHelper.getProducts();
		
		
		//when : productImportHistoryRepository		
		when(productImportHistoryRepository.findAll(Mockito.any(ProductImportHistorySpec.class)))
				.thenReturn(importHistories);
		
		//when : product
		when(productRepository.findAllById(anySet())).thenReturn(products);	
		
		// our service test
		
		List<ExpenseReportDTO> expenseReports = reportService.getExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		
		//then
		assertEquals(2, expenseReports.size());
		ExpenseReportDTO expense1 = expenseReports.get(0);
		
		assertEquals(1, expense1.getProductId());
		assertEquals("iphone 14 pro", expense1.getProductName());
		assertEquals(8, expense1.getTotalUnit());
		assertEquals(13000, expense1.getTotalAmout().doubleValue());
		
		
	}
	
}

	
	
	


