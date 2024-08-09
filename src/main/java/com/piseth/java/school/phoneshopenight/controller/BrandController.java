package com.piseth.java.school.phoneshopenight.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.BrandDTO;
import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.dto.PageDTO;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.mapper.BrandMapper;
import com.piseth.java.school.phoneshopenight.mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshopenight.service.BrandService;
import com.piseth.java.school.phoneshopenight.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("brands")
public class BrandController {
	
	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	
	// POST Method 
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	
	//GET BY ID
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	
	//PUT Method 
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
	}
	
	
	//GET Method GET Brands
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		Page<Brand> page = brandService.getBrands(params);
		
		PageDTO pageDTO = new PageDTO(page);
		/*
		List<BrandDTO> list = brandService.getBrands(params)
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());
		*/
		return ResponseEntity.ok(pageDTO);
		
	}
	
	// brands/1/models = Brand 1 = Nokia have List of Model = N75, N80 etc... 
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelByBrand(@PathVariable("id") Long brandId){
		List<Model> brands = modelService.getByBrand(brandId);
		
		List<ModelDTO> listModelDTO = brands.stream()
			.map(modelMapper::toModelDTO).toList();
		
		return ResponseEntity.ok(listModelDTO);
	}
	
	
}















