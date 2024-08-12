package com.piseth.java.school.phoneshopenight.service.impl;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository importHistoryRepository;
	private final ProductMapper productMapper;

	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(name);

		return productRepository.save(product);

	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		
		// we want to devide the importProduct into two table product and product import history
		
		// save or update available product- find product id first : if it have or not? 
		
//		Product product = this.getById(importDTO.getProductId()); in the same function we no need to put this.
		Product product = getById(importDTO.getProductId());
		Integer availableUnit = 0;
		
		if(product.getAvailableUnit() != null) {
			
			availableUnit = product.getAvailableUnit();
			
		}
		
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());		
		productRepository.save(product);
		
		
		// save product import history : stock update 
		
		ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
		importHistoryRepository.save(importHistory);
		
		
		
	}

}
















