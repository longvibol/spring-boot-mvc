package com.piseth.java.schoolmvc.phoneshopmvc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.schoolmvc.phoneshopmvc.dto.BrandDTO;
import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;
import com.piseth.java.schoolmvc.phoneshopmvc.mapper.BrandMapper;
import com.piseth.java.schoolmvc.phoneshopmvc.service.BrandService;

@RestController
@RequestMapping("brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {

		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);

		// return ResponseEntity.ok(Mapper.toBrandDTO(brand)); }
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	@RequestMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId) {

		Brand brand = brandService.findById(brandId);

		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO) {

		// toBrand = our real db
		// toBrandDTO = to ourside

		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
	}
	
	@GetMapping
	public ResponseEntity<?> getBrands() {
		
		List<BrandDTO> list = brandService.getBrands()
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());			

		return ResponseEntity.ok(list);

	}
	
	@GetMapping(name = "filter")
	public ResponseEntity<?> findByName(@RequestParam("name") String name) {
		
		List<BrandDTO> list = brandService.getBrands(name)
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());			

		return ResponseEntity.ok(list);

	}

}
