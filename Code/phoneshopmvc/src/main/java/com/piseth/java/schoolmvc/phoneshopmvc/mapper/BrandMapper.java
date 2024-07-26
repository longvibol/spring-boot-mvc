package com.piseth.java.schoolmvc.phoneshopmvc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.piseth.java.schoolmvc.phoneshopmvc.dto.BrandDTO;
import com.piseth.java.schoolmvc.phoneshopmvc.entity.Brand;

@Mapper
public interface BrandMapper {

	// we want from outside to inside (want Brand Entity) source from BrandDTO	
	
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	Brand toBrand(BrandDTO dto);
	
	BrandDTO toBrandDTO(Brand entity);

}
