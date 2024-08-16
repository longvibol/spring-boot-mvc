package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductSoldDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;
import com.piseth.java.school.phoneshopenight.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;

	@Override
	public void sell(SaleDTO saleDTO) {

		
		List<Long> productIds = saleDTO.getProducts().stream().map(ProductSoldDTO::getProductId).toList();
		// validate on product: this step mean we can get the id from DB
		productIds.forEach(productService::getById);		
		// we want to get all iteam in db where our id input 
		List<Product> products = productRepository.findAllById(productIds); 
		// we convert to Map then we can get the Id :: Value		
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity() ));		
		// from this Id we can get the product what we want 	
		// validate on stock
		saleDTO.getProducts().forEach(ps -> {			
			// we want to compare the product Unit to ProductSaleDTO :aviable unit			
			Product product = productMap.get(ps.getProductId());			
			if(product.getAvailableUnit() < ps.getNumberOfunit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, 
						"Product [%s] Not enough product in stock!".formatted(product.getName()));
			}
		});
		
		// sale 
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());		
		saleRepository.save(sale);
		
		// Sale Detail 
		saleDTO.getProducts().forEach(ps -> {
			Product product = productMap.get(ps.getProductId());
			
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(product.getSalePrice());
			saleDetail.setProduct(product);
			saleDetail.setSale(sale);
			saleDetail.setUnit(ps.getNumberOfunit());
			saleDetailRepository.save(saleDetail);
			
			
			// cut stock 
			
			
		Integer availableUnit =	product.getAvailableUnit() - ps.getNumberOfunit();
		product.setAvailableUnit(availableUnit);
		productRepository.save(product);
			
		});

	}
	
	private void saveSale(SaleDTO saleDTO) {
		
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());
		
		saleRepository.save(sale);
		
	}
	
	private void validate (SaleDTO saleDTO) {
		
		saleDTO.getProducts().forEach(ps ->{
			
			Product product = productRepository.getById(ps.getProductId());
			
			if(product.getAvailableUnit() < ps.getNumberOfunit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, 
						"Product [%s] Not enough product in stock!".formatted(product.getName()));
			}			
			
		});
	}


	@Override
	public void cancelSale(Long saleId) {
		//update sale status 
		Sale sale = getById(saleId);
		sale.setActive(false);
		saleRepository.save(sale);
		
		
		// update stock
		List<SaleDetail> saleDetail = saleDetailRepository.findBySaleId(saleId);
		
		List<Long> productIds = saleDetail.stream().map(sd -> sd.getProduct().getId()).toList();
		
		List<Product> products = productRepository.findAllById(productIds); 
		
		// from products we can get Map of the product by Id 
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity() ));	
		
		saleDetail.forEach(sd -> {
			Product product = productMap.get(sd.getProduct().getId());
			product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit()); // cancel manh + jol stock ving 
			productRepository.save(product);		
			
		});		
	}

	@Override
	public Sale getById(Long saleId) {		
		return saleRepository.findById(saleId).orElseThrow(() -> 
		new ResourceNotFoundException("Sale = ", saleId));
	}

}



















