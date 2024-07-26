package com.piseth.java.schoolmvc.phoneshopmvc.service;

import java.util.List;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;

public interface BrandService{
	
	Brand create(Brand brand);
	Brand findById(Integer id);
	Brand update(Integer id, Brand brandUpdate);
	List<Brand> getBrands();
	List<Brand> getBrands(String name); // Method Overload that why have the same name getBrands

}
