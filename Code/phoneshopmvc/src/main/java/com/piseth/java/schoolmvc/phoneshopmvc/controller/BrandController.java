package com.piseth.java.schoolmvc.phoneshopmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		return ResponseEntity.ok(Mapper.toBrandDTO(brand));
	}	

}
