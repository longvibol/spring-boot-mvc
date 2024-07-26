package com.piseth.java.schoolmvc.phoneshopmvc.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class BrandSpec implements Specification<Brand>{
	
	private final BrandFilter brandFilter;
	// we put final mean we add the file to the constructor 
	
	List<Predicate> predicates = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {		
//		criteriaBuilder = cb 
		
		
		if(brandFilter.getName() != null) {
			//Predicate name = brand.get("name").in(brandFilter.getName());
			
			Predicate name = cb.like(cb.upper(brand.get("name")), "%"+brandFilter.getName().toUpperCase()+"%");
			predicates.add(name);
		}
		
		if(brandFilter.getId() != null) {
			Predicate id = brand.get("id").in(brandFilter.getId());
			predicates.add(id);
		}
		
		// convert from List to Array 		
//		Predicate[] pp = predicates.toArray(new Predicate[0]);		
//		return cb.and(pp);
		
		return cb.and(predicates.toArray(Predicate[]::new));
		
	}

}
