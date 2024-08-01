package com.piseth.java.school.phoneshopenight.service.impl;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ModelDTO;
import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.mapper.ModelMapper;
import com.piseth.java.school.phoneshopenight.repository.ModelRepository;
import com.piseth.java.school.phoneshopenight.service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{
	private final ModelRepository modelRepository;	
	private final ModelMapper modelMapper;
	
	@Override
	public Model save(ModelDTO dto) { 
		
		Model model = modelMapper.toModel(dto);
		/*
		Integer brandId = model.getBrand().getId();
		brandService.getById(brandId);
		becasue our: modelMapper.toModel(modelDTO) already class the method getById from controller so 
		we no need to call it again! 
		*/
		return modelRepository.save(model);
	}

	

}
