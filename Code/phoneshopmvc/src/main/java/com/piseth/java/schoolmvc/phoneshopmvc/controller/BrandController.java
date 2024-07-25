package com.piseth.java.schoolmvc.phoneshopmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.schoolmvc.phoneshopmvc.dto.BrandDTO;
import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;
import com.piseth.java.schoolmvc.phoneshopmvc.service.BrandService;
import com.piseth.java.schoolmvc.phoneshopmvc.service.util.Mapper;


@RestController
@RequestMapping("brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
		
		Brand brand = Mapper.toBrand(brandDTO);
		brand = brandService.create(brand);		
		
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));	}	
	
	
	@RequestMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){
		Brand brand = brandService.findById(brandId);
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
		
		
		//toBrand = our real db 
		//toBrandDTO = to ourside 
		
		Brand brand = Mapper.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(Mapper.toBrandDTO(updatedBrand));
	}
	
	

}




















