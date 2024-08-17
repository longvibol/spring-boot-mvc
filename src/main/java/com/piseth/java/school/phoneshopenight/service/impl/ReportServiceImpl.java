package com.piseth.java.school.phoneshopenight.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.projection.ProductSold;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ReportService;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailFilter;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	
	private final SaleRepository saleRepository;
	private final SaleDetailRepository detailRepository;
	private final ProductRepository productRepository;

	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

	// programing criterial 
	@Override
	public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
		
		List<ProductReportDTO> list = new ArrayList<>(); // we create want to want and after the loop we add the item to that list 
		
		SaleDetailFilter saleDetailFilter = new SaleDetailFilter();
		
		saleDetailFilter.setStartDate(startDate);
		saleDetailFilter.setEndDate(endDate);
		
		Specification<SaleDetail> spec = new SaleDetailSpec(saleDetailFilter);
		
		List<SaleDetail> saleDetails = detailRepository.findAll(spec); // this poin we get all the data we want but we can see 
		//  there are many product that we get but we want to group by the product sale 
		
		
		List<Long> productId = saleDetails.stream().map(sd ->sd.getProduct().getId()).toList();
		
		List<Product> products = productRepository.findAllById(productId);
		
		// after we get the list of the Product we want to convert to Map easy to get the product 
		Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		// then we create output to what we want ProductReportDTO		
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream().collect(Collectors.groupingBy(SaleDetail::getProduct));
		
		// we loop inside the productMap to get the cb we want 
		
		for (var entry : saleDetailMap.entrySet()) {
			
			Product product = productMap.get(entry.getKey().getId());
			
			List<SaleDetail> sdList = entry.getValue();
			
			
			//total unit
			Integer unit = sdList.stream().map(SaleDetail::getUnit).reduce(0,(a,b) -> a+b);			
			// reduce : to convert to single resutl 
			
			//total amout 
			/* first option 
			Double totalAmout = sdList.stream().map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
					.reduce(0d, (a,b) -> a+b);
					
					*/
			//second option for total amout 
			double totalAmout = sdList.stream().mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue()).sum();
			
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmout(BigDecimal.valueOf(totalAmout));
			
			list.add(reportDTO);
			
		}
		
		
		return list;
	}

}

























