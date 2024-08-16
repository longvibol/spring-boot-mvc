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

public class SaleDetailSpec implements Specification<SaleDetail>{
	// We want to build our own customer criterial startdate , price or etc ...
	
	private SaleDetailFilter detailFilter;
	

	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		// join object from saleDetail (object: Sale->sale) to sale (becaue sale have field saleDate) 
		
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		
		if(Objects.nonNull(detailFilter.getStartDate())){
			
			cb.greaterThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());
		}
		
		if(Objects.nonNull(detailFilter.getStartDate())){
			
			cb.lessThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());
		}
		
		// we have list then want to convert to Array 
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		
		return predicate;
	}

}
