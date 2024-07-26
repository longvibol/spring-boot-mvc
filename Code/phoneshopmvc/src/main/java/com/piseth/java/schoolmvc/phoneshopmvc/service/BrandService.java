package com.piseth.java.schoolmvc.phoneshopmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;

public interface BrandService{
	
	Brand create(Brand brand);
	Brand findById(Integer id);
	Brand update(Integer id, Brand brandUpdate);
//	List<Brand> getBrands();
	List<Brand> getBrands(String name); // Method Overload that why have the same name getBrands	
//	List<Brand> getBrands(Map<String, String> params);
	
	Page<Brand> getBrands(Map<String, String> params);

}
