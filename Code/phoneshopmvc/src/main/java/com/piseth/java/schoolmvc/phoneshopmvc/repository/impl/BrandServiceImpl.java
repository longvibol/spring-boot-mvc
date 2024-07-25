package com.piseth.java.schoolmvc.phoneshopmvc.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;
import com.piseth.java.schoolmvc.phoneshopmvc.exception.ApiException;
import com.piseth.java.schoolmvc.phoneshopmvc.exception.ResourceNotFoundExpection;
import com.piseth.java.schoolmvc.phoneshopmvc.repository.BrandRepository;
import com.piseth.java.schoolmvc.phoneshopmvc.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {

		return brandRepository.save(brand);

	}

	@Override
	public Brand findById(Integer id) {
		
		Optional<Brand> brandOptional = brandRepository.findById(id);
		
		/*
		if(brandOptional.isPresent()) {
			return brandOptional.get();
		}
		//throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand with id = "+ id +"not found"); Not recomment
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found", id));
		// throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand with id = %d not found".formatted(id)); // version java 15 up
		*/
		
		return brandRepository.findById(id)
				//.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand with id = %d not found".formatted(id)));
		//.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Brand with id = %d not found".formatted(id)));
				.orElseThrow(() -> new ResourceNotFoundExpection("Brand", id));
				
	}

	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		
		Brand brand = findById(id);
		brand.setName(brandUpdate.getName()); // Need to update in case it have many field how to do it 
		
		return brandRepository.save(brand);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
