package com.piseth.java.schoolmvc.phoneshopmvc.service.util;

import com.piseth.java.schoolmvc.phoneshopmvc.dto.BrandDTO;
import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;

public interface Mapper{
	
	//from outside to db 
	public static Brand toBrand(BrandDTO dto){
		Brand brand = new Brand();
		//brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}
	
	public static BrandDTO toBrandDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(brand.getId());
		brandDTO.setName(brand.getName());
		return brandDTO;
		
	}

}
