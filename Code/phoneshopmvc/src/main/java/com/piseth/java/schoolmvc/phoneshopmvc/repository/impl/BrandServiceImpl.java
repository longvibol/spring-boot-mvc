package com.piseth.java.schoolmvc.phoneshopmvc.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;
import com.piseth.java.schoolmvc.phoneshopmvc.exception.ApiException;
import com.piseth.java.schoolmvc.phoneshopmvc.exception.ResourceNotFoundExpection;
import com.piseth.java.schoolmvc.phoneshopmvc.repository.BrandRepository;
import com.piseth.java.schoolmvc.phoneshopmvc.service.BrandService;
import com.piseth.java.schoolmvc.phoneshopmvc.service.util.PageUtil;
import com.piseth.java.schoolmvc.phoneshopmvc.spec.BrandFilter;
import com.piseth.java.schoolmvc.phoneshopmvc.spec.BrandSpec;

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
	
	@Override
	public List<Brand> getBrands(String name) {
		brandRepository.findByNameLike(name);
		return null;
	}	

	/*
	@Override
	public List<Brand> getBrands(Map<String, String> params) {
		
		BrandFilter brandFilter = new BrandFilter();
		
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);			
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id)); // convert from integer to string 
		}		
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		return brandRepository.findAll(brandSpec);
	}
	*/
	
	
	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		
		BrandFilter brandFilter = new BrandFilter();
		
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);			
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id)); // convert from integer to string 
		}			


		int pageNumber =PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT; 	
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);
		
		Page<Brand> page = brandRepository.findAll(brandSpec,pageable);
		
		return page;
	}

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
