package com.piseth.java.school.phoneshopenight.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail>{
	// We want to build our own customer criterial startdate , price or etc ... from SaleDetail 
	
	private SaleDetailFilter detailFilter;
	

	
	// within this point we can create CriteriaBuilder filter for start date and end date 
	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>(); // we want to get the correct cb then we add to the array list 		
		
		// join object from saleDetail (object: Sale->sale) to sale (because sale have field saleDate) 		
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		
		
		
		if(Objects.nonNull(detailFilter.getStartDate())){
			
			cb.greaterThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());
			//soldDate is the filed inside Sale 
		}
		
		if(Objects.nonNull(detailFilter.getEndDate())){
			
			cb.lessThanOrEqualTo(sale.get("soldDate"), detailFilter.getEndDate());
		}
		
		// we have list then want to convert to Array 
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		
		return predicate;
	}

}
