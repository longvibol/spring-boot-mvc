package com.piseth.java.school.phoneshopenight.service;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;	
	@Mock
	private ProductImportHistoryRepository importHistoryRepository;	
	@Mock
	private ProductMapper productMapper;	
	@Mock
	private ProductServiceImpl productServiceImpl;	
	private ProductService productService;	
	@BeforeEach
	public void setUp() {		
		productService = new ProductServiceImpl(productRepository, importHistoryRepository, productMapper);		
	}	
	
	public void testSetSalePrice() {		
		
		Product product = new Product();		
		BigDecimal price = BigDecimal.valueOf(1000);	
		
		product.setId(1L);		
		
		productService.setSalePrice(1L, price);

		verify(productRepository, times(1)).save(product);
	}
	
	

}
