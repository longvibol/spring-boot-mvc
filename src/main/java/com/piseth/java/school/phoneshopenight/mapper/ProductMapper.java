package com.piseth.java.school.phoneshopenight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piseth.java.school.phoneshopenight.dto.ProductDTO;
import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.service.ColorService;
import com.piseth.java.school.phoneshopenight.service.ModelService;

@Mapper(componentModel = "spring", 
	uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO productDTO);
	
	
	/*
	@ManyToOne
	@JoinColumn(name="model_id")
	private Model model;
	- We want convert from ProductDTO(modelId) to Product(target: product is "private Model model"==> modelId)	
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	 */
	
	// we want to import product Mapper 
	
	
	@Mapping(target = "dateImport", source = "importDTO.importDate")
	@Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id", ignore = true) // we ignore the id : we don't want to setId
	ProductImportHistory toProductImportHistory(ProductImportDTO importDTO, Product product); 
	
	/*
	 * Source : ProductImportDTO: importDate
	 * 
	 * Target:ProductImportHistory: dateImport
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}