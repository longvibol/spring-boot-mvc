package com.piseth.java.school.phoneshopenight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.service.BrandService;

@Mapper(componentModel = "spring" , uses = {BrandService.class})
public interface ModelMapper {
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
	
	// Target is Model 
	// Source is ModelDTO
	@Mapping(target = "brand", source = "brandId")	
	Model toModel(ModelDTO dto);
	
	
	// Target is ModelDTO 
	// Source is Model	
	@Mapping(target = "brandId", source = "model.id")
	ModelDTO toModelDTO(Model model);
	
	
	/*
	
	// we want to convert from id to brandId mean when input they will input BrandId (sumong id =1) than Model Name: s12Pro
	// we do it manualy becasue mapstruc can not convert from BrandId to id of the Model) 
	default Brand toBrand(Integer brId) {
		Brand brand = new Brand();
		brand.setId(brId);
		
		return brand;
	}
	-- it alrady have this function from Integer to Brand
	*/
}

