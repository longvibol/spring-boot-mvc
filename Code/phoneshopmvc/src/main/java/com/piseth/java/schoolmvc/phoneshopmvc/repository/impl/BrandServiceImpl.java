package com.piseth.java.schoolmvc.phoneshopmvc.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;
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

}
